package com.ruiyun.module.bee.entity;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 行销管理_签到明细
 * @author fzy
 * @createTime 2017年6月23日
 */
public class BeeSignEntity implements Serializable {

	private static final long serialVersionUID = 951608676132937733L;
	
	private Integer beeSignId;
	
	private Integer beeTaskId;
	
	private Integer beePersonnelId;
	
	private String signTime;
	
	private BigDecimal longitude;
	
	private BigDecimal latitude;
	
	private String address;
	
	private Integer beeSignCount;//签到数量
	
	
	public BeeSignEntity(){
		
	}
	
	public BeeSignEntity(BigDecimal longitude, BigDecimal latitude, String address){
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
	}

	public Integer getBeeSignCount() {
		return beeSignCount;
	}

	public void setBeeSignCount(Integer beeSignCount) {
		this.beeSignCount = beeSignCount;
	}

	public Integer getBeeSignId() {
		return beeSignId;
	}

	public void setBeeSignId(Integer beeSignId) {
		this.beeSignId = beeSignId;
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

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
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
	
	
}
