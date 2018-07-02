package com.vdi.batch.mds.helper.weekly;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import com.vdi.batch.mds.repository.dao.WeeklyPerfAgentDAOService;
import com.vdi.batch.mds.repository.dao.WeeklyPerfAllDAOService;
import com.vdi.batch.mds.repository.dao.WeeklyPerfTeamDAOService;
import com.vdi.model.performance.PerformanceAgent;
import com.vdi.model.performance.PerformanceOverall;
import com.vdi.model.performance.PerformanceTeam;

@Configuration
public class PopulatePerformance {

	@Autowired
	@Qualifier("weeklyPerfAllDAO")
	private WeeklyPerfAllDAOService allDAO;

	@Autowired
	@Qualifier("weeklyPerfTeamDAO")
	private WeeklyPerfTeamDAOService teamDAO;

	@Autowired
	@Qualifier("weeklyPerfAgentDAO")
	private WeeklyPerfAgentDAOService agentDAO;
	
	public void populatePerformance() {
		allDAO.insertWeeklyPerformance(getPerformanceOverall());
		teamDAO.insertWeeklyPerformance(getPerformanceTeamList());
		agentDAO.insertWeeklyPerformance(getPerformanceAgentList());
	}

	@SuppressWarnings("unused")
	public PerformanceOverall getPerformanceOverall() {

		int ticketCount = allDAO.getTicketCount();
		int achievedCount = allDAO.getAchievedTicketCount();
		int missedCount = allDAO.getMissedTicketCount();
		float achievement = (getAchievementTicket(new BigDecimal(achievedCount), new BigDecimal(ticketCount)))
				.floatValue();

		PerformanceOverall poUseThis = new PerformanceOverall();
		PerformanceOverall poExisting = allDAO.getPerformanceThisWeek();
		if(poExisting==null) {
			poUseThis.setTotalTicket(ticketCount);
			poUseThis.setTotalAchieved(achievedCount);
			poUseThis.setTotalMissed(missedCount);
			poUseThis.setAchievement(achievement);
		} else {
			poExisting.setTotalTicket(ticketCount);
			poExisting.setTotalAchieved(achievedCount);
			poExisting.setTotalMissed(missedCount);
			poExisting.setAchievement(achievement);

			poUseThis = poExisting;
		}
		
		return poUseThis;

	}

	public List<PerformanceTeam> getPerformanceTeamList() {

		// get new ticket
		List<Object[]> newObjList = new ArrayList<Object[]>();
		newObjList = teamDAO.getTeamTicketByDivision();

		Map<String, PerformanceTeam> newPerfMap = new HashMap<String, PerformanceTeam>();
		List<PerformanceTeam> newPerfList = new ArrayList<PerformanceTeam>();

		for (Object[] object : newObjList) {

			int ticketCount = ((BigInteger) object[1]).intValue();
			int achievedCount = ((BigInteger) object[2]).intValue();
			int missedCount = ((BigInteger) object[3]).intValue();
			float achievement = (getAchievementTicket(new BigDecimal(achievedCount), new BigDecimal(ticketCount)))
					.floatValue();
			String teamName = (String) object[0];

			PerformanceTeam perfTeam = new PerformanceTeam();
			perfTeam.setTeamName(teamName);
			perfTeam.setTotalTicket(ticketCount);
			perfTeam.setTotalAchieved(achievedCount);
			perfTeam.setTotalMissed(missedCount);
			perfTeam.setPeriod("weekly");
			perfTeam.setAchievement(achievement);

			newPerfList.add(perfTeam);
			newPerfMap.put(teamName, perfTeam);

		}

		// compare with existing ticket
		List<PerformanceTeam> existingList = teamDAO.getPerformanceThisWeek();
		List<PerformanceTeam> useThisList = new ArrayList<PerformanceTeam>();
		if (existingList.size() < 1) {
			useThisList = newPerfList;

		} else {
			Map<String, PerformanceTeam> existingMap = new HashMap<String, PerformanceTeam>();
			for (PerformanceTeam pf : existingList) {
				existingMap.put(pf.getTeamName(), pf);
			}

			for (Map.Entry<String, PerformanceTeam> entry : newPerfMap.entrySet()) {
				PerformanceTeam existing = existingMap.get(entry.getKey());
				PerformanceTeam updated = newPerfMap.get(entry.getKey());

				existing.setAchievement(updated.getAchievement());
				existing.setTeamName(updated.getTeamName());
				existing.setTotalAchieved(updated.getTotalAchieved());
				existing.setTotalMissed(updated.getTotalMissed());
				existing.setTotalTicket(updated.getTotalTicket());

				useThisList.add(existing);
			}
		}

		return useThisList;
	}

	public List<PerformanceAgent> getPerformanceAgentList() {

		// get new ticket
		List<Object[]> newObjList = new ArrayList<Object[]>();
		newObjList = agentDAO.getAgentTicket();

		Map<String, PerformanceAgent> newPerfMap = new HashMap<String, PerformanceAgent>();
		List<PerformanceAgent> newPerfList = new ArrayList<PerformanceAgent>();

		for (Object[] object : newObjList) {

			String division = (String) object[0];
			String agentName = (String) object[1];
			int totalAssigned = ((BigInteger) object[2]).intValue();
			int totalPending = ((BigInteger) object[3]).intValue();
			int totalAchieved = ((BigInteger) object[4]).intValue();
			int totalMissed = ((BigInteger) object[5]).intValue();
			int totalTicket = ((BigInteger) object[6]).intValue();
			String period = "weekly";
			float achievement = (getAchievementTicket(new BigDecimal(totalAchieved), new BigDecimal(totalTicket)))
					.floatValue();

			PerformanceAgent perfAgent = new PerformanceAgent();
			perfAgent.setDivision(division);
			perfAgent.setAgentName(agentName);
			perfAgent.setTotalAssigned(totalAssigned);
			perfAgent.setTotalPending(totalPending);
			perfAgent.setTotalAchieved(totalAchieved);
			perfAgent.setTotalMissed(totalMissed);
			perfAgent.setTotalTicket(totalTicket);
			perfAgent.setPeriod(period);
			perfAgent.setAchievement(achievement);

			newPerfList.add(perfAgent);
			newPerfMap.put(agentName, perfAgent);
		}

		// compare with existing ticket
		List<PerformanceAgent> existingList = agentDAO.getPerformanceThisWeek();
		List<PerformanceAgent> useThisList = new ArrayList<PerformanceAgent>();
		if (existingList.size() < 1) {
			useThisList = newPerfList;

		} else {
			Map<String, PerformanceAgent> existingMap = new HashMap<String, PerformanceAgent>();
			for (PerformanceAgent pf : existingList) {
				existingMap.put(pf.getAgentName(), pf);
			}

			for (Map.Entry<String, PerformanceAgent> entry : newPerfMap.entrySet()) {
				PerformanceAgent existing = existingMap.get(entry.getKey());
				PerformanceAgent updated = newPerfMap.get(entry.getKey());

				existing.setDivision(updated.getDivision());
				existing.setAgentName(updated.getAgentName());
				existing.setTotalAssigned(updated.getTotalAssigned());
				existing.setTotalPending(updated.getTotalPending());
				existing.setTotalAchieved(updated.getTotalAchieved());
				existing.setTotalMissed(updated.getTotalMissed());
				existing.setTotalTicket(updated.getTotalTicket());
				existing.setAchievement(updated.getAchievement());

				useThisList.add(existing);
			}
		}

		return useThisList;
	}

	public BigDecimal getAchievementTicket(BigDecimal ticketAchieved, BigDecimal ticketTotal) {

		BigDecimal achievement = new BigDecimal(0);
		
		achievement = ticketAchieved.divide(ticketTotal, 4, BigDecimal.ROUND_HALF_UP);
		achievement = achievement.multiply(new BigDecimal(100));

		return achievement;
	}

}
