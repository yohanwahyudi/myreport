package com.vdi.batch.mds.repository.dao.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdi.batch.mds.repository.WeeklySDPerfAllRepository;
import com.vdi.batch.mds.repository.dao.PerfAllDAOService;
import com.vdi.model.performance.PerformanceOverall;

@Transactional
@Service("weeklySDPerfAllDAO")
public class WeeklySDPerfAllDAOImpl implements PerfAllDAOService{
	
	@Autowired
	WeeklySDPerfAllRepository repo;

	@Override
	public int getTicketCount() {
		return repo.getTicketCount();
	}

	@Override
	public int getAchievedTicketCount() {
		return repo.getAchievedTicketCount();
	}

	@Override
	public int getMissedTicketCount() {
		return repo.getMissedTicketCount();
	}

	@Override
	public void insertPerformance(PerformanceOverall overall) {
//		overall.setPeriod("weekly");
		repo.save(overall);
		
	}

	@Override
	public PerformanceOverall getPerformance() {
		return (PerformanceOverall) repo.getPerformanceThisWeek();
	}

	@Override
	public void updatePerformance(PerformanceOverall perf) {
		repo.save(perf);
	}


}
