import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

import com.ruiyun.utils.security.MD5Utils;

public class TestType {

	@Test
	public void test1() {

	}

	@Test
	public void test2() throws IOException{
			//869588eff1922820c54c9dd456d55b8f
				String md5EncodePassword = MD5Utils.MD5EncodePassword("2577288789");
				System.out.println(md5EncodePassword);
				
	    Properties po = new Properties();
	    
	    InputStream resourceAsStream = this.getClass().getResourceAsStream("md5.properties");
	    
	    po.load(resourceAsStream);
	    
	    String string  = (String)po.get("pwd");
	    System.out.println(string);
	    
	    if(md5EncodePassword.equals(string)){
	    	System.out.println(true);
	    }else{
	    	System.out.println(false);
	    }
	
	}
}
