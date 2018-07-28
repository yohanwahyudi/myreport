package com.vdi.reports.djasper.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vdi.batch.mds.repository.dao.IncidentReportDAOService;
import com.vdi.batch.mds.repository.dao.PerfAgentDAOService;
import com.vdi.batch.mds.repository.dao.PerfAllDAOService;
import com.vdi.batch.mds.repository.dao.PerfTeamDAOService;
import com.vdi.model.Incident;
import com.vdi.model.performance.PerformanceAgent;
import com.vdi.model.performance.PerformanceOverall;
import com.vdi.model.performance.PerformanceTeam;
import com.vdi.reports.djasper.model.PerformanceReport;
import com.vdi.reports.djasper.service.ReportService;
import com.vdi.reports.djasper.templates.TemplateBuilders;
import com.vdi.reports.djasper.templates.TemplateStyles;
import com.vdi.tools.TimeStatic;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service("weeklySaIncidentReport")
public class WeeklySaIncidentReportImpl implements ReportService {

	@Autowired
	@Qualifier("weeklyPerfAllDAO")
	private PerfAllDAOService all;

	@Autowired
	@Qualifier("weeklyPerfTeamDAO")
	private PerfTeamDAOService team;

	@Autowired
	@Qualifier("weeklyPerfAgentDAO")
	private PerfAgentDAOService agent;
	
	@Autowired
	@Qualifier("incidentReportDAO")
	private IncidentReportDAOService incident;

	@Autowired
	private TemplateBuilders templateBuilders;

	@Autowired
	private TemplateStyles templateStyles;

	protected final static Map<String, Object> params = new HashMap<String, Object>();

	private List<PerformanceReport> reportList;

	private List<PerformanceOverall> performanceAllList;
	private List<PerformanceTeam> performanceTeamList;
	private List<PerformanceAgent> performanceAgentList;
	private List<Incident> supportAgentPendingList;
	private List<Incident> supportAgentAssignList;
	private List<Incident> supportAgentMissedList;
	private List<Incident> supportAgentIncidentList;

	private PerformanceReport report;
	private int currentMonth;
	private int currentWeek;

	public WeeklySaIncidentReportImpl() {

		currentMonth = TimeStatic.currentMonth;
		currentWeek = TimeStatic.currentWeekYear;

	}

	@Override
	public DynamicReport buildReport() {
		
		float achievement = reportList.get(0).getAchievement();
		
		// add params
		params.put("performanceAllList", reportList.get(0).getPerformanceAllList());
		params.put("performanceTeamList", reportList.get(0).getPerformanceTeamList());
		params.put("performanceAgentList", reportList.get(0).getPerformanceAgentList());
		params.put("supportAgentPendingList", reportList.get(0).getSupportAgentPendingList());
		params.put("supportAgentAssignList", reportList.get(0).getSupportAgentAssignList());
		params.put("supportAgentMissedList", reportList.get(0).getSupportAgentMissedList());
		params.put("supportAgentIncidentList", reportList.get(0).getSupportAgentIncidentList());

		// add subreport
		Style styleAchievement = new Style();
		Style missedTemplates = templateStyles.getStandardDetailRedTextStyle();
		Style achievedTemplates = templateStyles.getStandardDetailGreenTextStyle();

		if(achievement < 97) {
			styleAchievement = missedTemplates;
		} else {
			styleAchievement = achievedTemplates;
		}
		
		// subreport all
		DynamicReport subReportAll = templateBuilders.getSAAllSub();
		subReportAll.getColumns().get(3).setStyle(styleAchievement);
		// subreport team
		DynamicReport subReportTeam = templateBuilders.getSATeamSub();
		subReportTeam.getColumns().get(4).setStyle(styleAchievement);
		// subreport agent
		DynamicReport subReportAgent = templateBuilders.getSAAgentSub();
		subReportAgent.getColumns().get(7).setStyle(styleAchievement);
		//subreport assigned/pending/missed
		DynamicReport subReportMissed = templateBuilders.getSAMissedtSub();
		DynamicReport subReportPending = templateBuilders.getSAPendingSub();
		DynamicReport subReportAssigned = templateBuilders.getSAAssignedSub();
		//subreport all incident
		DynamicReport subReportIncident = templateBuilders.getSAIncidentSub();
		
		DynamicReportBuilder drb = templateBuilders.getSAMaster();
		drb.addConcatenatedReport(subReportAll, new ClassicLayoutManager(), "performanceAllList",
				DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, false)
			.addConcatenatedReport(subReportTeam, new ClassicLayoutManager(), "performanceTeamList",
				DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, false)
			.addConcatenatedReport(subReportAgent, new ClassicLayoutManager(), "performanceAgentList",
				DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, true)
			.addConcatenatedReport(subReportMissed, new ClassicLayoutManager(), "supportAgentMissedList",
					DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, true)
			.addConcatenatedReport(subReportPending, new ClassicLayoutManager(), "supportAgentPendingList",
					DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, false)
			.addConcatenatedReport(subReportAssigned, new ClassicLayoutManager(), "supportAgentAssignList",
					DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, false)
			.addConcatenatedReport(subReportIncident, new ClassicLayoutManager(), "supportAgentIncidentList",
					DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, true);


		return drb.build();
	}

	@Override
	public JasperPrint getReport() throws JRException, Exception {

		JRDataSource ds = getDataSource();
		JasperReport jr = DynamicJasperHelper.generateJasperReport(buildReport(), new ClassicLayoutManager(), params);
		JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);

		return jp;
	}

	@Override
	public JRDataSource getDataSource() {

		setPerformanceReport();

		return new JRBeanCollectionDataSource(getPerformanceReport());

	}

	public List<PerformanceReport> getPerformanceReport() {

		return reportList;
	}

	public void setPerformanceReport() {

		report = new PerformanceReport();
		performanceAllList = new ArrayList<PerformanceOverall>();
		performanceAllList.add(all.getPerformance(currentWeek, currentMonth));
		performanceTeamList = team.getPerformance(currentWeek, currentMonth);
		performanceAgentList = agent.getPerformance(currentWeek, currentMonth);
		int totalTicket = all.getPerformance(currentWeek, currentMonth).getTotalTicket();
		int totalAchieved = all.getPerformance(currentWeek, currentMonth).getTotalAchieved();
		int totalMissed = all.getPerformance(currentWeek, currentMonth).getTotalMissed();
		float achievement = all.getPerformance(currentWeek, currentMonth).getAchievement();
		
		supportAgentPendingList = incident.getPendingIncidentByWeek(currentMonth, currentWeek-1);
		supportAgentAssignList = incident.getAssignedIncidentByWeek(currentMonth, currentWeek-1);
		supportAgentMissedList = incident.getMissedIncidentByWeek(currentMonth, currentWeek-1);
		supportAgentIncidentList = incident.getAllIncidentByWeek(currentMonth, currentWeek-1);

		report.setTotalTicket(totalTicket);
		report.setTotalAchieved(totalAchieved);
		report.setTotalMissed(totalMissed);
		report.setAchievement(achievement);
		report.setPerformanceAllList(performanceAllList);
		report.setPerformanceTeamList(performanceTeamList);
		report.setPerformanceAgentList(performanceAgentList);
		report.setSupportAgentPendingList(supportAgentPendingList);
		report.setSupportAgentAssignList(supportAgentAssignList);
		report.setSupportAgentMissedList(supportAgentMissedList);
		report.setSupportAgentIncidentList(supportAgentIncidentList);

		System.out.println("assigned: "+supportAgentAssignList);

		List<PerformanceReport> list = new ArrayList<PerformanceReport>();
		list.add(report);

		this.reportList = list;
	}

}
