package com.vdi.batch.mds.repository.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdi.batch.mds.repository.WeeklyPerfAgentRepository;
import com.vdi.batch.mds.repository.dao.WeeklyPerfAgentDAOService;
import com.vdi.model.performance.PerformanceAgent;

@Transactional
@Service("weeklyPerfAgentDAO")
public class WeeklyPerfAgentDAOImpl implements WeeklyPerfAgentDAOService{
	
	@Autowired
	private WeeklyPerfAgentRepository repo;

	@Override
	public List<Object[]> getAgentTicket() {
		return repo.getAgentTicket();
	}

	@Override
	public void insertWeeklyPerformance(List<PerformanceAgent> listAgent) {
		repo.saveAll(listAgent);
		
	}

	@Override
	public List<PerformanceAgent> getPerformanceThisWeek() {
		return repo.getPerformanceThisWeek();
	}

	@Override
	public void updatePerformanceThisWeek(List<PerformanceAgent> agents) {
		repo.saveAll(agents);		
	}

	
	
}
