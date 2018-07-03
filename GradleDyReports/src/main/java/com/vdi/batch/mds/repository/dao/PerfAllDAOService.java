package com.vdi.batch.mds.repository.dao;

import com.vdi.model.performance.PerformanceOverall;

public interface PerfAllDAOService {
	
	public int getTicketCount();
	public int getAchievedTicketCount();
	public int getMissedTicketCount();
	public void insertPerformance(PerformanceOverall overall);
	public PerformanceOverall getPerformance();
	public void updatePerformance(PerformanceOverall list);

}
