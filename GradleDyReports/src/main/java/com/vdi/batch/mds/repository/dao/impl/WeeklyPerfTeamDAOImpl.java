package com.vdi.batch.mds.repository.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdi.batch.mds.repository.WeeklyPerfTeamRepository;
import com.vdi.batch.mds.repository.dao.WeeklyPerfTeamDAOService;
import com.vdi.model.performance.PerformanceTeam;

@Transactional
@Service("weeklyPerfTeamDAO")
public class WeeklyPerfTeamDAOImpl implements WeeklyPerfTeamDAOService {
	
	@Autowired
	private WeeklyPerfTeamRepository repo;

	@Override
	public List<Object[]> getTeamTicketByDivision() {
		return repo.getTeamTicketByDivision();
	}

	@Override
	public void insertWeeklyPerformance(List<PerformanceTeam> team) {
		repo.saveAll(team);
	}

	@Override
	public List<PerformanceTeam> getPerformanceThisWeek() {
		return repo.getPerformanceThisWeek();
	}

	@Override
	public void updatePerformanceThisWeek(List<PerformanceTeam> teams) {
		repo.saveAll(teams);		
	}

}
