package com.veriqual.itracker.services.spring.data.access.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.config.AbstractMongoConfiguration;
import com.mongodb.Mongo;

/**
 * Spring MongoDB configuration file
 * 
 */
@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {

	@Override
	public @Bean Mongo mongo() throws Exception {

		return new Mongo("127.0.0.1:27017");
	}

	@Override
	public @Bean MongoTemplate mongoTemplate() throws Exception {

		return new MongoTemplate(mongo(),"yourdb");
	}
		
}
