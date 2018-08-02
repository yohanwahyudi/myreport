package com.vdi.batch.mds.repository;

import org.springframework.data.repository.CrudRepository;

import com.vdi.model.staging.StagingUserRequest;

public interface StagingUserRequestRepository extends CrudRepository<StagingUserRequest, Long>{

}
