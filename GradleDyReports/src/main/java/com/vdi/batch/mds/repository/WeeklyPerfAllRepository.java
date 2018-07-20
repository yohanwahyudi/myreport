package com.vdi.batch.mds.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vdi.model.performance.PerformanceOverall;

@Repository
public interface WeeklyPerfAllRepository extends CrudRepository<PerformanceOverall, Long>{
	
	@Query(value="select count(ref) as total_ticket from incident "
			+ "where yearweek(start_date,3)=yearweek(curdate(),3);" ,nativeQuery=true)
	public int getAllTicketCount();
	
	@Query(value="select count(ref) as total_ticket from incident "
			+ "where status in ('closed','resolved') and yearweek(start_date,3)=yearweek(curdate(),3);" ,nativeQuery=true)
	public int getTicketCount();
	
	@Query(value="select count(ref) as total_ticket from incident "
			+ "where status in ('closed','resolved') and ttr_passed='no' and yearweek(start_date,3)=yearweek(curdate(),3);" ,nativeQuery=true)
	public int getAchievedTicketCount();
	
	@Query(value="select count(ref) as total_ticket from incident "
			+ "where status in ('closed','resolved') and ttr_passed='yes' and yearweek(start_date,3)=yearweek(curdate(),3);" ,nativeQuery=true)
	public int getMissedTicketCount();
	
	@Query(value="select * from perf_overall WHERE  Yearweek(created_dt, 3) = Yearweek(Curdate(), 3) AND period='weekly' AND category='sa';", nativeQuery=true)
	public PerformanceOverall getPerformanceThisWeek();

}
