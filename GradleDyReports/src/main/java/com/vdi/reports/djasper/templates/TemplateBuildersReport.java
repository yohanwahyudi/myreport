package com.vdi.reports.djasper.templates;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;

@Configuration
public class TemplateBuildersReport {

	private DynamicReportBuilder master;

	//for support agent report
	private DynamicReport summarySub;
	private DynamicReport summarySub2;
	private DynamicReport teamSub;
	private DynamicReport agentSub;
	private DynamicReport missedSub;
	private DynamicReport pendingSub;
	private DynamicReport assignedSub;
	private DynamicReport incidentListSub;

	public TemplateBuildersReport() {
	}

	@Autowired
	public TemplateBuildersReport(TemplateStylesReport style) throws ColumnBuilderException, ClassNotFoundException {

		this.master = createMaster(style);

		this.summarySub = createSummarySub(style);
		this.summarySub2 = createSummarySub2(style);
		this.teamSub = createTeamSub(style);
		this.agentSub = createAgentSub(style);
		this.missedSub = createMissedSub(style);
		this.pendingSub = createPendingSub(style);
		this.assignedSub = createAssignedSub(style);
		this.incidentListSub = createIncidentListSub(style);

	}

	private DynamicReportBuilder createMaster(TemplateStylesReport style) {

		DynamicReportBuilder drb = new DynamicReportBuilder();
		Integer margin = 20;
		drb.setTitleStyle(style.getArialTitleAgentStyle())
				.setTitle("VDI SUPPORT AGENT PERFORMANCE BASED ON iTop").setTitleHeight(60)
				.setSubtitle("July 2018").setSubtitleStyle(style.getArialTitleAgentStyle()).setSubtitleHeight(15)
//				.setDetailHeight(15)
				.setLeftMargin(margin).setRightMargin(margin).setTopMargin(20).setBottomMargin(margin)
//				.setPrintBackgroundOnOddRows(true).setOddRowBackgroundStyle(style.getOddRowStyle())
				.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT);
		return drb;
	}

	private DynamicReport createSummarySub(TemplateStylesReport style) {

		AbstractColumn columnTotalTicket = ColumnBuilder.getNew()
				.setColumnProperty("totalTicket", Integer.class.getName()).setTitle("Ticket Total")
				.setWidth(new Integer(45)).setStyle(style.getDetailStyle())
				.setHeaderStyle(style.getDejavuSansTitleStyle()).build();

		AbstractColumn columnAchievedTicket = ColumnBuilder.getNew()
				.setColumnProperty("totalAchieved", Integer.class.getName()).setTitle("Achieved")
				.setWidth(new Integer(45)).setStyle(style.getDetailStyle())
				.setHeaderStyle(style.getDejavuSansTitleStyle()).build();

		AbstractColumn columnMissedTicket = ColumnBuilder.getNew()
				.setColumnProperty("totalMissed", Integer.class.getName()).setTitle("Missed").setWidth(new Integer(45))
				.setStyle(style.getDetailStyle()).setHeaderStyle(style.getDejavuSansTitleStyle()).build();

		AbstractColumn columnAchievement = ColumnBuilder.getNew()
				.setColumnProperty("achievement", Float.class.getName()).setTitle("Achievement")
				.setWidth(new Integer(45)).setStyle(style.getDetailStyle())
				.setHeaderStyle(style.getDejavuSansTitleStyle()).build();

		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.addColumn(columnTotalTicket);
		drb.addColumn(columnAchievedTicket);
		drb.addColumn(columnMissedTicket);
		drb.addColumn(columnAchievement);
		drb.setUseFullPageWidth(true);

		return drb.build();

	}

	private DynamicReport createSummarySub2(TemplateStylesReport style) {

		AbstractColumn columnName = ColumnBuilder.getNew().setColumnProperty("name", String.class.getName())
				.setTitle("Achievement").setWidth(new Integer(100)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle())
				.build();

		AbstractColumn columnValue = ColumnBuilder.getNew().setColumnProperty("value", String.class.getName())
				.setTitle("").setWidth(new Integer(100)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();

		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.addColumn(columnName);
		drb.addColumn(columnValue);
//		drb.setUseFullPageWidth(true);

		return drb.build();
	}
	
	private DynamicReport createTeamSub(TemplateStylesReport style) {

		AbstractColumn columnTeamName = ColumnBuilder.getNew().setColumnProperty("teamName", String.class.getName())
				.setTitle("Team").setWidth(new Integer(80)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle())
				.build();
		AbstractColumn columnTotalTicket = ColumnBuilder.getNew().setColumnProperty("totalTicket", Integer.class.getName())
				.setTitle("Total Ticket").setWidth(new Integer(52)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnTotalAchieved = ColumnBuilder.getNew().setColumnProperty("totalAchieved", Integer.class.getName())
				.setTitle("Achieved Ticket").setWidth(new Integer(52)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnTotalMissed = ColumnBuilder.getNew().setColumnProperty("totalMissed", Integer.class.getName())
				.setTitle("Missed Ticket").setWidth(new Integer(52)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnAchievement = ColumnBuilder.getNew().setColumnProperty("achievement", Float.class.getName())
				.setTitle("Achievement").setWidth(new Integer(80)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();

		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.addColumn(columnTeamName);		
		drb.addColumn(columnTotalAchieved);
		drb.addColumn(columnTotalMissed);
		drb.addColumn(columnTotalTicket);
		drb.addColumn(columnAchievement);
//		drb.setUseFullPageWidth(true);
		
		return drb.build();		
	}

	private DynamicReport createAgentSub(TemplateStylesReport style) {
		
		AbstractColumn columnTeamName = ColumnBuilder.getNew().setColumnProperty("division", String.class.getName())
				.setTitle("Team").setWidth(new Integer(70)).setStyle(style.getArialLeftAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle())
				.build();
		AbstractColumn columnAgent = ColumnBuilder.getNew().setColumnProperty("agentName", String.class.getName())
				.setTitle("Agent").setWidth(new Integer(150)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnTotalAssigned = ColumnBuilder.getNew().setColumnProperty("totalAssigned", Integer.class.getName())
				.setTitle("Assigned").setWidth(new Integer(50)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnTotalPending = ColumnBuilder.getNew().setColumnProperty("totalPending", Integer.class.getName())
				.setTitle("Pending").setWidth(new Integer(50)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnTotalAchieved = ColumnBuilder.getNew().setColumnProperty("totalAchieved", Integer.class.getName())
				.setTitle("Achieved").setWidth(new Integer(50)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnTotalMissed = ColumnBuilder.getNew().setColumnProperty("totalMissed", Integer.class.getName())
				.setTitle("Missed").setWidth(new Integer(50)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnTotal = ColumnBuilder.getNew().setColumnProperty("totalTicket", Integer.class.getName())
				.setTitle("Total").setWidth(new Integer(50)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnAchievement = ColumnBuilder.getNew().setColumnProperty("achievement", Float.class.getName())
				.setTitle("Achievement").setWidth(new Integer(70)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();

		Style groupVariables = new Style("groupVariables");
		groupVariables.setFont(Font.ARIAL_MEDIUM_BOLD);
		groupVariables.setTextColor(Color.BLUE);
//		groupVariables.setBorderBottom(Border.THIN());
		groupVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
		groupVariables.setVerticalAlign(VerticalAlign.BOTTOM);
		
		GroupBuilder gb1 = new GroupBuilder();
		DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) columnTeamName).build();
		
		int margin=20;
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.setTitleStyle(style.getArialTitleAgentStyle()).setTitleHeight(20);
		drb.setLeftMargin(margin).setRightMargin(margin).setTopMargin(20).setBottomMargin(margin);
		drb.setTitle("VDI PERFORMANCE AGENT");
		drb.addColumn(columnTeamName);	
		drb.addColumn(columnAgent);
		drb.addColumn(columnTotalAssigned);	
		drb.addColumn(columnTotalPending);	
		drb.addColumn(columnTotalAchieved);
		drb.addColumn(columnTotalMissed);
		drb.addColumn(columnTotal);
		drb.addColumn(columnAchievement);
		drb.addGroup(g1);		
		
		return drb.build();
		
	}
	
	private DynamicReport createMissedSub(TemplateStylesReport style) {
		
		AbstractColumn columnRef = ColumnBuilder.getNew().setColumnProperty("ref", String.class.getName())
				.setTitle("Ref").setWidth(new Integer(45)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle())
				.build();
		AbstractColumn columnTitle = ColumnBuilder.getNew().setColumnProperty("title", String.class.getName())
				.setTitle("Title").setWidth(new Integer(150)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnAgent = ColumnBuilder.getNew().setColumnProperty("agent_fullname", String.class.getName())
				.setTitle("Agent").setWidth(new Integer(150)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnStartDt = ColumnBuilder.getNew().setColumnProperty("start_date", String.class.getName())
				.setTitle("Start Date").setWidth(new Integer(50)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnCloseDt = ColumnBuilder.getNew().setColumnProperty("close_date", String.class.getName())
				.setTitle("Close Date").setWidth(new Integer(50)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnStatus = ColumnBuilder.getNew().setColumnProperty("status", String.class.getName())
				.setTitle("Status").setWidth(new Integer(50)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.setTitleStyle(style.getArialTitleAgentStyle()).setTitleHeight(20);
		drb.setTitle("MISSED TICKET");
		drb.addColumn(columnRef);	
		drb.addColumn(columnTitle);
		drb.addColumn(columnAgent);	
		drb.addColumn(columnStartDt);	
		drb.addColumn(columnCloseDt);
		drb.addColumn(columnStatus);
		drb.setUseFullPageWidth(true);
		
		return drb.build();
		
	}
	
	private DynamicReport createPendingSub(TemplateStylesReport style) {
		AbstractColumn columnRef = ColumnBuilder.getNew().setColumnProperty("ref", String.class.getName())
				.setTitle("Ref").setWidth(new Integer(45)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle())
				.build();
		AbstractColumn columnTitle = ColumnBuilder.getNew().setColumnProperty("title", String.class.getName())
				.setTitle("Title").setWidth(new Integer(150)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnAgent = ColumnBuilder.getNew().setColumnProperty("agent_fullname", String.class.getName())
				.setTitle("Agent").setWidth(new Integer(150)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnStartDt = ColumnBuilder.getNew().setColumnProperty("start_date", String.class.getName())
				.setTitle("Start Date").setWidth(new Integer(50)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnPendingReason = ColumnBuilder.getNew().setColumnProperty("pending_reason", String.class.getName())
				.setTitle("Pending Reason").setWidth(new Integer(200)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.setTitleStyle(style.getArialTitleAgentStyle()).setTitleHeight(20);
		drb.setTitle("PENDING TICKET");
		drb.addColumn(columnRef);	
		drb.addColumn(columnTitle);
		drb.addColumn(columnAgent);	
		drb.addColumn(columnStartDt);	
		drb.addColumn(columnPendingReason);
		drb.setUseFullPageWidth(true);
		
		return drb.build();
	}
	
	private DynamicReport createAssignedSub(TemplateStylesReport style) {
		
		AbstractColumn columnRef = ColumnBuilder.getNew().setColumnProperty("ref", String.class.getName())
				.setTitle("Ref").setWidth(new Integer(45)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle())
				.build();
		AbstractColumn columnTitle = ColumnBuilder.getNew().setColumnProperty("title", String.class.getName())
				.setTitle("Title").setWidth(new Integer(150)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnAgent = ColumnBuilder.getNew().setColumnProperty("agent_fullname", String.class.getName())
				.setTitle("Agent").setWidth(new Integer(150)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnStartDt = ColumnBuilder.getNew().setColumnProperty("start_date", String.class.getName())
				.setTitle("Start Date").setWidth(new Integer(50)).setStyle(style.getArialDetailAgentStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.setTitleStyle(style.getArialTitleAgentStyle()).setTitleHeight(20);
		drb.setTitle("ASSIGNED TICKET");
		drb.addColumn(columnRef);	
		drb.addColumn(columnTitle);
		drb.addColumn(columnAgent);	
		drb.addColumn(columnStartDt);	
		drb.setUseFullPageWidth(true);
		
		return drb.build();
	}
	
	private DynamicReport createIncidentListSub(TemplateStylesReport style) {
		
		AbstractColumn columnRef = ColumnBuilder.getNew().setColumnProperty("ref", String.class.getName())
				.setTitle("Ref").setWidth(new Integer(35)).setStyle(style.getArialDetailIncidentListStyle())
				.setHeaderStyle(style.getArialHeaderIncidentListStyle())
				.build();
		AbstractColumn columnTitle= ColumnBuilder.getNew().setColumnProperty("title", String.class.getName())
				.setTitle("Title").setWidth(new Integer(200)).setStyle(style.getArialDetailIncidentListStyle())
				.setHeaderStyle(style.getArialHeaderIncidentListStyle())
				.build();
		AbstractColumn columnStatus= ColumnBuilder.getNew().setColumnProperty("status", String.class.getName())
				.setTitle("Status").setWidth(new Integer(30)).setStyle(style.getArialDetailIncidentListStyle())
				.setHeaderStyle(style.getArialHeaderIncidentListStyle())
				.build();
		AbstractColumn columnAgent= ColumnBuilder.getNew().setColumnProperty("agent_fullname", String.class.getName())
				.setTitle("Agent").setWidth(new Integer(150)).setStyle(style.getArialDetailIncidentListStyle())
				.setHeaderStyle(style.getArialHeaderIncidentListStyle())
				.build();
		AbstractColumn columnPriority= ColumnBuilder.getNew().setColumnProperty("priority", String.class.getName())
				.setTitle("Priority").setWidth(new Integer(50)).setStyle(style.getArialDetailIncidentListStyle())
				.setHeaderStyle(style.getArialHeaderIncidentListStyle())
				.build();
		AbstractColumn columnStartDt= ColumnBuilder.getNew().setColumnProperty("start_date", String.class.getName())
				.setTitle("Start Date").setWidth(new Integer(50)).setStyle(style.getArialDetailIncidentListStyle())
				.setHeaderStyle(style.getArialHeaderIncidentListStyle())
				.build();
		AbstractColumn columnResolutionDt= ColumnBuilder.getNew().setColumnProperty("resolution_date", String.class.getName())
				.setTitle("Resolution Date").setWidth(new Integer(50)).setStyle(style.getArialDetailIncidentListStyle())
				.setHeaderStyle(style.getArialHeaderIncidentListStyle())
				.build();
				
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.setTitle("INCIDENT TICKET");
		drb.setTitleStyle(style.getArialTitleAgentStyle()).setTitleHeight(30);
		drb.addColumn(columnRef);	
		drb.addColumn(columnTitle);
		drb.addColumn(columnStatus);
		drb.addColumn(columnAgent);	
		drb.addColumn(columnPriority);	
		drb.addColumn(columnStartDt);
		drb.addColumn(columnResolutionDt);	
		drb.setUseFullPageWidth(true);
		
		return drb.build();
				
	}

	
	public DynamicReportBuilder getMaster() {
		return master;
	}

	public DynamicReport getSummarySub() {
		return summarySub;
	}

	public DynamicReport getSummarySub2() {
		return summarySub2;
	}

	public DynamicReport getTeamSub() {
		return teamSub;
	}

	public DynamicReport getAgentSub() {
		return agentSub;
	}

	public DynamicReport getMissedSub() {
		return missedSub;
	}

	public DynamicReport getPendingSub() {
		return pendingSub;
	}

	public DynamicReport getAssignedSub() {
		return assignedSub;
	}

	public DynamicReport getIncidentListSub() {
		return incidentListSub;
	}

}
