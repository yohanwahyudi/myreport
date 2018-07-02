package com.vdi.batch.mds.repository.dao;

import java.util.List;

import com.vdi.model.performance.PerformanceTeam;

public interface WeeklyPerfTeamDAOService {
	
	public List<Object[]> getTeamTicketByDivision();
	public void insertWeeklyPerformance(List<PerformanceTeam> team);
	public List<PerformanceTeam> getPerformanceThisWeek();
	public void updatePerformanceThisWeek(List<PerformanceTeam> teams);

}
