package com.vdi.batch.mds.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vdi.model.performance.PerformanceOverall;

public interface MonthlyURPerfAllRepository extends CrudRepository<PerformanceOverall, Long>{
	
	@Query(value="select " + 
			"	count(incident_ref) " + 
			"from staging_userrequest " + 
			"where yearweek(urequest_startdate,3)=yearweek(curdate(),3)-1 and scalar_user like 'EXT%';", nativeQuery=true)
	public int getAllTicketCount();
	
	@Query(value="select " + 
			"	count(scalar_urequestref) " + 
			"from staging_userrequest " + 
			"where year(urequest_startdate)=year(curdate()) "+   
			"and month(urequest_startdate)= :month "+
			"and scalar_previousvalue in ('escalated_tto','new') and scalar_newvalue = 'assigned' "+
			"and scalar_user like 'EXT%' "+
			";", nativeQuery=true)
	public int getTicketCount(@Param("month") int month);
	
	@Query(value="select " + 
			"	count(scalar_urequestref) " + 
			"from staging_userrequest " + 
			"where year(urequest_startdate)=year(curdate()) "+   
			"and month(urequest_startdate)= :month "+
			"and scalar_previousvalue in ('escalated_tto','new') and scalar_newvalue = 'assigned' "+
			"and urequest_slattopassed='no'  "+
			"and scalar_user like 'EXT%' "+
			";", nativeQuery=true)
	public int getAchievedTicketCount(@Param("month") int month);
	
	@Query(value="select " + 
			"	count(scalar_urequestref) " + 
			"from staging_userrequest " + 
			"where year(urequest_startdate)=year(curdate()) "+   
			"and month(urequest_startdate)= :month "+
			"and scalar_previousvalue in ('escalated_tto','new') and scalar_newvalue = 'assigned' "+
			"and urequest_slattopassed='yes'  "+
			"and scalar_user like 'EXT%' "+
			";", nativeQuery=true)
	public int getMissedTicketCount(@Param("month") int month);
	
	@Query(value="select * from perf_overall "+
			"WHERE  " +
				"year(created_dt)=year(curdate()) "+   
				"AND month(created_dt)= :month "+
				"AND period='monthly' "+
				"AND category='ur';", nativeQuery=true)
	public PerformanceOverall getPerformanceThisMonth(@Param("month") int month);
	
	@Query(value="select * from perf_overall WHERE  Year(created_dt) = Year(Curdate()) AND month(created_dt) = month(curdate()) "+
			"AND period='monthly' AND category='ur';", nativeQuery=true)
	public PerformanceOverall getPerformanceThisMonth();

}
