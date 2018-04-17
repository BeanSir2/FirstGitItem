package com.ruiyun.module.bee.entity;

import java.io.Serializable;
/**
 * 行销管理_行销团队管理
 * @author fzy
 * @createTime 2017年6月23日
 */
public class BeeTeamEntity implements Serializable {

	private static final long serialVersionUID = 4439642319069143672L;
	
	private Integer beeTeamId;
	
	private String teamCode;
	
	private String teamName;
	
	private Integer teamCount;//团队人员数量
	
	private Integer beePersonnelId;
	
	private String beePersonnelName;
	
	private Integer parentBeePersonnelId;
	
	private String parentBeePersonnelName;//上级归属
	
	private Integer status;
	
	private Integer companyId;
	
	private String companyName;

	private String createTime;
	
	private Integer merchantId;
	
	private Integer provinceId;
	
	private Integer cityId;
	
	public BeeTeamEntity(){
		
	}
	public BeeTeamEntity(Integer beeTeamId,Integer status){
		this.beeTeamId = beeTeamId;
		this.status = status;
	}
	
	public BeeTeamEntity(Integer beeTeamId,Integer beePersonnelId, Integer parentBeePersonnelId, Integer status){
		this.beeTeamId = beeTeamId;
		this.beePersonnelId = beePersonnelId;
		this.parentBeePersonnelId = parentBeePersonnelId;
		this.status = status;
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

	public Integer getTeamCount() {
		return teamCount;
	}

	public void setTeamCount(Integer teamCount) {
		this.teamCount = teamCount;
	}

	public Integer getBeeTeamId() {
		return beeTeamId;
	}

	public void setBeeTeamId(Integer beeTeamId) {
		this.beeTeamId = beeTeamId;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getBeePersonnelId() {
		return beePersonnelId;
	}

	public void setBeePersonnelId(Integer beePersonnelId) {
		this.beePersonnelId = beePersonnelId;
	}

	public Integer getParentBeePersonnelId() {
		return parentBeePersonnelId;
	}

	public void setParentBeePersonnelId(Integer parentBeePersonnelId) {
		this.parentBeePersonnelId = parentBeePersonnelId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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

	public String getBeePersonnelName() {
		return beePersonnelName;
	}

	public void setBeePersonnelName(String beePersonnelName) {
		this.beePersonnelName = beePersonnelName;
	}

	public String getParentBeePersonnelName() {
		return parentBeePersonnelName;
	}

	public void setParentBeePersonnelName(String parentBeePersonnelName) {
		this.parentBeePersonnelName = parentBeePersonnelName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	
	

}
