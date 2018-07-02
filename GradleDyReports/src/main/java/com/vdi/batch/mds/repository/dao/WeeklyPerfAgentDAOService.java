package com.vdi.batch.mds.repository.dao;

import java.util.List;

import com.vdi.model.performance.PerformanceAgent;

public interface WeeklyPerfAgentDAOService {
	
	public List<Object[]> getAgentTicket();
	public void insertWeeklyPerformance(List<PerformanceAgent> listAgent);
	public List<PerformanceAgent> getPerformanceThisWeek();
	public void updatePerformanceThisWeek(List<PerformanceAgent> agents);

}
