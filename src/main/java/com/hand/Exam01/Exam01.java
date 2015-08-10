package com.hand.Exam01;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Exam01 {

	public static void main(String[] args) {
		new Get().start();

	}

}
class Get extends Thread{
	
	HttpClient client=HttpClients.createDefault();
	
	public void run(){
		HttpGet get=new HttpGet("http://www.manning.com/gsmith/SampleChapter1.pdf");
		try {
			
			
			
			
			
			HttpResponse response = client.execute(get);
			HttpEntity entity= response.getEntity();
			String result=EntityUtils.toString(entity,"UTF-8");
			
			System.out.println(result);
			
			
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}