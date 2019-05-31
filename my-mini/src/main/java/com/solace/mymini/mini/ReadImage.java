package com.solace.mymini.mini;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadImage {
	
	public static InputStream getInputStream(){
		InputStream inputStream=null;
		HttpURLConnection httpURLConnection=null;
		try{
			URL url=new URL("https://wx.qlogo.cn/mmopen/vi_32/AT5yh2JK0vQGicicnDbBGQ8Rlc5tJOzsCZAxgYWX4AibOuTP9aBgIxIjZo9SDibqzgicQs8q75A6nZdRlCNKibUa3R2g/132");
			if(url!=null){
				httpURLConnection=(HttpURLConnection) url.openConnection();
				httpURLConnection.setConnectTimeout(3000);
				httpURLConnection.setRequestMethod("GET");
				int responseCode=httpURLConnection.getResponseCode();
				if(responseCode==200){
					inputStream=httpURLConnection.getInputStream();
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		
		
		return inputStream;
		
	}
	
	public static void saveImage(){
		InputStream inputStream=getInputStream();
		FileOutputStream fileOutputStream=null;
		byte[] data=new byte[1024];
		int len=0;
		try{
	    fileOutputStream=new FileOutputStream("D:\\2.jpg");
		while((len=inputStream.read(data))!=-1){
		fileOutputStream.write(data,0,len);	
			
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
				fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         saveImage();
	}
 
}
