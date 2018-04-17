package com.ruiyun.common.base;

import java.util.Random;
import java.util.UUID;

import com.ruiyun.utils.DateUtil;

/**
 * 公用基础父类
 * 
 * @author jiangbo
 *
 */
public class Base {
	protected BaseResult generateResult(boolean isSuccess, String msg) {
		return generateResult(isSuccess, msg, null);
	}

	protected BaseResult generateResult(boolean isSuccess, String msg, Object result) {
		BaseResult obj = new BaseResult();
		obj.setMsg(msg);
		obj.setIsSuccess(isSuccess);
		obj.setResult(result);

		return obj;
	}

	/**
	 * 生成指纹信息
	 * 
	 * @return
	 */
	protected String generateFingerprint() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 生产Id 
	 */
	protected Integer generateId() {
		String yyMMdd = DateUtil.currentTime("mmssSSS");
		return Integer.parseInt((new Random().nextInt(9)+1)+yyMMdd);
	}
}
