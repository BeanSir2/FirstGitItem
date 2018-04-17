/**
 * 
 */
package com.ruiyun.module.bee.entity;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @className:AttenanceEntity
 * @author:   wurong
 * @date:     2017年6月27日 下午4:09:51
 * @version:
 */
public class AttenanceEntity extends HashMap<String, Object>{

	
	private static final long serialVersionUID = 7938456044842290316L;
	private String attendDate; 
	private BigDecimal attendDay;
	private BigDecimal validDay;
	private String personnelName;
	private String taskDate;
	private Integer beePersonnelId;
	private String startDate;
	private String createTime;
	
	public String getCreateTime() {
		return (String) super.get("createTime");
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getAttendDate() {
		return attendDate;
	}
	public void setAttendDate(String attendDate) {
		this.attendDate = attendDate;
	}
	public BigDecimal getAttendDay() {
		return attendDay;
	}
	public void setAttendDay(BigDecimal attendDay) {
		this.attendDay = attendDay;
	}
	public BigDecimal getValidDay() {
		return validDay;
	}
	public void setValidDay(BigDecimal validDay) {
		this.validDay = validDay;
	}
	public String getPersonnelName() {
		return personnelName;
	}
	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}
	public String getTaskDate() {
		return (String) super.get("taskDate");
	}
	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}
	public Integer getBeePersonnelId() {
		return beePersonnelId;
	}
	public void setBeePersonnelId(Integer beePersonnelId) {
		this.beePersonnelId = beePersonnelId;
	}
	
}
