/**
 * 
 */
package com.ruiyun.module.bee.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className:SubscriptionEntity
 * @author:   wurong
 * @date:     2017年7月5日 下午6:31:29
 * @version:
 */
public class SubscriptionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6013393082820399777L;
	private String personnelName;
	private String customName;
	private String customTel;
	private String projectInfoName;
	private String recordTime;
	private String reportLocation;
	private Integer recordDistance;
	private String house_building;
	private String houseNnit;
	private BigDecimal houseAmount;
	private String realBuyTime;
	private Integer roomTotal;
	private Integer livingRoomTotal;
	private Integer bathroomTotal;
	private String brokerName;
	
	private String subscription;//认购房源
	private String apartment;//户型
	
	
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getSubscription() {
		return subscription;
	}
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getPersonnelName() {
		return personnelName;
	}
	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
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
	public String getHouse_building() {
		return house_building;
	}
	public void setHouse_building(String house_building) {
		this.house_building = house_building;
	}
	public String getHouseNnit() {
		return houseNnit;
	}
	public void setHouseNnit(String houseNnit) {
		this.houseNnit = houseNnit;
	}
	public BigDecimal getHouseAmount() {
		return houseAmount;
	}
	public void setHouseAmount(BigDecimal houseAmount) {
		this.houseAmount = houseAmount;
	}
	public String getRealBuyTime() {
		return realBuyTime;
	}
	public void setRealBuyTime(String realBuyTime) {
		this.realBuyTime = realBuyTime;
	}
	public Integer getRoomTotal() {
		return roomTotal;
	}
	public void setRoomTotal(Integer roomTotal) {
		this.roomTotal = roomTotal;
	}
	public Integer getLivingRoomTotal() {
		return livingRoomTotal;
	}
	public void setLivingRoomTotal(Integer livingRoomTotal) {
		this.livingRoomTotal = livingRoomTotal;
	}
	public Integer getBathroomTotal() {
		return bathroomTotal;
	}
	public void setBathroomTotal(Integer bathroomTotal) {
		this.bathroomTotal = bathroomTotal;
	}
	
}
