package com.vdi.batch.mds.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdi.batch.mds.service.ItopMDSDataLoaderService;
import com.vdi.configuration.AppConfig;
import com.vdi.model.staging.StagingUserRequest;
import com.vdi.tools.IOToolsService;

@Service("userRequestDataLoaderService")
public class UserRequestDataLoaderImpl implements ItopMDSDataLoaderService {

	private static final Logger logger = LogManager.getLogger(ServiceDeskDataLoaderImpl.class);
	private final String HTML_REGEX_CLEAR_TAG = "<[^<>]+>";
	private final String HTML_ENTITY_CLEAR = "(&nbsp;|&lt;|&gt;|&amp;|&quot;|&apos;)+";
	private final String UNACCENT_CLEAR = "[^\\p{Print}]";

	private List<StagingUserRequest> allStagingList;

	@Autowired
	private IOToolsService ioToolsService;

	@Autowired
	private AppConfig appConfig;

	public Elements parseTableTr(String data) {
		Elements rowsData;
		Document doc = Jsoup.parse(data);
		Element table = doc.select("table").get(0);
		rowsData = table.select("tr");

		return rowsData;
	}

	@Override
	public List<List<String>> loadTrToListVisionetByUrl() {
		return null;
	}

	@Override
	public List<List<String>> loadTrToListVisionetByFile() {
		return null;
	}

	public List<List<String>> loadTrToListAllByUrl() {

		List<List<String>> records = new ArrayList<List<String>>();
		Elements rows = parseTableTr(ioToolsService.readUrl(appConfig.getMdsHttpUrUrl()));

		if (rows != null && rows.size() > 0) {

			for (int i = 1; i < rows.size(); i++) {
				Element row = rows.get(i);
				Elements cols = row.select("td");

				List<String> record = new ArrayList<String>();

				for (int j = 0; j < cols.size(); j++) {
					Element col = cols.get(j);
					List<?> temp = new ArrayList<Object>();

					int size = col.childNodesCopy().size();

					if (size > 1) {
						StringBuffer sb = new StringBuffer();
						temp = col.childNodesCopy();

						Iterator<?> iter = temp.iterator();
						while (iter.hasNext()) {
							sb.append(iter.next());
						}

						record.add(sb.toString());
					} else if (size < 1) {
						record.add("");

					} else {
						temp = col.childNodes();
						Iterator<?> iter = temp.iterator();
						while (iter.hasNext()) {
							Object value = iter.next();

							if (value instanceof TextNode) {
								record.add(value.toString());
							} else if (value instanceof Element) {
								Element element = (Element) value;

								if (element != null) {
									int size1 = element.childNodesCopy().size();
									List<?> temp1 = new ArrayList<Object>();

									if (size1 < 1) {
										record.add("");
									} else {
										StringBuffer sb1 = new StringBuffer();
										temp1 = new ArrayList<Object>();
										temp1 = element.childNodesCopy();

										Iterator<?> iter1 = temp1.iterator();
										while (iter1.hasNext()) {
											sb1.append(iter1.next().toString());
										}

										record.add(sb1.toString());

									}

								}

							}
						}
					}

				}
				records.add(record);

			}
		}

		return records;

	}

	@Override
	public List<StagingUserRequest> getStagingAllByURL() {

		List<List<String>> input = loadTrToListAllByUrl();
		List<StagingUserRequest> temp = mapListToStaging(input);
		allStagingList = new ArrayList<StagingUserRequest>();

		for (Iterator<?> iterator = (Iterator<?>) temp.iterator(); iterator.hasNext();) {
			StagingUserRequest staging = (StagingUserRequest) iterator.next();

			allStagingList.add(staging);

		}
		logger.debug("All Incident url Daily list size: " + allStagingList.size());

		return allStagingList;
	}

	@Override
	public List getStagingAllByFile() {
		// TODO Auto-generated method stub
		return null;
	}

	public StagingUserRequest mapStaging(List<String> row) {

		StagingUserRequest ur = new StagingUserRequest();
		ur.setScalar_objectid(row.get(0));
		ur.setScalar_urequestid(row.get(0));
		ur.setScalar_date(row.get(0));
		ur.setScalar_datetime(row.get(0));
		ur.setScalar_user(row.get(0));
		ur.setScalar_objectclass(row.get(0));
		ur.setScalar_objectid2(row.get(0));
		ur.setScalar_attribute(row.get(0));
		ur.setScalar_previousvalue(row.get(0));
		ur.setScalar_newvalue(row.get(0));
		ur.setScalar_type(row.get(0));
		ur.setScalar_urequestref(row.get(0));
		ur.setUrequest_title(row.get(0));
		ur.setUrequest_startdate(row.get(0));
		ur.setUrequest_starttime(row.get(0));
		ur.setUrequest_enddate(row.get(0));
		ur.setUrequest_endtime(row.get(0));
		ur.setUrequest_assignmentdate(row.get(0));
		ur.setUrequest_assignmenttime(row.get(0));
		ur.setUrequest_lastupdate(row.get(0));
		ur.setUrequest_lastupdatetime(row.get(1));
		ur.setUrequest_closedate(row.get(2));
		ur.setUrequest_closedatetime(row.get(3));
		ur.setUrequest_caller(row.get(4));
		ur.setUrequest_agentname(row.get(5));
		ur.setUrequest_agent(row.get(6));
		ur.setUrequest_teamname(row.get(7));
		ur.setUrequest_team(row.get(8));

		String description = row.get(9);
		description = description.replaceAll(HTML_REGEX_CLEAR_TAG, "");
		description = description.replaceAll(HTML_ENTITY_CLEAR, " ");
		description = description.replaceAll(UNACCENT_CLEAR, "");
		if (description.length() > 4000) {
			description = description.substring(0, 4000);
		}
		ur.setUrequest_description(description);

		ur.setUrequest_status(row.get(10));
		ur.setUrequest_priority(row.get(11));
		ur.setUrequest_origin(row.get(12));
		ur.setUrequest_lastpendingdate(row.get(13));
		ur.setUrequest_lastpendingtime(row.get(14));
		ur.setUrequest_cumulatedpending(row.get(15));

		String pendingReason = row.get(16);
		pendingReason = pendingReason.replaceAll(HTML_REGEX_CLEAR_TAG, "");
		pendingReason = pendingReason.replaceAll(HTML_ENTITY_CLEAR, " ");
		pendingReason = pendingReason.replaceAll(UNACCENT_CLEAR, "");
		if (pendingReason.length() > 4000) {
			pendingReason = pendingReason.substring(0, 4000);
		}
		ur.setUrequest_pendingreason(pendingReason);

		ur.setUrequest_refproblem(row.get(17));
		ur.setUrequest_refchange(row.get(18));
		ur.setUrequest_organization(row.get(19));
		ur.setUrequest_organizationname(row.get(20));
		ur.setUrequest_slattopassed(row.get(21));
		ur.setUrequest_slattoover(row.get(22));
		ur.setUrequest_ttodeadline(row.get(23));
		ur.setUrequest_slattrpassed(row.get(24));
		ur.setUrequest_slattrover(row.get(25));
		ur.setUrequest_ttrdeadline(row.get(26));
		ur.setUrequest_resolutiondelay(row.get(27));

		String solution = row.get(28);
		solution = solution.replaceAll(HTML_REGEX_CLEAR_TAG, "");
		solution = solution.replaceAll(HTML_ENTITY_CLEAR, " ");
		solution = solution.replaceAll(UNACCENT_CLEAR, "");
		if (solution.length() > 4000) {
			solution = solution.substring(0, 4000);
		}
		ur.setUrequest_solution(solution);

		ur.setUrequest_tto(row.get(29));
		ur.setUrequest_ttr(row.get(30));
		ur.setPerson_fullname(row.get(31));
		ur.setPerson_organizationname(row.get(32));
		ur.setPerson_organization(row.get(33));
		ur.setUrequest_usersatisfaction(row.get(34));

		String userComment = row.get(35);
		userComment = userComment.replaceAll(HTML_REGEX_CLEAR_TAG, "");
		userComment = userComment.replaceAll(HTML_ENTITY_CLEAR, " ");
		userComment = userComment.replaceAll(UNACCENT_CLEAR, "");
		if (userComment.length() > 4000) {
			userComment = userComment.substring(0, 4000);
		}
		ur.setUrequest_usercomment(userComment);

		ur.setUrequest_servicename(row.get(36));

		return ur;
	}

	public List<StagingUserRequest> mapListToStaging(List<?> input) {
		List<StagingUserRequest> temp = new ArrayList<StagingUserRequest>();

		for (Iterator<ArrayList> iterator = (Iterator<ArrayList>) input.iterator(); iterator.hasNext();) {
			List<String> row = iterator.next();

			StagingUserRequest staging = new StagingUserRequest();
			staging = mapStaging(row);
			temp.add(staging);
		}

		return temp;
	}

}
