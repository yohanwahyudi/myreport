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
import com.vdi.reports.djasper.model.SummaryReport;
import com.vdi.reports.djasper.service.ReportService;
import com.vdi.reports.djasper.templates.TemplateBuilders;
import com.vdi.reports.djasper.templates.TemplateBuildersReport;
import com.vdi.reports.djasper.templates.TemplateStyles;
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

@Service("monthlySaIncidentReport")
public class MonthlySaIncidentReport implements ReportService {

	@Autowired
	@Qualifier("monthlyPerfAllDAO")
	private PerfAllDAOService all;

	@Autowired
	@Qualifier("monthlyPerfTeamDAO")
	private PerfTeamDAOService team;

	@Autowired
	@Qualifier("monthlyPerfAgentDAO")
	private PerfAgentDAOService agent;

	@Autowired
	@Qualifier("incidentReportDAO")
	private IncidentReportDAOService incident;

	@Autowired
	private TemplateBuildersReport templateBuilders;

	@Autowired
	private TemplateStyles templateStyles;

	protected final static Map<String, Object> params = new HashMap<String, Object>();

	private List<PerformanceReport> reportList;

	private List<PerformanceOverall> performanceAllList;
	private List<SummaryReport> summaryList;
	
	private List<PerformanceTeam> performanceTeamList;
	private List<PerformanceAgent> performanceAgentList;
	private List<Incident> supportAgentPendingList;
	private List<Incident> supportAgentAssignList;
	private List<Incident> supportAgentMissedList;
	private List<Incident> supportAgentIncidentList;

	private PerformanceReport report;
	private int currentMonth;
	private int currentWeek;
	private int prevMonth;

	public MonthlySaIncidentReport() {
		this.currentMonth = TimeStatic.currentMonth;
		this.currentWeek = TimeStatic.currentWeekYear;
		this.prevMonth = currentMonth - 1;
	}

	@Override
	public DynamicReport buildReport() {

		//get master
		DynamicReportBuilder master = templateBuilders.getMaster();
		
		// add params
		params.put("summaryReport", reportList.get(0).getSummaryReport());
		params.put("teamReport", reportList.get(0).getPerformanceTeamList());
		params.put("agentReport", reportList.get(0).getPerformanceAgentList());
		
		// add subreport
		
		// all subreport
		DynamicReport subReportAll = templateBuilders.getAllSub2();
		DynamicReport subReportTeam = templateBuilders.getTeamSub();
		DynamicReport subReportAgent = templateBuilders.getAgentSub();
		
		master.addConcatenatedReport(subReportAll, new ClassicLayoutManager(), "summaryReport",
				DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, false);
		master.addConcatenatedReport(subReportTeam, new ClassicLayoutManager(), "teamReport",
				DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, false);
		master.addConcatenatedReport(subReportAgent, new ClassicLayoutManager(), "agentReport",
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

	public void setPerformanceReport() {

		report = new PerformanceReport();
		
		//performance
		summaryList = new ArrayList<SummaryReport>();
		PerformanceOverall perfAll = all.getPerformance();
		Integer totalTicket = perfAll.getTotalTicket();
		Integer totalAchieved = perfAll.getTotalAchieved();
		Integer totalMissed = perfAll.getTotalMissed();
		Float achievement = perfAll.getAchievement();		
		summaryList.add(new SummaryReport("Total Ticket", totalTicket.toString()));
		summaryList.add(new SummaryReport("Achieved", totalAchieved.toString()));
		summaryList.add(new SummaryReport("Missed", totalMissed.toString()));
		summaryList.add(new SummaryReport("Achievement", achievement.toString()));
		
		performanceTeamList = team.getPerformance();
		performanceAgentList = agent.getPerformance();
		
		//incident
		
		
		//setreport
		report.setSummaryReport(summaryList);
		report.setPerformanceTeamList(performanceTeamList);
		report.setPerformanceAgentList(performanceAgentList);
		
		System.out.println("agent: "+performanceAgentList);

		List<PerformanceReport> list = new ArrayList<PerformanceReport>();
		list.add(report);

		this.reportList = list;
	}

	@Override
	public List<PerformanceReport> getPerformanceReport() {
		return reportList;
	}

}
