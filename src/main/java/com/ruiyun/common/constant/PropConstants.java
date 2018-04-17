package com.ruiyun.common.constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.ruiyun.utils.ClassPathUtil;
import com.ruiyun.utils.ProFileReader;

/**
 * 配置文件常量 静态加载配置文件里面的属性
 * 
 * @author Jiangbo
 *
 */
public class PropConstants {
	// 本地图片路径
	public static String UPLOAD_DISK_PATH = "";
	// http路径
	public static String UPLOAD_HTTP_PATH = "";
	
	public static String UPLOAD_BASE_FOLDER = "";
	
	/** 水印图片LOGO路径 */
	public static String LOGO_DISK_PATH = "";
	
	// 楼盘扫码跳转链接
	public static String BUILDING_LINK = "";
	
	/** 调用接口账号 */
	public static String PINGAN_API_NAME = "";
	
	/** 调用接口秘钥 */
	public static String PINGAN_API_PASSWORD = "";
	
	/** 接口令牌 */
	public static String PINGAN_TOKEN = "";
	
	/** 平安-根据订单编号查询系统回调信息 */
	public static String PINGAN_FIND_POLICYINFO_BY_ORDERID_HTTP = "";
	
	/** 公众号APPID */
	public static String WEIXIN_APP_ID = "";
	
	/** 预约看房-分配客户的商户ID */
	public static String MANAGER_MERCHANT_ID = "";

	static {
		File file = new File(ClassPathUtil.getPath() + "resource/system.properties");
		ProFileReader uploadPropFile;
		try {
			uploadPropFile = new ProFileReader(new FileInputStream(file));
			UPLOAD_DISK_PATH = uploadPropFile.getParamValue("UPLOAD_DISK_PATH");
			UPLOAD_HTTP_PATH = uploadPropFile.getParamValue("UPLOAD_HTTP_PATH");
			UPLOAD_BASE_FOLDER = uploadPropFile.getParamValue("UPLOAD_BASE_FOLDER");
			LOGO_DISK_PATH = uploadPropFile.getParamValue("LOGO_DISK_PATH");
			BUILDING_LINK = uploadPropFile.getParamValue("BUILDING_LINK");
			PINGAN_API_NAME = uploadPropFile.getParamValue("PINGAN_API_NAME");
			PINGAN_API_PASSWORD = uploadPropFile.getParamValue("PINGAN_API_PASSWORD");
			PINGAN_TOKEN = uploadPropFile.getParamValue("PINGAN_TOKEN");
			PINGAN_FIND_POLICYINFO_BY_ORDERID_HTTP = uploadPropFile.getParamValue("PINGAN_FIND_POLICYINFO_BY_ORDERID_HTTP");
			WEIXIN_APP_ID = uploadPropFile.getParamValue("WEIXIN_APP_ID");
			MANAGER_MERCHANT_ID = uploadPropFile.getParamValue("MANAGER_MERCHANT_ID");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
