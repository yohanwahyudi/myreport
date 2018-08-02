package com.vdi.reports.djasper.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import com.vdi.batch.mds.repository.dao.PerfAgentDAOService;
import com.vdi.batch.mds.repository.dao.PerfAllDAOService;
import com.vdi.batch.mds.repository.dao.ServiceDeskReportDAO;
import com.vdi.configuration.AppConfig;
import com.vdi.model.performance.PerformanceAgent;
import com.vdi.model.performance.PerformanceOverall;
import com.vdi.model.staging.StagingServiceDesk;
import com.vdi.reports.djasper.model.PerformanceReport;
import com.vdi.reports.djasper.model.SummaryReport;
import com.vdi.reports.djasper.service.ReportService;
import com.vdi.reports.djasper.templates.TemplateBuildersReport;
import com.vdi.reports.djasper.templates.TemplateStylesReport;
import com.vdi.tools.TimeStatic;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Configuration("monthlyIncidentSDReport")
public class MonthlyIncidentSDReportImpl implements ReportService {

	@Autowired
	@Qualifier("monthlySDPerfAllDAO")
	private PerfAllDAOService all;

	@Autowired
	@Qualifier("monthlySDPerfAgentDAO")
	private PerfAgentDAOService agent;
	
	@Autowired
	@Qualifier("serviceDeskReportDAO")
	private ServiceDeskReportDAO sdReport;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private TemplateBuildersReport templateBuilders;
	
	@Autowired
	private TemplateStylesReport templateStyles;

	private int currentYear;
	private int currentMonth;
	private int previousMonth;
	private String prevMonthStr;

	private PerformanceReport report;

	protected final Map<String, Object> params = new HashMap<String, Object>();

	private List<PerformanceReport> reportList;
	private List<SummaryReport> summaryList;
	private List<PerformanceAgent> performanceAgentList;
	private List<StagingServiceDesk> serviceDeskIncidentList; 

	public MonthlyIncidentSDReportImpl() {

		this.currentYear = TimeStatic.currentYear;
		this.currentMonth = TimeStatic.currentMonth;
		this.previousMonth = currentMonth-1;
		this.prevMonthStr = TimeStatic.prevMonthStr;

	}

	@Override
	public DynamicReport buildReport() {
		//get serviceDeskPersons
		List<PerformanceReport> serviceDeskPersonsList = new ArrayList<PerformanceReport>();
		serviceDeskPersonsList = getServiceDeskPersons();
		
		// get master report
		DynamicReportBuilder master = templateBuilders.getMaster();
		master.setTitle("VDI SERVICE DESK PERFORMANCE BASED ON iTop");
		master.setSubtitle(prevMonthStr.toUpperCase() + " " + currentYear);

		// set params
		params.put("summaryReport", reportList.get(0).getSummaryReport());
		params.put("performanceAgentList", reportList.get(0).getPerformanceAgentList());
		params.put("servicedeskIncidentList", reportList.get(0).getServiceDeskIncidentList());

		// add subreports
		DynamicReport subReportAll = templateBuilders.getSummarySub2();
		DynamicReport subReportPerson = templateBuilders.getServiceDeskPersonSub();
			subReportPerson.getColumns().get(4).setStyle(templateStyles.getArialDetailGreenAgentStyle());
		DynamicReport subReportAgent = templateBuilders.getServiceDeskAgentSub();
		DynamicReport subReportIncident = templateBuilders.getServiceDeskIncidentSub();

		master.addConcatenatedReport(subReportAll, new ClassicLayoutManager(), "summaryReport",
				DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, false);
		
		for(int i=0; i<serviceDeskPersonsList.size(); i++) {
			
			List<PerformanceAgent> perfAgent = new ArrayList<PerformanceAgent>();
			perfAgent = serviceDeskPersonsList.get(i).getPerformanceAgentList();
			
			params.put("personSub"+i, perfAgent); 			
			master.addConcatenatedReport(subReportPerson, new ClassicLayoutManager(), "personSub"+i,
					DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, false);
		}
		
		master.addConcatenatedReport(subReportAgent, new ClassicLayoutManager(), "performanceAgentList",
				DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, true);
		master.addConcatenatedReport(subReportIncident, new ClassicLayoutManager(), "servicedeskIncidentList",
				DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, true);

		return master.build();
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

	private void setPerformanceReport() {

		report = new PerformanceReport();

		summaryList = new ArrayList<SummaryReport>();
		PerformanceOverall perfAll = all.getPerformance();
		Integer totalTicket = perfAll.getTotalTicket();
		Integer totalAchieved = perfAll.getTotalAchieved();
		Integer totalMissed = perfAll.getTotalMissed();
		Float achievement = perfAll.getAchievement();
		summaryList.add(new SummaryReport("Ticket Achieved", totalAchieved.toString()));
		summaryList.add(new SummaryReport("Ticket Missed", totalMissed.toString()));
		summaryList.add(new SummaryReport("Ticket Total", totalTicket.toString()));
		summaryList.add(new SummaryReport("Achievement", achievement.toString()));

		performanceAgentList = agent.getPerformance();
		serviceDeskIncidentList = sdReport.getAllIncidentByMonth(previousMonth);

		report.setTotalTicket(totalTicket);
		report.setTotalAchieved(totalAchieved);
		report.setTotalMissed(totalMissed);
		report.setAchievement(achievement);
		report.setSummaryReport(summaryList);
		report.setPerformanceAgentList(performanceAgentList);
		report.setServiceDeskIncidentList(serviceDeskIncidentList);

		List<PerformanceReport> list = new ArrayList<PerformanceReport>();
		list.add(report);

		this.reportList = list;

		System.out.println(reportList.get(0).getSummaryReport());
		System.out.println("summaryList: " + summaryList);
		System.out.println("agentList: " + reportList.get(0).getPerformanceAgentList());
		System.out.println("size: "+serviceDeskIncidentList.size());
	}

	@Override
	public List<PerformanceReport> getPerformanceReport() {

		return reportList;
	}

	public List<PerformanceReport> getServiceDeskPersons() {

		List<PerformanceAgent> performanceReport = reportList.get(0).getPerformanceAgentList();
		String[] personArray = appConfig.getServicedeskPerson();

		List<PerformanceReport> personReport = new ArrayList<PerformanceReport>();

		int tempTotal = 0;
		int tempMissed = 0;
		int tempAchieved = 0;		
		for (String person : personArray) {
			for (PerformanceAgent agent : performanceReport) {
				if (person.trim().equalsIgnoreCase(agent.getAgentName().trim())) {
					PerformanceReport pr = new PerformanceReport();
					
					List<PerformanceAgent> temp = new ArrayList<PerformanceAgent>();
					temp.add(agent);
					
					tempTotal = tempTotal + agent.getTotalTicket();
					tempMissed = tempMissed + agent.getTotalMissed();
					tempAchieved = tempAchieved + agent.getTotalAchieved();
					
					pr.setPerformanceAgentList(temp);

					personReport.add(pr);
					
					break;
				} 
			}
		}
		
		//get dcu ticket count
		int otherDCUTotalTicket = report.getTotalTicket() - tempTotal;
		int otherDCUTotalAchieved = report.getTotalAchieved() - tempAchieved;
		int otherDCUTotalMissed = report.getTotalMissed() - tempMissed;
		
		
		BigDecimal achievement = new BigDecimal(0);
		achievement = (new BigDecimal(otherDCUTotalAchieved)).divide((new BigDecimal(otherDCUTotalTicket)), 4, BigDecimal.ROUND_HALF_UP);
		achievement = achievement.multiply(new BigDecimal(100));
		
		PerformanceAgent otherOrDCU = new PerformanceAgent();
		otherOrDCU.setAgentName(personArray[2]);
		otherOrDCU.setTotalTicket(otherDCUTotalTicket);
		otherOrDCU.setTotalAchieved(otherDCUTotalAchieved);
		otherOrDCU.setTotalMissed(otherDCUTotalMissed);
		otherOrDCU.setAchievement(achievement.floatValue());		

		List<PerformanceAgent> temp = new ArrayList<PerformanceAgent>();
		temp.add(otherOrDCU);
		
		PerformanceReport pr = new PerformanceReport();
		pr.setPerformanceAgentList(temp);
		
		personReport.add(pr);
		
		return personReport;
	}

}
