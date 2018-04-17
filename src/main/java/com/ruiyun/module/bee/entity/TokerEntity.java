/**
 * 
 */
package com.ruiyun.module.bee.entity;

import java.io.Serializable;

/**
 * @className:TokerEntity
 * @author:   wurong
 * @date:     2017年7月5日 下午3:08:05
 * @version:
 */
public class TokerEntity implements Serializable{

	private static final long serialVersionUID = 4911499085717927762L;
	private String customName;
	private String customTel;
	private String projectInfoName;
	private String recordTime;
	private String personnelName;
	private String brokerName;
	
	
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
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
	
}
