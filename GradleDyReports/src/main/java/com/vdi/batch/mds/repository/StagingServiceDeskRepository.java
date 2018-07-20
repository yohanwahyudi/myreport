package com.vdi.batch.mds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vdi.model.staging.StagingServiceDesk;

public interface StagingServiceDeskRepository extends CrudRepository<StagingServiceDesk, Long> {

	@Query(value = "select   	scalar_user   from "+ 
		 "(select scalar_user from staging_servicedesk where scalar_user like '%EXT%' group by scalar_user) AS agentIncident "+ 
		 "where agentIncident.scalar_user not in (select name from agent group by name);", nativeQuery = true)
	public List<Object[]> getUnregisteredAgent();

}
