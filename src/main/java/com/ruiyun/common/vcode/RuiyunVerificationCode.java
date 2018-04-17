package com.ruiyun.common.vcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 验证码参数设置
 * @author HKCHEN
 *
 */
public class RuiyunVerificationCode {
	private final String key = "填你自己的"; // 密钥
	private String url = "http://c.dun.163yun.com/api/v1/verify"; // 请求地址
	private String captchaId = "填你自己的"; // 验证码ID
	private String validate = ""; // 提交二次校验的验证数据，即NECaptchaValidate值
	private String user = ""; // 用户信息，值可为空
	private String secretId = "<span style='font-family:Arial,Helvetica,sans-serif;'>填你自己的</span><span style='font-family:Arial,Helvetica,sans-serif;'>"; // 密钥对id</span>
	private String version = "v1"; // 版本信息，固定值v1
	private String timestamp = System.currentTimeMillis() + ""; // 当前时间戳的毫秒值，例1480395193000
	private String nonce = new Random().nextInt(10) + ""; // 随机正整数，与 timestamp
															// 联合起来，用于防止重放攻击
	private String signature = ""; // 签名信息，见签名计算
	private Map<String, String> params = new HashMap<String, String>();

	public RuiyunVerificationCode(String validate, String user) {
		this.validate = validate;
		this.user = user;
		this.params.put("captchaId", captchaId);
		this.params.put("validate", validate);
		this.params.put("user", user);
		this.params.put("secretId", secretId);
		this.params.put("version", version);
		this.params.put("timestamp", timestamp);
		this.params.put("nonce", nonce);
	}

	/**
	 * 生成签名信息
	 * 
	 * @param secretKey 产品私钥
	 * @param params 接口请求参数名和参数值map，不包括signature参数名
	 * @return
	 */
	public void genSignature() throws Exception {
		// 1. 参数名按照ASCII码表升序排序
		String[] keys = params.keySet().toArray(new String[0]);
		Arrays.sort(keys);

		// 2. 按照排序拼接参数名与参数值
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			sb.append(key).append(params.get(key));
		}
		// 3. 将secretKey拼接到最后
		sb.append(this.key);

		// 4. MD5是128位长度的摘要算法，转换为十六进制之后长度为32字符
		signature = DigestUtils.md5Hex(sb.toString().getBytes("UTF-8"));
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUrl() {
		return url;
	}

	public String getCaptchaId() {
		return captchaId;
	}

	public String getSecretId() {
		return secretId;
	}

	public String getVersion() {
		return version;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public String getSignature() {
		return signature;
	}

	public String getKey() {
		return key;
	}
}
