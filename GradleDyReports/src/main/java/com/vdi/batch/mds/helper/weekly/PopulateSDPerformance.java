package com.vdi.batch.mds.helper.weekly;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import com.vdi.batch.mds.repository.dao.PerfAgentDAOService;
import com.vdi.batch.mds.repository.dao.PerfAllDAOService;
import com.vdi.model.performance.PerformanceAgent;
import com.vdi.model.performance.PerformanceOverall;

@Configuration("populateSDPerformanceWeekly")
public class PopulateSDPerformance {

	@Autowired
	@Qualifier("weeklySDPerfAllDAO")
	private PerfAllDAOService allDAO;

	@Autowired
	@Qualifier("weeklySDPerfAgentDAO")
	private PerfAgentDAOService agentDAO;
	
	private final Logger logger = LogManager.getLogger(PopulateSDPerformance.class);
	
	public void populatePerformance() {
		allDAO.insertPerformance(getPerformanceOverall());
		agentDAO.insertPerformance(getPerformanceAgentList());
	}

	@SuppressWarnings("unused")
	public PerformanceOverall getPerformanceOverall() {

		int ticketCount = allDAO.getTicketCount();
		int achievedCount = allDAO.getAchievedTicketCount();
		int missedCount = allDAO.getMissedTicketCount();
		float achievement = (getAchievementTicket(new BigDecimal(achievedCount), new BigDecimal(ticketCount)))
				.floatValue();

		PerformanceOverall poUseThis = new PerformanceOverall();
		PerformanceOverall poExisting = allDAO.getPerformance();
		if(poExisting==null) {
			poUseThis.setTotalTicket(ticketCount);
			poUseThis.setTotalAchieved(achievedCount);
			poUseThis.setTotalMissed(missedCount);
			poUseThis.setAchievement(achievement);
			poUseThis.setPeriod("weekly");
			poUseThis.setCategory("sd");
		} else {
			poExisting.setTotalTicket(ticketCount);
			poExisting.setTotalAchieved(achievedCount);
			poExisting.setTotalMissed(missedCount);
			poExisting.setAchievement(achievement);
			
			poUseThis = poExisting;
		}
		
		return poUseThis;

	}

	public List<PerformanceAgent> getPerformanceAgentList() {

		// get new ticket
		List<Object[]> newObjList = new ArrayList<Object[]>();
		newObjList = agentDAO.getAgentTicket();

		Map<String, PerformanceAgent> newPerfMap = new HashMap<String, PerformanceAgent>();
		List<PerformanceAgent> newPerfList = new ArrayList<PerformanceAgent>();

		for (Object[] object : newObjList) {

			logger.debug(object[0]+" "+object[0].getClass());
			logger.debug(object[1]);
			
			String division = (String) object[0];
			String agentName = (String) object[1];
			int totalAchieved = ((BigInteger) object[2]).intValue();
			int totalMissed = ((BigInteger) object[3]).intValue();
			int totalTicket = ((BigInteger) object[4]).intValue();
			float achievement = (getAchievementTicket(new BigDecimal(totalAchieved), new BigDecimal(totalTicket)))
					.floatValue();

			PerformanceAgent perfAgent = new PerformanceAgent();
			perfAgent.setDivision(division);
			perfAgent.setAgentName(agentName);
			perfAgent.setTotalAchieved(totalAchieved);
			perfAgent.setTotalMissed(totalMissed);
			perfAgent.setTotalTicket(totalTicket);
			perfAgent.setPeriod("weekly");
			perfAgent.setCategory("sd");
			perfAgent.setAchievement(achievement);

			newPerfList.add(perfAgent);
			newPerfMap.put(agentName, perfAgent);
		}

		// compare with existing ticket
		List<PerformanceAgent> existingList = agentDAO.getPerformance();
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

				if(existing!=null) {
					existing.setDivision(updated.getDivision());
					existing.setAgentName(updated.getAgentName());
					existing.setTotalAssigned(updated.getTotalAssigned());
					existing.setTotalPending(updated.getTotalPending());
					existing.setTotalAchieved(updated.getTotalAchieved());
					existing.setTotalMissed(updated.getTotalMissed());
					existing.setTotalTicket(updated.getTotalTicket());
					existing.setAchievement(updated.getAchievement());
				} else {
					existing = updated;
				}

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
