package com.vdi.batch.mds.repository.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdi.batch.mds.repository.WeeklyPerfAllRepository;
import com.vdi.batch.mds.repository.dao.WeeklyPerfAllDAOService;
import com.vdi.model.performance.PerformanceOverall;

@Transactional
@Service("weeklyPerfAllDAO")
public class WeeklyPerfAllDAOImpl implements WeeklyPerfAllDAOService{
	
	@Autowired
	WeeklyPerfAllRepository repo;

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
	public void insertWeeklyPerformance(PerformanceOverall overall) {
		overall.setPeriod("weekly");
		repo.save(overall);
		
	}

	@Override
	public PerformanceOverall getPerformanceThisWeek() {
		return (PerformanceOverall) repo.getPerformanceThisWeek();
	}

	@Override
	public void updatePerformance(PerformanceOverall perf) {
		repo.save(perf);
	}


}
