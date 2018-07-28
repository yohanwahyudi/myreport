package com.vdi.reports.djasper.model;

import java.util.List;

import com.vdi.model.Incident;
import com.vdi.model.ServiceDesk;
import com.vdi.model.performance.PerformanceAgent;
import com.vdi.model.performance.PerformanceOverall;
import com.vdi.model.performance.PerformanceTeam;

public class PerformanceReport {
	
	private int totalTicket;
	private int totalAchieved;
	private int totalMissed;
	private float achievement; 
	
	private List<PerformanceOverall> performanceAllList;
	private List<SummaryReport> summaryReport;
	private List<PerformanceTeam> performanceTeamList;
	private List<PerformanceAgent> performanceAgentList;
	
	private List<Incident> supportAgentIncidentList;
	private List<Incident> supportAgentPendingList;
	private List<Incident> supportAgentAssignList;
	private List<Incident> supportAgentMissedList;
	
	private List<ServiceDesk> serviceDeskIncidentList;

	public PerformanceReport() {
		
	}
	
//	public PerformanceReport(List<PerformanceOverall> performanceAllList, List<PerformanceTeam> performanceTeamList, 
//			List<PerformanceAgent> performanceAgentList) {
//		this.performanceAllList = performanceAllList;
//		this.performanceTeamList = performanceTeamList;
//		this.performanceAgentList = performanceAgentList;
//	}
//	
//	public PerformanceReport(List<PerformanceOverall> performanceAllList, List<PerformanceTeam> performanceTeamList, 
//			List<PerformanceAgent> performanceAgentList, List<Incident> supportAgentPendingList, List<Incident> supportAgentAssignList,
//			List<Incident> supportAgentMissedList, List<Incident> supportAgentIncidentList) {
//		this.performanceAllList = performanceAllList;
//		this.performanceTeamList = performanceTeamList;
//		this.performanceAgentList = performanceAgentList;
//		this.supportAgentPendingList = supportAgentPendingList;
//		this.supportAgentAssignList = supportAgentAssignList;
//		this.supportAgentMissedList = supportAgentMissedList;
//		this.supportAgentIncidentList = supportAgentIncidentList;
//	}
//	
	public List<PerformanceOverall> getPerformanceAllList() {
		return performanceAllList;
	}

	public void setPerformanceAllList(List<PerformanceOverall> performanceAllList) {
		
		this.performanceAllList = performanceAllList;
	}

	public List<PerformanceTeam> getPerformanceTeamList() {
		return performanceTeamList;
	}

	public void setPerformanceTeamList(List<PerformanceTeam> performanceTeamList) {
		this.performanceTeamList = performanceTeamList;
	}

	public List<PerformanceAgent> getPerformanceAgentList() {
		return performanceAgentList;
	}

	public void setPerformanceAgentList(List<PerformanceAgent> performanceAgentList) {
		this.performanceAgentList = performanceAgentList;
	}

	public List<Incident> getSupportAgentIncidentList() {
		return supportAgentIncidentList;
	}

	public void setSupportAgentIncidentList(List<Incident> supportAgentIncidentList) {
		this.supportAgentIncidentList = supportAgentIncidentList;
	}

	public List<Incident> getSupportAgentPendingList() {
		return supportAgentPendingList;
	}

	public void setSupportAgentPendingList(List<Incident> supportAgentPendingList) {
		this.supportAgentPendingList = supportAgentPendingList;
	}

	public List<Incident> getSupportAgentAssignList() {
		return supportAgentAssignList;
	}

	public void setSupportAgentAssignList(List<Incident> supportAgentAssignList) {
		this.supportAgentAssignList = supportAgentAssignList;
	}

	public List<Incident> getSupportAgentMissedList() {
		return supportAgentMissedList;
	}

	public void setSupportAgentMissedList(List<Incident> supportAgentMissedList) {
		this.supportAgentMissedList = supportAgentMissedList;
	}

	public List<ServiceDesk> getServiceDeskIncidentList() {
		return serviceDeskIncidentList;
	}

	public void setServiceDeskIncidentList(List<ServiceDesk> serviceDeskIncidentList) {
		this.serviceDeskIncidentList = serviceDeskIncidentList;
	}

	public int getTotalTicket() {
		return totalTicket;
	}

	public void setTotalTicket(int totalTicket) {
		this.totalTicket = totalTicket;
	}

	public int getTotalAchieved() {
		return totalAchieved;
	}

	public void setTotalAchieved(int totalAchieved) {
		this.totalAchieved = totalAchieved;
	}

	public int getTotalMissed() {
		return totalMissed;
	}

	public List<SummaryReport> getSummaryReport() {
		return summaryReport;
	}

	public void setSummaryReport(List<SummaryReport> summaryReport) {
		this.summaryReport = summaryReport;
	}

	public void setTotalMissed(int totalMissed) {
		this.totalMissed = totalMissed;
	}

	public float getAchievement() {
		return achievement;
	}

	public void setAchievement(float achievement) {
		this.achievement = achievement;
	}
	
	
	

}
