package com.vdi.batch.mds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.vdi.batch.mds.helper.PopulateIncident;
import com.vdi.configuration.AppConfig;

@Component
public class BatchMDSPopulateIncident extends QuartzJobBean{
	
	private final Logger logger = LogManager.getLogger(BatchMDSPopulateIncident.class);
	private AnnotationConfigApplicationContext ctx;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.debug("Execute BatchMDSPopulateIncident......");
		ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		PopulateIncident populateIncident = ctx.getBean(PopulateIncident.class);
		try {
			populateIncident.populate();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		logger.debug("Execute BatchMDSPopulateIncident finished......");
		
	}
	
	

}
