package com.ruiyun.common.vcode;

public class VerifyCodeUtils {
	public void test() {
		if(getPara("NECaptchaValidate")==null||getPara("NECaptchaValidate").equals("")){  
//		    setAttr("state", new State("/","滑动验证失败,请重新尝试!","5","102"));  
//		    renderJsp("/new_nei/funm.jsp");  
		    return;  
		}  
		RuiyunVerificationCode wy=new RuiyunVerificationCode((String.valueOf(getPara("NECaptchaValidate"))),"");  
		String validate =getPara("NECaptchaValidate");  
		NECaptchaVerifier verifier = new NECaptchaVerifier(wy.getCaptchaId(), new NESecretPair(wy.getSecretId(), wy.getKey()));  
		boolean isValid = verifier.verify(validate, ""); // 发起二次校验  
		  
		      System.out.println("validate = " + validate + ", isValid = " + isValid);  
		      if (isValid) {  
		          System.out.println("滑动验证二次校验成功!");  
		      } else {  
//		        setAttr("state", new State("/","滑动验证失败,请重新尝试!","5","102"));  
//		    renderJsp("/new_nei/funm.jsp");  
		    return;  
		      }  
	}
	
	public String getPara(String str) {
		return str;
	}
}
