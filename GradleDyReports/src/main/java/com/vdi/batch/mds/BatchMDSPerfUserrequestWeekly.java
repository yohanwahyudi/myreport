package com.vdi.batch.mds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.vdi.batch.mds.helper.weekly.PopulateURPerformance;
import com.vdi.configuration.AppConfig;

@Configuration
public class BatchMDSPerfUserrequestWeekly extends QuartzJobBean{

	private Logger logger = LogManager.getLogger(BatchMDSPerfUserrequestWeekly.class);
	private AnnotationConfigApplicationContext ctx;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.debug("Execute BatchMDSPerfUserrequestWeekly......");
		
		ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		PopulateURPerformance weekly = ctx.getBean("populateURPerformanceWeekly", PopulateURPerformance.class);
		weekly.populatePerformance();
		
		logger.debug("Execute BatchMDSPerfUserrequestWeekly finished......");
		
	}
	
}
