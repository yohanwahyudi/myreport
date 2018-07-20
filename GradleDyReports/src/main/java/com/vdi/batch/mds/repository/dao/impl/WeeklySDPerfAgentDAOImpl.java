package com.vdi.batch.mds.repository.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdi.batch.mds.repository.WeeklySDPerfAgentRepository;
import com.vdi.batch.mds.repository.dao.PerfAgentDAOService;
import com.vdi.model.performance.PerformanceAgent;

@Transactional
@Service("weeklySDPerfAgentDAO")
public class WeeklySDPerfAgentDAOImpl implements PerfAgentDAOService{
	
	@Autowired
	private WeeklySDPerfAgentRepository repo;

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

	
	
}
