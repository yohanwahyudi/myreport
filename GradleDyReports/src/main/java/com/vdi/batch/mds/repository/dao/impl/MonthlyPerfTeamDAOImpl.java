package com.vdi.batch.mds.repository.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdi.batch.mds.repository.MonthlyPerfTeamRepository;
import com.vdi.batch.mds.repository.dao.PerfTeamDAOService;
import com.vdi.model.performance.PerformanceTeam;

@Transactional
@Service("monthlyPerfTeamDAO")
public class MonthlyPerfTeamDAOImpl implements PerfTeamDAOService{
	
	@Autowired
	MonthlyPerfTeamRepository repo;

	@Override
	public List<Object[]> getTeamTicketByDivision() {
		return repo.getTeamTicketByDivision();
	}

	@Override
	public void insertPerformance(List<PerformanceTeam> team) {
		repo.saveAll(team);
	}

	@Override
	public List<PerformanceTeam> getPerformance() {
		return repo.getPerformanceThisWeek();
	}

	@Override
	public void updatePerformance(List<PerformanceTeam> teams) {
		repo.saveAll(teams);		
	}
	
	

}
