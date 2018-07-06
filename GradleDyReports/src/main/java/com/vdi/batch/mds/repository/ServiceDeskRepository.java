package com.vdi.batch.mds.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vdi.model.ServiceDesk;

public interface ServiceDeskRepository extends CrudRepository<ServiceDesk, Long>{

}
