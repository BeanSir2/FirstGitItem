package com.ruiyun.module.app.proxy;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ruiyun.ace.jqgrid.JQGridPageParams;
import com.ruiyun.module.app.entity.AppJPushConfig;

/**
 * app极光推送配置proxy
 * 
 * @author yaochen
 * @date 2017年6月5日
 */
@Repository("appJPushConfigProxy")
public interface AppJPushConfigProxy {

	/*
	 * 列表数据
	 */
	public List<AppJPushConfig> getJPushConfigList(@Param("page") JQGridPageParams pageParams, @Param("appName") String appName);

	/*
	 * 总条数
	 */
	public long getJPushConfigCount(@Param("appName") String appName);

	/*
	 * 通过主键查询
	 */
	public AppJPushConfig selectByPrimaryKey(@Param("jpushConfigId") Integer jpushConfigId);

	/*
	 * 新增数据
	 */
	public int insertSelective(@Param("config") AppJPushConfig config);

	/*
	 * 修改数据
	 */
	public int updateByPrimaryKeySelective(@Param("config") AppJPushConfig config);

	/*
	 * 通过appname查询
	 */
	public Long selectByAppName(@Param("appName") String appName);

}
