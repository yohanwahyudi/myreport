package com.vdi.batch.mds.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import com.vdi.batch.mds.repository.dao.StagingServiceDeskDAOService;
import com.vdi.batch.mds.service.ItopMDSDataLoaderService;
import com.vdi.batch.mds.service.MailService;
import com.vdi.configuration.AppConfig;
import com.vdi.model.staging.StagingServiceDesk;

@Configuration
public class PopulateServiceDesk {

	@Autowired
	@Qualifier("stagingServiceDeskDAO")
	private StagingServiceDeskDAOService stagingDAO;

	@Autowired
	@Qualifier("serviceDeskDataLoaderService")
	private ItopMDSDataLoaderService load;

	@Autowired
	@Qualifier("mailService")
	private MailService mailService;

	private List<StagingServiceDesk> stagingList;


	@SuppressWarnings("unchecked")
	public void addToStaging() throws Throwable {
		
		stagingList = new ArrayList<StagingServiceDesk>();
		stagingList = load.getStagingAllByURL();

		if (stagingList != null && stagingList.size() > 0) {
			stagingDAO.addAll(stagingList);
		}		
		
	}


	public void populate() throws Throwable {
		
		// delete and reset sequence for staging table
		stagingDAO.deleteEntity();

		// add new incident list to staging
		addToStaging();


	}

}