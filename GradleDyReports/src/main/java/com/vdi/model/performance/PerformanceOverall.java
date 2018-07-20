package com.vdi.model.performance;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="perf_overall")
public class PerformanceOverall {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", updatable=false,nullable=false)
	private Long id;
	private int totalTicket;
	private int totalAchieved;
	private int totalMissed;
	private float achievement;
	private String period;
	private String category;

	@Column(name="created_dt")
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(name="updated_dt")
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	@Version
	private int version;

	public int getTotalTicket() {
		return totalTicket;
	}

	public void setTotalTicket(int totalTicket) {
		this.totalTicket = totalTicket;
	}

	public int getTotalAchieved() {
		return totalAchieved;
	}

	public void setTotalAchieved(int totalAchieved) {
		this.totalAchieved = totalAchieved;
	}

	public int getTotalMissed() {
		return totalMissed;
	}

	public void setTotalMissed(int totalMissed) {
		this.totalMissed = totalMissed;
	}

	public float getAchievement() {
		return achievement;
	}

	public void setAchievement(float achievement) {
		this.achievement = achievement;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
