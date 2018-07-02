package com.vdi.batch.mds.repository.dao;

import com.vdi.model.performance.PerformanceOverall;

public interface WeeklyPerfAllDAOService {
	
	public int getTicketCount();
	public int getAchievedTicketCount();
	public int getMissedTicketCount();
	public void insertWeeklyPerformance(PerformanceOverall overall);
	public PerformanceOverall getPerformanceThisWeek();
	public void updatePerformance(PerformanceOverall list);

}
