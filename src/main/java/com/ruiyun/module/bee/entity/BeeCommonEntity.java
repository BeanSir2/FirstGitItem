package com.ruiyun.module.bee.entity;

import java.io.Serializable;
/**
 * 该实体只适用于查询签约、拓客数的公共查询
 * @author fzy
 * @createTime 2017年6月29日
 */
public class BeeCommonEntity implements Serializable {

	private static final long serialVersionUID = -5590730604294741783L;
	
	private Integer beePersonnelId;
	
	private Integer beeTeamId;
	
	private Integer beeTaskId;
	
	private String startDate;
	
	private String endDate;
	
	private Integer provinceId;
	
	private Integer cityId;
	
	private Integer buildingProjectId;
	

	public Integer getBeePersonnelId() {
		return beePersonnelId;
	}

	public void setBeePersonnelId(Integer beePersonnelId) {
		this.beePersonnelId = beePersonnelId;
	}

	public Integer getBeeTeamId() {
		return beeTeamId;
	}

	public void setBeeTeamId(Integer beeTeamId) {
		this.beeTeamId = beeTeamId;
	}

	public Integer getBeeTaskId() {
		return beeTaskId;
	}

	public void setBeeTaskId(Integer beeTaskId) {
		this.beeTaskId = beeTaskId;
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

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getBuildingProjectId() {
		return buildingProjectId;
	}

	public void setBuildingProjectId(Integer buildingProjectId) {
		this.buildingProjectId = buildingProjectId;
	}
	
	public BeeCommonEntity(){
		
	}
	public BeeCommonEntity(Integer provinceId,Integer cityId){
		this.provinceId = provinceId;
		this.cityId = cityId;
	}
	
	public BeeCommonEntity(String startDate,String endDate){
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public BeeCommonEntity(Integer beeTeamId,Integer beeTaskId,Integer beePersonnelId ){
		this.beeTeamId = beeTeamId;
		this.beeTaskId = beeTaskId;
		this.beePersonnelId = beePersonnelId;
	}

}
