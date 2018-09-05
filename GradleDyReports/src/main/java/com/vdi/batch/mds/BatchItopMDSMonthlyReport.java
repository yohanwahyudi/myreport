package com.vdi.batch.mds;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.vdi.batch.mds.helper.weekly.PopulatePerformance;
import com.vdi.batch.mds.helper.weekly.PopulateSDPerformance;
import com.vdi.batch.mds.helper.weekly.PopulateURPerformance;
import com.vdi.configuration.AppConfig;
import com.vdi.configuration.PropertyNames;
import com.vdi.reports.ReportExporter;
import com.vdi.reports.djasper.service.ReportService;
import com.vdi.tools.TimeStatic;

import net.sf.jasperreports.engine.JRException;

public class BatchItopMDSMonthlyReport extends QuartzJobBean{

	private final String period = PropertyNames.CONSTANT_REPORT_PERIOD_MONTHLY;
	private final Logger logger = LogManager.getLogger(BatchItopMDSMonthlyReport.class);
	private AnnotationConfigApplicationContext ctx;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		logger.debug("Batch itop mds monthly report started.......");
		
ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
		//populate performance
		PopulatePerformance weekly = ctx.getBean("populatePerformanceMonthly", PopulatePerformance.class);
		weekly.populatePerformance();		
		PopulateSDPerformance weeklySD = ctx.getBean("populateSDPerformanceMonthly", PopulateSDPerformance.class);
		weeklySD.populatePerformance();		
		PopulateURPerformance weeklyUR = ctx.getBean("populateURPerformanceMonthly", PopulateURPerformance.class);
		weeklyUR.populatePerformance();
		
		String fileName = "VDI_ITOP_Performance_"+TimeStatic.prevMonthStr+"_"+TimeStatic.currentYear+".pdf";
		ReportService rpt = ctx.getBean("itopPerformanceReport", ReportService.class);
		try {
			ReportExporter.exportReport(rpt.getReport(period), 
					System.getProperty("user.dir") + "/target/reports/" 
					+ fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		logger.debug("Batch itop mds monthly report finished.......");
	}

	
	
}
