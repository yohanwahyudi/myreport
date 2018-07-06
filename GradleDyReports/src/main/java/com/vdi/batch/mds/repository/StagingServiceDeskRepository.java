package com.vdi.batch.mds.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vdi.model.staging.StagingServiceDesk;

public interface StagingServiceDeskRepository extends CrudRepository<StagingServiceDesk, Long>{
	


}
