package com.sist.main;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Method;
/*
	JAXP
		DOM
		SAX => Spring,MyBatis (실제 데이터만 추출)
		
		<?xml version="1.0"?> => startDocument
		<beans> => startElement()
		  <bean> => startElement()
		  	=< characters()
		  </bean> => endElement()
		  <bean> => startElement()
		  </bean> => endElement()
		</beans> => endElement()
		-------------------------- endDocument()
 */
import java.util.*;
public class XMLParse extends DefaultHandler{
	private Map map=new HashMap();
	
	public Map getMap() {
		return map;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try {
			if(qName.equals("bean")) {
				String id=attributes.getValue("id"); // sa
				String cls=attributes.getValue("class");
				String sabun=attributes.getValue("p:sabun");
				String name=attributes.getValue("p:name");
				String dept=attributes.getValue("p:dept");
				String job=attributes.getValue("p:job");
				String[] aName= {sabun,name,dept,job};
				String[] ss= {attributes.getQName(2),
						attributes.getQName(3),
						attributes.getQName(4),
						attributes.getQName(5)};
				// 메모리 할당
				Class clsName=Class.forName(cls);
				Object obj=clsName.getDeclaredConstructor().newInstance();
				Method[] methods=clsName.getDeclaredMethods();
				for(Method m:methods) {
					String mName=m.getName(); //메소드 이름
					for(int i=0;i<ss.length;i++) {
						if(mName.equalsIgnoreCase("set"+ss[i].substring(ss[i].indexOf(":")+1))) {
							if(i==0) {
								m.invoke(obj, Integer.parseInt(aName[i]));
							} else {
								m.invoke(obj, aName[i]);
							}
						}
					}
					//if(mName.equalsIgnoreCase("set"+))
				}
				map.put(id, obj);
			}
		} catch (Exception e) {}
	}
}
