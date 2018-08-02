package com.vdi.batch.mds.repository.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vdi.batch.mds.repository.MonthlyURPerfAgentRepository;
import com.vdi.batch.mds.repository.dao.PerfAgentDAOService;
import com.vdi.model.performance.PerformanceAgent;

public class MonthlyURPerfAgentDAOImpl implements PerfAgentDAOService{

	@Autowired
	private MonthlyURPerfAgentRepository repo;
	
	@Override
	public List<Object[]> getAgentTicket() {
		
		return null;
	}

	@Override
	public List<Object[]> getAgentTicket(int week, int month) {
		
		return repo.getAgentTicket(month);
	}

	@Override
	public void insertPerformance(List<PerformanceAgent> listAgent) {
		
		repo.saveAll(listAgent);
	}

	@Override
	public List<PerformanceAgent> getPerformance() {
		
		return null;
	}

	@Override
	public List<PerformanceAgent> getPerformance(int week, int month) {
		
		return repo.getPerformanceThisMonth(month);
	}

	@Override
	public void updatePerformance(List<PerformanceAgent> agents) {
		
		repo.saveAll(agents);
		
	}
	
	

}
