package com.ruiyun.module.app.entity;

import java.io.Serializable;

public class AppVersion implements Serializable {
	private static final long serialVersionUID = 1814535896549987862L;

	private Integer versionId;

	private String appName;

	private Integer systemType;

	private String versionNum;

	private Integer versionServiceNum;

	private String createTime;

	private Integer isMustUpdate;

	private String downloadUrl;

	private Integer status;

	private String startDate;

	private String endDate;

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName == null ? null : appName.trim();
	}

	public Integer getSystemType() {
		return systemType;
	}

	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum == null ? null : versionNum.trim();
	}

	public Integer getVersionServiceNum() {
		return versionServiceNum;
	}

	public void setVersionServiceNum(Integer versionServiceNum) {
		this.versionServiceNum = versionServiceNum;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getIsMustUpdate() {
		return isMustUpdate;
	}

	public void setIsMustUpdate(Integer isMustUpdate) {
		this.isMustUpdate = isMustUpdate;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
}