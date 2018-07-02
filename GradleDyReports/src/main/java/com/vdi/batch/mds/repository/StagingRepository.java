package com.vdi.batch.mds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vdi.model.staging.Staging;

@Repository
public interface StagingRepository extends CrudRepository<Staging, Long>{	
	
	@Query(value="SELECT staging.* "
			+ "FROM staging_incident as staging "
			+ "LEFT OUTER JOIN test.incident as incident "
			+ "on staging.ref=incident.ref where incident.ref is null", nativeQuery=true)
	public List<Staging> getDataForInsert();
	
	@Query(value="update test.seq4 set next_val= 1", nativeQuery=true)
	public void resetSequenceTo1();
	
	@Query(value="SET SQL_SAFE_UPDATES = 0", nativeQuery=true)
	public void disableSafeUpdates();
	
	@Query(value="SET SQL_SAFE_UPDATES = 1", nativeQuery=true)
	public void enableSafeUpdates();
	
	@Query(value="update test.incident as incident " + 
			"inner join test.staging_incident as staging " + 
			"on incident.ref=staging.ref " + 
			"set incident.status=staging.status, incident.status2=staging.status2, incident.resolution_date=staging.resolution_date, " + 
			"incident.resolution_delay=staging.resolution_delay, incident.resolution_time=staging.resolution_time, " + 
			"incident.lastpending_date=staging.lastpending_date, incident.lastpending_time=staging.lastpending_time, " + 
			"incident.lastupdate_date=staging.lastupdate_date,incident.lastupdate_time=staging.lastupdate_time, " + 
			"incident.cumulated_pending=staging.cumulated_pending, incident.assignment_date=staging.assignment_date, incident.assignment_time=staging.assignment_time, " + 
			"incident.agent_fullname=staging.agent_fullname, incident.agent_lastname=staging.agent_lastname, incident.agent=staging.agent, " + 
			"incident.tto=staging.tto, incident.tto_passed=staging.tto_passed, incident.tto_deadline=staging.tto_deadline, " + 
			"incident.ttr=staging.ttr, incident.ttr_passed=staging.ttr_passed, incident.ttr_deadline=staging.ttr_deadline", nativeQuery=true)
	public void updateIncidentTable();
	
	@Query(value="insert into test.incident " + 
			"select " + 
			"	staging.* " + 
			"from test.staging_incident as staging " + 
			"	left outer join test.incident as incident " + 
			"	on staging.ref=incident.ref " + 
			"where (staging.person_org_name='DCU PT. Visionet Data International' OR staging.person_org_name='PT. Visionet Data International') AND incident.ref is null ", nativeQuery=true)
	public void insertToIncidentTable();
	
	@Query(value="select " + 
			"	agent_fullname, team_name " + 
			"from " + 
			"	(select agent_fullname,team_name from test.incident group by agent_fullname) AS agentIncident " + 
			"where agentIncident.agent_fullname not in (select name from test.agent group by name)", nativeQuery=true)
	public List<Object[]> getUnregisteredAgent();

}
