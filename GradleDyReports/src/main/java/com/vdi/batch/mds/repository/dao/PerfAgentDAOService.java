package com.vdi.batch.mds.repository.dao;

import java.util.List;

import com.vdi.model.performance.PerformanceAgent;

public interface PerfAgentDAOService {
	
	public List<Object[]> getAgentTicket();
	public void insertPerformance(List<PerformanceAgent> listAgent);
	public List<PerformanceAgent> getPerformance();
	public void updatePerformance(List<PerformanceAgent> agents);

}
