package com.ruiyun.module.bee.entity;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 行销管理_考勤
 * @author fzy
 * @createTime 2017年6月23日
 */
public class BeeAttendEntity implements Serializable{

	private static final long serialVersionUID = 6465916885149697553L;
	
	private Integer beeAttendId;
	
	private Integer beeTaskId;
	
	private Integer beePersonnelId;
	
	private String attendDate;
	
	private Integer signFloatMinute;
	
	private String amSignInTime;
	
	private String amRealSignInTime;
	
	private Integer amSignInStatus;
	
	private String amSignOutTime;
	
	private String amRealSignOutTime;
	
	private Integer amSignOutStatus;
	
	private String pmSignInTime;
	
	private String pmRealSignInTime;
	
	private Integer pmSignInStatus;
	
	private String pmSignOutTime;
	
	private String pmRealSignOutTime;
	
	private Integer pmSignOutStatus;
	
	private Integer attendStatus;
	
	private BigDecimal attendDay;
	
	private BigDecimal validDay;

	public Integer getBeeAttendId() {
		return beeAttendId;
	}

	public void setBeeAttendId(Integer beeAttendId) {
		this.beeAttendId = beeAttendId;
	}

	public Integer getBeeTaskId() {
		return beeTaskId;
	}

	public void setBeeTaskId(Integer beeTaskId) {
		this.beeTaskId = beeTaskId;
	}

	public Integer getBeePersonnelId() {
		return beePersonnelId;
	}

	public void setBeePersonnelId(Integer beePersonnelId) {
		this.beePersonnelId = beePersonnelId;
	}

	public String getAttendDate() {
		return attendDate;
	}

	public void setAttendDate(String attendDate) {
		this.attendDate = attendDate;
	}

	public Integer getSignFloatMinute() {
		return signFloatMinute;
	}

	public void setSignFloatMinute(Integer signFloatMinute) {
		this.signFloatMinute = signFloatMinute;
	}

	public String getAmSignInTime() {
		return amSignInTime;
	}

	public void setAmSignInTime(String amSignInTime) {
		this.amSignInTime = amSignInTime;
	}

	public String getAmRealSignInTime() {
		return amRealSignInTime;
	}

	public void setAmRealSignInTime(String amRealSignInTime) {
		this.amRealSignInTime = amRealSignInTime;
	}

	public Integer getAmSignInStatus() {
		return amSignInStatus;
	}

	public void setAmSignInStatus(Integer amSignInStatus) {
		this.amSignInStatus = amSignInStatus;
	}

	public String getAmSignOutTime() {
		return amSignOutTime;
	}

	public void setAmSignOutTime(String amSignOutTime) {
		this.amSignOutTime = amSignOutTime;
	}

	public String getAmRealSignOutTime() {
		return amRealSignOutTime;
	}

	public void setAmRealSignOutTime(String amRealSignOutTime) {
		this.amRealSignOutTime = amRealSignOutTime;
	}

	public Integer getAmSignOutStatus() {
		return amSignOutStatus;
	}

	public void setAmSignOutStatus(Integer amSignOutStatus) {
		this.amSignOutStatus = amSignOutStatus;
	}

	public String getPmSignInTime() {
		return pmSignInTime;
	}

	public void setPmSignInTime(String pmSignInTime) {
		this.pmSignInTime = pmSignInTime;
	}

	public String getPmRealSignInTime() {
		return pmRealSignInTime;
	}

	public void setPmRealSignInTime(String pmRealSignInTime) {
		this.pmRealSignInTime = pmRealSignInTime;
	}

	public Integer getPmSignInStatus() {
		return pmSignInStatus;
	}

	public void setPmSignInStatus(Integer pmSignInStatus) {
		this.pmSignInStatus = pmSignInStatus;
	}

	public String getPmSignOutTime() {
		return pmSignOutTime;
	}

	public void setPmSignOutTime(String pmSignOutTime) {
		this.pmSignOutTime = pmSignOutTime;
	}

	public String getPmRealSignOutTime() {
		return pmRealSignOutTime;
	}

	public void setPmRealSignOutTime(String pmRealSignOutTime) {
		this.pmRealSignOutTime = pmRealSignOutTime;
	}

	public Integer getPmSignOutStatus() {
		return pmSignOutStatus;
	}

	public void setPmSignOutStatus(Integer pmSignOutStatus) {
		this.pmSignOutStatus = pmSignOutStatus;
	}

	public Integer getAttendStatus() {
		return attendStatus;
	}

	public void setAttendStatus(Integer attendStatus) {
		this.attendStatus = attendStatus;
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
	
	
	
}
