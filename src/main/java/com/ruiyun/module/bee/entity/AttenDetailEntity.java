/**
 * 
 */
package com.ruiyun.module.bee.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className:AttenDetailEntity
 * @author:   wurong
 * @date:     2017年7月11日 下午3:18:54
 * @version:
 */
public class AttenDetailEntity implements Serializable{


	private static final long serialVersionUID = -3907183207502075967L;
	private String attendDate;//考勤日期
	private BigDecimal validDay;//实际出勤
	private String personnelName;//考勤人
	private String amRealSignInTime;//上午实际签到时间
	private String amRealSignOutTime;//上午实际签退时间
	private String amSignInAddress;//上午签到地点
	private String amSignOutAddress;//上午签退地点
	private Integer amSignInStatus;//上午签到状态
	private Integer amSignOutStatus;//上午签退状态

	private String pmRealSignInTime;//下午实际签到时间
	private String pmRealSignOutTime;//下午实际签退时间
	private String pmSignInAddress;//下午签到地点
	private String pmSignOutAddress;//下午签退地点
	private Integer pmSignInStatus;//下午签到状态
	private Integer pmSignOutStatus;//下午签退状态
	private Integer signFloatMinute;//打卡浮动时间(时间范围)
	private Integer signRadius;//打卡距离

	private String signTime;
	private String address;
	private Integer amOrPmStatus;


	public AttenDetailEntity(){

	}
	public AttenDetailEntity(String personnelName,BigDecimal validDay){
		this.personnelName = personnelName;
		this.validDay = validDay;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public Integer getAmOrPmStatus() {
		return amOrPmStatus;
	}

	public void setAmOrPmStatus(Integer amOrPmStatus) {
		this.amOrPmStatus = amOrPmStatus;
	}

	public String getAttendDate() {
		return attendDate;
	}

	public void setAttendDate(String attendDate) {
		this.attendDate = attendDate;
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

	public String getAmRealSignInTime() {
		return amRealSignInTime;
	}

	public void setAmRealSignInTime(String amRealSignInTime) {
		this.amRealSignInTime = amRealSignInTime;
	}

	public String getAmRealSignOutTime() {
		return amRealSignOutTime;
	}

	public void setAmRealSignOutTime(String amRealSignOutTime) {
		this.amRealSignOutTime = amRealSignOutTime;
	}

	public String getAmSignInAddress() {
		return amSignInAddress;
	}

	public void setAmSignInAddress(String amSignInAddress) {
		this.amSignInAddress = amSignInAddress;
	}

	public String getAmSignOutAddress() {
		return amSignOutAddress;
	}

	public void setAmSignOutAddress(String amSignOutAddress) {
		this.amSignOutAddress = amSignOutAddress;
	}

	public Integer getAmSignInStatus() {
		return amSignInStatus;
	}

	public void setAmSignInStatus(Integer amSignInStatus) {
		this.amSignInStatus = amSignInStatus;
	}

	public Integer getAmSignOutStatus() {
		return amSignOutStatus;
	}

	public void setAmSignOutStatus(Integer amSignOutStatus) {
		this.amSignOutStatus = amSignOutStatus;
	}

	public String getPmRealSignInTime() {
		return pmRealSignInTime;
	}

	public void setPmRealSignInTime(String pmRealSignInTime) {
		this.pmRealSignInTime = pmRealSignInTime;
	}

	public String getPmRealSignOutTime() {
		return pmRealSignOutTime;
	}

	public void setPmRealSignOutTime(String pmRealSignOutTime) {
		this.pmRealSignOutTime = pmRealSignOutTime;
	}

	public String getPmSignInAddress() {
		return pmSignInAddress;
	}

	public void setPmSignInAddress(String pmSignInAddress) {
		this.pmSignInAddress = pmSignInAddress;
	}

	public String getPmSignOutAddress() {
		return pmSignOutAddress;
	}

	public void setPmSignOutAddress(String pmSignOutAddress) {
		this.pmSignOutAddress = pmSignOutAddress;
	}

	public Integer getPmSignInStatus() {
		return pmSignInStatus;
	}

	public void setPmSignInStatus(Integer pmSignInStatus) {
		this.pmSignInStatus = pmSignInStatus;
	}

	public Integer getPmSignOutStatus() {
		return pmSignOutStatus;
	}

	public void setPmSignOutStatus(Integer pmSignOutStatus) {
		this.pmSignOutStatus = pmSignOutStatus;
	}

	public Integer getSignFloatMinute() {
		return signFloatMinute;
	}

	public void setSignFloatMinute(Integer signFloatMinute) {
		this.signFloatMinute = signFloatMinute;
	}

	public Integer getSignRadius() {
		return signRadius;
	}

	public void setSignRadius(Integer signRadius) {
		this.signRadius = signRadius;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	private String times;//打卡时间



}
