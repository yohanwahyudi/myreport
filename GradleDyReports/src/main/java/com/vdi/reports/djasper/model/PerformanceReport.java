package com.vdi.reports.djasper.model;

import java.util.List;

import com.vdi.model.Incident;
import com.vdi.model.ServiceDesk;
import com.vdi.model.performance.PerformanceAgent;
import com.vdi.model.performance.PerformanceOverall;
import com.vdi.model.performance.PerformanceTeam;

public class PerformanceReport {
	
	private List<PerformanceOverall> performanceAllList;
	private List<PerformanceTeam> performanceTeamList;
	private List<PerformanceAgent> performanceAgentList;
	
	private List<Incident> supportAgentIncidentList;
	private List<Incident> supportAgentPendingList;
	private List<Incident> supportAgentAssignList;
	private List<Incident> supportAgentMissedList;
	
	private List<ServiceDesk> serviceDeskIncidentList;

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
	
	
	

}
