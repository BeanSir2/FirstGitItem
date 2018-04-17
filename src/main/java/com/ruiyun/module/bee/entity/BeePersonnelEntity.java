package com.ruiyun.module.bee.entity;

import java.io.Serializable;
/**
 * 行销人员entity
 * @author fzy
 * @createTime 2017年6月23日
 */
public class BeePersonnelEntity implements Serializable {
	
	private static final long serialVersionUID = 1091603245608592602L;
	
	private Integer beePersonnelId;
	
	private String personnelName;
	
	private String personnelTel;
	
	private String personnelPermission;
	
	private String idCode;
	
	private Integer parentBeePersonnelId;
	
	private String parentBeePersonnelName;//上级归属人员姓名
	
	private Integer beeTeamId;
	
	private String beeTeamName;
	
	private Integer companyId;
	
	private String companyName;
	
	private String beeLoginAccount;
	
	private String beeLoginPwd;
	
	private Integer status;
	
	private Integer authStatus;
	
	private String authTime;
	
	private String lastLoginTime;
	
	private String openid;
	
	private String createTime;
	
	private Integer createPersonnelId;
	
	private String createPersonnelName;
	
	private Integer merchantId;
	
	private String operate;//操作
	
	private Integer provinceId;
	
	private Integer cityId;
	
	private String searchValue;//输入框的值	
	
	private Integer beeTaskId;
	
	private String taskName;
	
	
	private Double signCount;//签到数
	
	private Integer tokerCount;//拓客数
	
	private Integer reportCount;//报备数
	
	private Integer visitCount;//到访数

	private Integer subscripteCount;//认购数
	
	private Integer turnoverCount;//成交数/签约数
	
	
	

	public BeePersonnelEntity(){
		
	}
	
	public BeePersonnelEntity(Integer beeTeamId){
		this.beeTeamId = beeTeamId;
	}
	
	
	public Integer getBeeTaskId() {
		return beeTaskId;
	}

	public void setBeeTaskId(Integer beeTaskId) {
		this.beeTaskId = beeTaskId;
	}

	public BeePersonnelEntity(Integer beeTeamId,Integer beePersonnelId){
		this.beeTeamId= beeTeamId;
		this.beePersonnelId = beePersonnelId;
	}
	public BeePersonnelEntity(Integer beeTeamId,Integer beePersonnelId, Integer parentBeePersonnelId){
		this.beeTeamId= beeTeamId;
		this.beePersonnelId = beePersonnelId;
		this.parentBeePersonnelId = parentBeePersonnelId;
	}
	
	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
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

	public Integer getBeePersonnelId() {
		return beePersonnelId;
	}

	public void setBeePersonnelId(Integer beePersonnelId) {
		this.beePersonnelId = beePersonnelId;
	}

	public String getPersonnelName() {
		return personnelName;
	}

	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}

	public String getPersonnelTel() {
		return personnelTel;
	}

	public void setPersonnelTel(String personnelTel) {
		this.personnelTel = personnelTel;
	}

	public String getPersonnelPermission() {
		return personnelPermission;
	}

	public void setPersonnelPermission(String personnelPermission) {
		this.personnelPermission = personnelPermission;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public Integer getParentBeePersonnelId() {
		return parentBeePersonnelId;
	}

	public void setParentBeePersonnelId(Integer parentBeePersonnelId) {
		this.parentBeePersonnelId = parentBeePersonnelId;
	}

	public Integer getBeeTeamId() {
		return beeTeamId;
	}

	public void setBeeTeamId(Integer beeTeamId) {
		this.beeTeamId = beeTeamId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBeeLoginAccount() {
		return beeLoginAccount;
	}

	public void setBeeLoginAccount(String beeLoginAccount) {
		this.beeLoginAccount = beeLoginAccount;
	}

	public String getBeeLoginPwd() {
		return beeLoginPwd;
	}

	public void setBeeLoginPwd(String beeLoginPwd) {
		this.beeLoginPwd = beeLoginPwd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public String getAuthTime() {
		return authTime;
	}

	public void setAuthTime(String authTime) {
		this.authTime = authTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCreatePersonnelId() {
		return createPersonnelId;
	}

	public void setCreatePersonnelId(Integer createPersonnelId) {
		this.createPersonnelId = createPersonnelId;
	}

	public String getCreatePersonnelName() {
		return createPersonnelName;
	}

	public void setCreatePersonnelName(String createPersonnelName) {
		this.createPersonnelName = createPersonnelName;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getParentBeePersonnelName() {
		return parentBeePersonnelName;
	}

	public void setParentBeePersonnelName(String parentBeePersonnelName) {
		this.parentBeePersonnelName = parentBeePersonnelName;
	}

	public String getBeeTeamName() {
		return beeTeamName;
	}

	public void setBeeTeamName(String beeTeamName) {
		this.beeTeamName = beeTeamName;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Double getSignCount() {
		return signCount;
	}

	public void setSignCount(Double signCount) {
		this.signCount = signCount;
	}

	public Integer getTokerCount() {
		return tokerCount;
	}

	public void setTokerCount(Integer tokerCount) {
		this.tokerCount = tokerCount;
	}

	public Integer getReportCount() {
		return reportCount;
	}

	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	public Integer getSubscripteCount() {
		return subscripteCount;
	}

	public void setSubscripteCount(Integer subscripteCount) {
		this.subscripteCount = subscripteCount;
	}

	public Integer getTurnoverCount() {
		return turnoverCount;
	}

	public void setTurnoverCount(Integer turnoverCount) {
		this.turnoverCount = turnoverCount;
	}
	
	
}
