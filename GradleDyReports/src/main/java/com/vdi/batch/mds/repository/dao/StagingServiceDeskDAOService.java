package com.vdi.batch.mds.repository.dao;

import java.util.List;

import com.vdi.model.staging.StagingServiceDesk;

public interface StagingServiceDeskDAOService extends BaseDAOService{

	public List<Object[]> getUnregisteredAgent();
}
