package com.ruiyun.module.app.entity;

import java.io.Serializable;

public class AppJPushConfig implements Serializable {
	
	private static final long serialVersionUID = -5867049368188826512L;

	private Integer jpushConfigId;

    private String appName;

    private String appKey;

    private String masterSecret;

    private String time;

    private String sound;

    private Integer contentAvailable;

    public Integer getJpushConfigId() {
        return jpushConfigId;
    }

    public void setJpushConfigId(Integer jpushConfigId) {
        this.jpushConfigId = jpushConfigId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret == null ? null : masterSecret.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound == null ? null : sound.trim();
    }

    public Integer getContentAvailable() {
        return contentAvailable;
    }

    public void setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
    }
}