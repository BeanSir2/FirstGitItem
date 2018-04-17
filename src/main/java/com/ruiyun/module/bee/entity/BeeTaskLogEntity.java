package com.ruiyun.module.bee.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 行销管理_任务管理日志
 * @author fzy
 * @createTime 2017年6月23日
 */
public class BeeTaskLogEntity implements Serializable {
 
	private static final long serialVersionUID = 8668557673690309050L;
	 
	private Integer beeTaskLogId;
	
	private Integer beeTaskId;
	
	private Integer beeTeamId;
	
	private String taskName;
	
	private Integer taskPersonnelCount;
	
	private String  startDate;
	
	private String endDate;
	
	private BigDecimal longitude;
	
	private BigDecimal latitude;
	
	private String  address;
	
	private String  taskDate;
	
	private String amSignInTime;
	
	private String amSignOutTime;
	
	private String pmSignInTime;
	
	private String pmSignOutTime;
	
	private String signFloatMinute;
	
	private Integer signRadius;
	
	private String createTime;
	
	private Integer merchantId;
	
	private Integer auditBeePersonnelId;
	
	private String auditTime;
	
	private Integer auditStatus;
	
	private String auditReason;
	
	private String taskStatus;
	

	
	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Integer getBeeTaskId() {
		return beeTaskId;
	}

	public void setBeeTaskId(Integer beeTaskId) {
		this.beeTaskId = beeTaskId;
	}

	public Integer getBeeTeamId() {
		return beeTeamId;
	}

	public void setBeeTeamId(Integer beeTeamId) {
		this.beeTeamId = beeTeamId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaskPersonnelCount() {
		return taskPersonnelCount;
	}

	public void setTaskPersonnelCount(Integer taskPersonnelCount) {
		this.taskPersonnelCount = taskPersonnelCount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}

	public String getAmSignInTime() {
		return amSignInTime;
	}

	public void setAmSignInTime(String amSignInTime) {
		this.amSignInTime = amSignInTime;
	}

	public String getAmSignOutTime() {
		return amSignOutTime;
	}

	public void setAmSignOutTime(String amSignOutTime) {
		this.amSignOutTime = amSignOutTime;
	}

	public String getPmSignInTime() {
		return pmSignInTime;
	}

	public void setPmSignInTime(String pmSignInTime) {
		this.pmSignInTime = pmSignInTime;
	}

	public String getPmSignOutTime() {
		return pmSignOutTime;
	}

	public void setPmSignOutTime(String pmSignOutTime) {
		this.pmSignOutTime = pmSignOutTime;
	}

	public String getSignFloatMinute() {
		return signFloatMinute;
	}

	public void setSignFloatMinute(String signFloatMinute) {
		this.signFloatMinute = signFloatMinute;
	}

	public Integer getSignRadius() {
		return signRadius;
	}

	public void setSignRadius(Integer signRadius) {
		this.signRadius = signRadius;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getBeeTaskLogId() {
		return beeTaskLogId;
	}

	public void setBeeTaskLogId(Integer beeTaskLogId) {
		this.beeTaskLogId = beeTaskLogId;
	}

	public Integer getAuditBeePersonnelId() {
		return auditBeePersonnelId;
	}

	public void setAuditBeePersonnelId(Integer auditBeePersonnelId) {
		this.auditBeePersonnelId = auditBeePersonnelId;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditReason() {
		return auditReason;
	}

	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}
	
}
