package com.solace.mymini.mini;

import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.text.SimpleDateFormat;

/**
 * 封装对外访问方法
 * @author liuyazhuang
 *
 */
public class WXCore {
	
	private static final String WATERMARK = "watermark";
	private static final String APPID = "appid";
	/**
	 * 解密数据
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String appId, String encryptedData, String sessionKey, String iv){
		String result = "";
		try {
			AES aes = new AES();  
		    byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
		    if(null != resultByte && resultByte.length > 0){  
		        result = new String(WxPKCS7Encoder.decode(resultByte));  
		    	JSONObject jsonObject = JSONObject.fromObject(result);
		    	String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);
		    	if(!appId.equals(decryptAppid)){
		    		result = "";
		    	}
	        }  
		} catch (Exception e) {
			result = "";
			e.printStackTrace();
		}
	    return result;
	}
	
	
	public static void main(String[] args) throws Exception{
	   String appId = "wx3ea988d97b2111b9";
	   String encryptedData = "Qkejs9ZQ0Zt8o5qkzmpdpYRSoGFz/6KP744Ola9D2r223K+tnqzfcorh7M7ACeK3RMVt8BNH44Tz6ObTCmhQhB6pvfvEO5uBzOJnbA58R4SMGRhSKQDx+gtajXNPXqis1a0TZNGDOe4I4vEmC2IXUZu84wYeusWQdK1ia57h0kLkh6HPVpPrSHbI+3zIS5xW9A0Hfvxb62/Bzfl9hFxcPLX0TgY41TJXLj0iJK6CgEZpWPUP4D5quEtJ4uXj7JdE8E9nSb5LUO1qJbP+Q0pfM+mN7nVaT7TFEOIMkiC5QzA0Yns5AArokXJDKChR3PlQJhDMN1RqUrOmxsDUwzuwGbr+GQj/S5f6Goj7Nuk0Lx0rDtWYqjfyFNecHPPzJpy0hptKjCOZPPL/cT14uSHl5+8g45j4bTRKy3KTVS5WJBn+NC30X98Fcmc5C/G9V6zIAtUvmSWYy41vy1OzpPvrAenC3U5ym53NqqDYpRILAS4=";
	   String sessionKey = "BYHqcJxF3BZzG4o4oZB6Dg==";
	   String iv = "fzF++QqrF63olK8GK/uLCA==";
		String decrypt = decrypt(appId, encryptedData, sessionKey, iv);
		System.out.println(decrypt);
        JSONObject jsonObject = JSONObject.fromObject(decrypt);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject watermark = JSONObject.fromObject(jsonObject.get("watermark"));
        String timestamp = df.format(watermark.get("timestamp"));
        System.out.println(timestamp);
    }
}