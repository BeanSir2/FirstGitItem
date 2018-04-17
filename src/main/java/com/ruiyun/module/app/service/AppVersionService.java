package com.ruiyun.module.app.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ruiyun.ace.jqgrid.JQGridPageParams;
import com.ruiyun.ace.jqgrid.JQGridResultEntity;
import com.ruiyun.common.base.BaseResult;
import com.ruiyun.common.base.BaseService;
import com.ruiyun.module.app.entity.AppVersion;
import com.ruiyun.module.app.proxy.AppVersionProxy;
import com.ruiyun.utils.StringUtil;

/**
 * 
 * @author yaochen
 * @date 2017年5月31日
 */
@Service("appVersionService")
public class AppVersionService extends BaseService {

	@Autowired
	private AppVersionProxy appVersionProxy;

	/**
	 * 获取列表数据
	 * 
	 * @param pageParams
	 * @param appVersion
	 * @return
	 */
	public JQGridResultEntity<AppVersion> getVersionList(JQGridPageParams pageParams, AppVersion appVersion) {
		try {
			JQGridResultEntity<AppVersion> result = new JQGridResultEntity<AppVersion>();

			if (!StringUtil.isEmpty(appVersion.getCreateTime())) {
				String[] strs = appVersion.getCreateTime().split(" 至 ");
				appVersion.setStartDate(strs[0]);
				appVersion.setEndDate(strs[1]);
			}

			// 分页列表
			List<AppVersion> list = appVersionProxy.getVersionList(pageParams, appVersion);

			// 总行数
			long totalRecords = appVersionProxy.getVersionCount(appVersion);

			return fillJQGrid(result, list, totalRecords, pageParams);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取某个版本信息
	 * 
	 * @param versionId
	 * @return
	 */
	public AppVersion show(Integer versionId) {

		try {
			return appVersionProxy.selectByPrimaryKey(versionId);
		} catch (Exception e) {
			e.printStackTrace();
			return new AppVersion();
		}
	}

	/**
	 * 新增app版本
	 * 
	 * @param appVersion
	 * @return
	 */
	public BaseResult addAppVersion(AppVersion appVersion) {
		try {
			if (appVersionProxy.insertSelective(appVersion) == 1) {
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
	 * 修改app版本信息
	 * 
	 * @param appVersion
	 * @return
	 */
	public BaseResult editAppVersion(AppVersion appVersion) {
		try {
			if (appVersionProxy.updateByPrimaryKeySelective(appVersion) == 1) {
				return generateResult(true, "修改成功");
			} else {
				return generateResult(false, "修改失败，数据库操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return generateResult(false, "修改失败，服务器异常");
		}
	}

	/**
	 * 启用
	 * 
	 * @param versionId
	 * @return
	 */
	public BaseResult enable(Integer versionId) {
		try {
			if (appVersionProxy.enable(versionId) == 1) {
				return generateResult(true, "启用成功");
			} else {
				return generateResult(false, "启用失败，数据库操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return generateResult(false, "启用失败，服务器异常");
		}
	}

	/**
	 * 废除
	 * 
	 * @param versionId
	 * @return
	 */
	public BaseResult disable(Integer versionId) {
		try {
			if (appVersionProxy.disable(versionId) == 1) {
				return generateResult(true, "废除成功");
			} else {
				return generateResult(false, "废除失败，数据库操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return generateResult(false, "废除失败，服务器异常");
		}
	}

	/**
	 * 上传APK
	 * @param filePath
	 * @param apkFile
	 * @return BaseResult  
	 * @author chentianjin
	 * @date 2017年11月30日
	 */
	public BaseResult uploadApk(String filePath, MultipartFile apkFile) {
		try {
			String fileName = apkFile.getOriginalFilename();
			File file = new File(filePath);
			if (!file.exists()){
				file.mkdirs();
			}
			apkFile.transferTo(new File(filePath +File.separator+ fileName));
			return generateResult(true, "上传成功");
		} catch (Exception e) {
			e.printStackTrace();
			return generateResult(false, "上传失败");
		}
		
	}

}
