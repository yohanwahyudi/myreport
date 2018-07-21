package com.vdi.batch.mds.repository.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vdi.batch.mds.repository.WeeklyPerfAgentRepository;
import com.vdi.batch.mds.repository.dao.PerfAgentDAOService;
import com.vdi.model.performance.PerformanceAgent;

@Transactional
@Repository("weeklyPerfAgentDAO")
public class WeeklyPerfAgentDAOImpl implements PerfAgentDAOService{
	
	@Autowired
	private WeeklyPerfAgentRepository repo;

	@Override
	public List<Object[]> getAgentTicket() {
		return repo.getAgentTicket();
	}

	@Override
	public void insertPerformance(List<PerformanceAgent> listAgent) {
		repo.saveAll(listAgent);
		
	}

	@Override
	public List<PerformanceAgent> getPerformance() {
		return repo.getPerformanceThisWeek();
	}

	@Override
	public void updatePerformance(List<PerformanceAgent> agents) {
		repo.saveAll(agents);		
	}

	@Override
	public List<Object[]> getAgentTicket(int week, int month) {
		return repo.getAgentTicket(week, month);
	}

	@Override
	public List<PerformanceAgent> getPerformance(int week, int month) {
		return repo.getPerformanceThisWeek(week, month);
	}

	
	
}
