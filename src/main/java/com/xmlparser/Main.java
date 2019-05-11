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

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.net.ssl.HttpsURLConnection;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@Controller
@SpringBootApplication
public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;
  
  private static final String XMLURL = "https://www.watchclub.com/merchant/feed.xml";

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  public ModelAndView mainindex() {
	URL url;
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
	  
	JAXBContext context;
	Unmarshaller um;
	Rss myxml;
	try {
		context = JAXBContext.newInstance(Rss.class);
		um = context.createUnmarshaller();
		myxml = (Rss) um.unmarshal( is );
	} catch (JAXBException e) {
		myxml = null;  
		e.printStackTrace();
	}
	Channel ch = myxml.getChannel();
	ArrayList<Item> items = ch.getItem();
	
	ch.setSize(items.size());
	System.out.println(items.get(2).getId());
	System.out.println(items.get(2).getgId());
	
	ModelAndView mav = new ModelAndView();
    mav.setViewName("index");
    mav.addObject("global", ch);
    //mav.addObject("records", items);
     
    return mav;
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }
}
