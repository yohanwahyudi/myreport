package com.vdi.batch.mds.repository.dao.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdi.batch.mds.repository.MonthlyPerfAllRepository;
import com.vdi.batch.mds.repository.dao.PerfAllDAOService;
import com.vdi.model.performance.PerformanceOverall;

@Transactional
@Service("monthlyPerfAllDAO")
public class MonthlyPerfAllDAOImpl implements PerfAllDAOService{
	
	@Autowired
	MonthlyPerfAllRepository repo;

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
		overall.setPeriod("monthly");
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

	@Override
	public int getTicketCount(int week, int month) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAchievedTicketCount(int week, int month) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMissedTicketCount(int week, int month) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PerformanceOverall getPerformance(int week, int month) {
		// TODO Auto-generated method stub
		return null;
	}

}
