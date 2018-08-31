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
@Table(name = "perf_agent")
public class PerformanceAgent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private int teamId;
	private String agentName;
	private String division;
	private int totalAssigned;
	private int totalPending;
	private int totalTicket;
	private int totalAchieved;
	private int totalMissed;
	private float achievement;
	private String period;
	private String category;
	private short month;

	@Column(name = "created_dt")
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "updated_dt")
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	@Version
	private int version;
	
	public PerformanceAgent() {
		
	}
	
	public PerformanceAgent(String agent, int totalAchieved, int totalMissed, int totalTicket, float achievement) {
		this.agentName = agent;
		this.totalAchieved = totalAchieved;
		this.totalMissed = totalMissed;
		this.totalTicket = totalTicket;
		this.achievement = achievement;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public int getTotalAssigned() {
		return totalAssigned;
	}

	public void setTotalAssigned(int totalAssigned) {
		this.totalAssigned = totalAssigned;
	}

	public int getTotalPending() {
		return totalPending;
	}

	public void setTotalPending(int totalPending) {
		this.totalPending = totalPending;
	}

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

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
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

	public short getMonth() {
		return month;
	}

	public void setMonth(short month) {
		this.month = month;
	}

	

}
