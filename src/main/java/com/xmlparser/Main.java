/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xmlparser;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;
import javax.xml.transform.stream.StreamSource;

import java.io.IOException;
import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


@Controller
@SpringBootApplication
public class Main {
  
  private static final String XMLURL = "https://www.watchclub.com/merchant/feed.xml";

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  public ModelAndView mainindex() {
	URL url=null;
	InputStream is = null;
	try {
		url = new URL( XMLURL );
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		is = conn.getInputStream();
	} catch (MalformedURLException e1) {
		url = null;
		e1.printStackTrace();
	} catch (IOException e) {
		is = null;
		e.printStackTrace();
	}
	
	XMLInputFactory xif = XMLInputFactory.newFactory();
	xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true); // this is the magic line
	StreamSource source = new StreamSource(is);
	XMLStreamReader xsr = null;
	try {
		xsr = xif.createXMLStreamReader(source);
	} catch (XMLStreamException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	JAXBContext context;
	
	Unmarshaller um;
	Rss myxml;
	try {
		context = JAXBContext.newInstance(Rss.class);
		um = context.createUnmarshaller();
		XXHeaderXMLReader xxHeaderXMLReader = new XXHeaderXMLReader(xsr);
		myxml = (Rss) um.unmarshal( xxHeaderXMLReader );
	} catch (JAXBException e) {
		myxml = null;  
		e.printStackTrace();
	}
	Channel ch = myxml.getChannel();
	ArrayList<Item> items = ch.getItem();
	
	ch.setSize(items.size());
	System.out.println(items.get(2).getId());
	
	ModelAndView mav = new ModelAndView();
    mav.setViewName("index");
    mav.addObject("global", ch);
    //mav.addObject("records", items);
     
    return mav;
  }
}
