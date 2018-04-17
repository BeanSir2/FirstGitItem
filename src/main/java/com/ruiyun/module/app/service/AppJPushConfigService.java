package com.ruiyun.module.app.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiyun.ace.jqgrid.JQGridPageParams;
import com.ruiyun.ace.jqgrid.JQGridResultEntity;
import com.ruiyun.common.base.BaseResult;
import com.ruiyun.common.base.BaseService;
import com.ruiyun.module.app.entity.AppJPushConfig;
import com.ruiyun.module.app.proxy.AppJPushConfigProxy;

/**
 * app极光推送配置service
 * 
 * @author yaochen
 * @date 2017年6月5日
 */
@Service("appJPushConfigService")
public class AppJPushConfigService extends BaseService {

	@Autowired
	private AppJPushConfigProxy appJPushConfigProxy;

	/**
	 * 获取某个配置信息
	 * 
	 * @param jpushConfigId
	 * @return
	 */
	public AppJPushConfig show(Integer jpushConfigId) {
		try {
			AppJPushConfig config = appJPushConfigProxy.selectByPrimaryKey(jpushConfigId);
			String time = new BigDecimal(config.getTime()).divide(new BigDecimal(24 * 60 * 60), 1, RoundingMode.HALF_UP).toString();
			config.setTime(time);
			return config;
		} catch (Exception e) {
			e.printStackTrace();
			return new AppJPushConfig();
		}
	}

	/**
	 * 获取列表数据
	 * 
	 * @param pageParams
	 * @param appName
	 * @return
	 */
	public JQGridResultEntity<AppJPushConfig> getJPushConfigList(JQGridPageParams pageParams, String appName) {
		try {
			JQGridResultEntity<AppJPushConfig> result = new JQGridResultEntity<AppJPushConfig>();

			// 分页列表
			List<AppJPushConfig> list = appJPushConfigProxy.getJPushConfigList(pageParams, appName);

			// 总行数
			long totalRecords = appJPushConfigProxy.getJPushConfigCount(appName);

			return fillJQGrid(result, list, totalRecords, pageParams);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 保存新增
	 */
	public BaseResult addAppVersion(AppJPushConfig config) {
		if (appJPushConfigProxy.selectByAppName(config.getAppName()) != 0) {
			return generateResult(false, "一个app只能创建一个配置信息！");
		}
		try {
			BigDecimal bd = new BigDecimal(config.getTime());
			config.setTime(String.valueOf(bd.multiply(new BigDecimal(24 * 60 * 60)).intValue()));
		} catch (Exception e1) {
			e1.printStackTrace();
			return generateResult(false, "请填入正确的时长！");
		}
		try {
			if (appJPushConfigProxy.insertSelective(config) == 1) {
				return generateResult(true, "新增成功");
			} else {
				return generateResult(false, "新增失败，数据库操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return generateResult(false, "新增失败，服务器异常");
		}
	}

	/**
	 * 保存修改
	 * 
	 * @param config
	 * @return
	 */
	public BaseResult editAppVersion(AppJPushConfig config) {
		try {
			BigDecimal bd = new BigDecimal(config.getTime());
			config.setTime(String.valueOf(bd.multiply(new BigDecimal(24 * 60 * 60)).intValue()));
		} catch (Exception e1) {
			e1.printStackTrace();
			return generateResult(false, "请填入正确的时长！");
		}
		try {
			if (appJPushConfigProxy.updateByPrimaryKeySelective(config) == 1) {
				return generateResult(true, "修改成功");
			} else {
				return generateResult(false, "修改失败，数据库操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return generateResult(false, "修改失败，服务器异常");
		}
	}
}
