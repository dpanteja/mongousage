package com.dandi.api;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.dandi.api.repository.UserRepository;
import com.mongodb.Mongo;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@Configuration
@EnableMongoRepositories(basePackageClasses = {UserRepository.class},
        mongoTemplateRef = "template1",
        includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*UserRepository")}
)
public class MongoConfig {

	@Value("${mongo-url}")
	private String mongoURL;
	
    @Primary
    @Bean
    public MongoDbFactory mongoDbFactory1() throws UnknownHostException {

    	MongoClientURI uri = new MongoClientURI(mongoURL);

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("mydb");
        return new SimpleMongoDbFactory(mongoClient, "mydb");
    }

    @Primary
    @Bean
    public MongoTemplate template1() throws UnknownHostException {
        return new MongoTemplate(mongoDbFactory1());
    }
}