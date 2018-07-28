package com.vdi.reports.djasper.service;


import java.util.List;

import com.vdi.reports.djasper.model.PerformanceReport;

import ar.com.fdvs.dj.domain.DynamicReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface ReportService {

	public DynamicReport buildReport();
	public JasperPrint getReport() throws JRException, Exception;
	public JRDataSource getDataSource();
	public List<PerformanceReport> getPerformanceReport();
	
}
