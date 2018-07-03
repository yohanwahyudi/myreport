package com.vdi.batch.mds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vdi.model.performance.PerformanceTeam;

@Repository
public interface MonthlyPerfTeamRepository extends CrudRepository<PerformanceTeam,Long>{

	@Query(value="SELECT one.division, " + 
			"       Ifnull(one.total_ticket, 0) as total_ticket, " + 
			"       Ifnull(two.achieved_ticket, 0) as achieved_ticket, " + 
			"       Ifnull(three.missed_ticket, 0) as missed_ticket " + 
			"FROM   (SELECT agent.division, " + 
			"               Count(ref) AS total_ticket " + 
			"        FROM   test.incident incident " + 
			"               INNER JOIN test.agent agent " + 
			"                       ON incident.agent_fullname = agent.NAME " + 
			"        WHERE  status IN ( 'closed', 'resolved' ) " + 
			"               AND month(start_date)=month(curdate())-1 AND year(start_date)=year(curdate()) " + 
			"        GROUP  BY division) one " + 
			"       LEFT JOIN (SELECT agent.division, " + 
			"                         Count(ref) AS achieved_ticket " + 
			"                  FROM   test.incident incident " + 
			"                         INNER JOIN test.agent agent " + 
			"                                 ON incident.agent_fullname = agent.NAME " + 
			"                  WHERE  status IN ( 'closed', 'resolved' ) " + 
			"                         AND ttr_passed = 'no' " + 
			"                         AND month(start_date)=month(curdate())-1 AND year(start_date)=year(curdate()) " + 
			"                  GROUP  BY division) two " + 
			"              ON one.division = two.division " + 
			"       LEFT JOIN (SELECT agent.division, " + 
			"                         Count(ref) AS missed_ticket " + 
			"                  FROM   test.incident incident " + 
			"                         INNER JOIN test.agent agent " + 
			"                                 ON incident.agent_fullname = agent.NAME " + 
			"                  WHERE  status IN ( 'closed', 'resolved' ) " + 
			"                         AND ttr_passed = 'yes' " + 
			"                         AND month(start_date)=month(curdate())-1 AND year(start_date)=year(curdate()) " + 
			"                  GROUP  BY division) three " + 
			"              ON one.division = three.division; ",nativeQuery=true)
	public List<Object[]> getTeamTicketByDivision();
	
	@Query(value="select * from test.perf_team WHERE  month(created_dt)=month(curdate()) and year(created_dt)=year(curdate()) AND period='monthly';", nativeQuery=true)
	public List<PerformanceTeam> getPerformanceThisWeek();
	
}
