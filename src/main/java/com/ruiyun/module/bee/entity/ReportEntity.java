/**
 * 
 */
package com.ruiyun.module.bee.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className:ReportEntity
 * @author:   wurong
 * @date:     2017年7月5日 下午4:21:54
 * @version:
 */
public class ReportEntity implements Serializable {
	
	private static final long serialVersionUID = 7989700977623077694L;
	private String customName;
	private String customTel;
	private String projectInfoName;
	private String recordTime;
	private String personnelName;
	private String reportLocation;
	private Integer recordDistance; 
	private String confirmRemark;
	private BigDecimal recordStatus;
	private String brokerName;
	
	
	
	
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public BigDecimal getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(BigDecimal recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getCustomTel() {
		return customTel;
	}
	public void setCustomTel(String customTel) {
		this.customTel = customTel;
	}
	public String getProjectInfoName() {
		return projectInfoName;
	}
	public void setProjectInfoName(String projectInfoName) {
		this.projectInfoName = projectInfoName;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getPersonnelName() {
		return personnelName;
	}
	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}
	public String getReportLocation() {
		return reportLocation;
	}
	public void setReportLocation(String reportLocation) {
		this.reportLocation = reportLocation;
	}
	public Integer getRecordDistance() {
		return recordDistance;
	}
	public void setRecordDistance(Integer recordDistance) {
		this.recordDistance = recordDistance;
	}
	public String getConfirmRemark() {
		return confirmRemark;
	}
	public void setConfirmRemark(String confirmRemark) {
		this.confirmRemark = confirmRemark;
	}
	
}
