/*
 * Copyright 2005-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.veriqual.itracker.services.spring;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.document.mongodb.MongoOperations;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Service;

import com.veriqual.itracker.services.spring.data.access.user.User;
import com.veriqual.itracker.services.spring.data.access.DataAccess;
/**
 * Simple stub implementation of {@link HumanResourceService}, which does nothing but logging.
 *
 * @author Arjen Poutsma
 */
@Service
public class StubHumanResourceService implements HumanResourceService {

	@Autowired
    private DataAccess dataAccess;
    private static final Log logger = LogFactory.getLog(StubHumanResourceService.class);

    public void bookHoliday(Date startDate, Date endDate, String name) {
        logger.info("Booking holiday for [" + startDate + "-" + endDate + "] for [" + name + "] ");
//        addtoDB("Booking holiday for [" + startDate + "-" + endDate + "] for [" + name + "] ");
        User user = new User("1005", "yong", "mook kim", 30);
        getDataAccess().saveUser(user);
    }
    
    private void addtoDB(String log) {
ApplicationContext ctx = new GenericXmlApplicationContext("mongo-config.xml");
    	
    	MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
//    	mongoOperation.save("userprofile","abc");
//    	List<String> logs =  
//            	mongoOperation.getCollection("logs", String.class);
//            System.out.println("Number of user = " + logs.size());
//            
            User user = new User("1004", "yong", "mook kim", 30);
          
          //save
          mongoOperation.save("userprofile",user);
          
          //find
          User savedUser = mongoOperation.findOne("userprofile",
          		new Query(Criteria.where("id").is("1004")),
  				User.class);
          
          System.out.println("savedUser : " + savedUser);
    }
    
    public DataAccess getDataAccess() {
    	System.out.println("===========================================");
    	System.out.println(dataAccess != null);
    	System.out.println("===========================================");
		return dataAccess;
	}
}
