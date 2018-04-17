package com.ruiyun.module.app.proxy;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ruiyun.ace.jqgrid.JQGridPageParams;
import com.ruiyun.module.app.entity.AppVersion;

/**
 * app版本管理proxy
 * 
 * @author yaochen
 * @date 2017年5月31日
 */
@Repository("appVersionProxy")
public interface AppVersionProxy {

	/*
	 * 获取版本列表条数
	 */
	public Long getVersionCount(@Param("appVersion") AppVersion appVersion);

	/*
	 * 获取版本列表数据
	 */
	public List<AppVersion> getVersionList(@Param("page") JQGridPageParams pageParams, @Param("appVersion") AppVersion appVersion);

	/*
	 * 通过ID查询
	 */
	public AppVersion selectByPrimaryKey(@Param("versionId") Integer versionId);

	/*
	 * 插入数据
	 */
	public Integer insertSelective(@Param("appVersion") AppVersion appVersion);

	/*
	 * 修改数据
	 */
	public Integer updateByPrimaryKeySelective(@Param("appVersion") AppVersion appVersion);

	/*
	 * 启用
	 */
	public int enable(@Param("versionId") Integer versionId);

	/*
	 * 废除
	 */
	public int disable(@Param("versionId") Integer versionId);

}
