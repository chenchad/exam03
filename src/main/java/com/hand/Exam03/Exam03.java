package com.hand.Exam03;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonObject;

public class Exam03 {
	public static void main(String[] args) {
        new Get().start();
          try {
				  
			
			
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder  builder =factory.newDocumentBuilder();
	        //创建全新的XML文档
			Document document=builder.newDocument();

			Element root=document.createElement("xml");
			 
			Element stock=document.createElement("stock");
		
			
			
			Element name=document.createElement("name");
			name.setTextContent("汉得信息");
			Element open=document.createElement("open");
			open.setTextContent("20.000");
			Element close=document.createElement("close");
			close.setTextContent("19.400");
			Element current=document.createElement("current");
			current.setTextContent("21.130");
			Element high=document.createElement("high");
			high.setTextContent("21.340");
			Element low=document.createElement("low");
			low.setTextContent("19.540");
			
            stock.appendChild(name);
            stock.appendChild(open);
            stock.appendChild(close);
            stock.appendChild(current);
            stock.appendChild(high);
            stock.appendChild(low);
            root.appendChild(stock);
			document.appendChild(root);
		
			
			//当前文档内容已经创建完毕,下面为简单的输出
		   TransformerFactory transformerFactory =TransformerFactory.newInstance();
		   Transformer transformer =transformerFactory.newTransformer();
		   StringWriter writer=new StringWriter();
		   transformer.transform(new DOMSource(document), new StreamResult(writer));
		   System.out.println(writer.toString());
		   
		   transformer.transform(new DOMSource(document), new StreamResult(new File("xml.xml")));
		
		
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
		 
			e.printStackTrace();
		} catch (TransformerException e) {
 
			e.printStackTrace();
		}
          
          JsonObject object=new JsonObject();
          object.addProperty("naeme", "汉得信息");
          object.addProperty("open",20.000);
          object.addProperty("close", 19.400);
          object.addProperty("current", 21.130);
          object.addProperty("high", 21.340);
          object.addProperty("low", 19.540);
          
          System.out.println(object.toString());
          

             
          
	}
}
class Get extends Thread{
	
	HttpClient client=HttpClients.createDefault();
	
	public void run(){
		HttpGet get=new HttpGet("http://hq.sinajs.cn/list=sz300170");
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