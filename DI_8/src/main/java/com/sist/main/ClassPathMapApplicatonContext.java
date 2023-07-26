package com.sist.main;
import java.io.File;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
public class ClassPathMapApplicatonContext implements ApplicationContext {
	private Map clsMap=new HashMap();
	
	public ClassPathMapApplicatonContext(String path) {
		try {
			SAXParserFactory spf=SAXParserFactory.newInstance();
			SAXParser sp=spf.newSAXParser();
			XMLParse xm=new XMLParse();
			sp.parse(new File(path), xm);
			clsMap=xm.getMap();
		} catch (Exception e) {}
	}
	
	@Override
	public Object getBean(String key) {
		return clsMap.get(key);
	}
}
