package com.je.enterprise.mievento.domain.configuration;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.je.enterprise.mievento.domain.utils.BigDecimalConverter;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;
import com.mongodb.WriteConcern;


@Configuration
public class MongoConfiguration {

	public static final String DB_NAME = "mi-evento";
	
	
	 @Bean
    public DB getDb() throws UnknownHostException, MongoException {
        MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
        DB db = mongoURI.connectDB();
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());

        return db;
    }

	@Bean
	public Datastore MongoConnectionManager() {
		MongoClient client = null;
		try {
			client = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		Morphia morphia = new Morphia();
		morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
		
		Datastore ds = morphia.createDatastore(client, DB_NAME);
		ds.setDefaultWriteConcern(WriteConcern.FSYNCED);
		return ds;
		//mapPackage("at.ac.tuwien.ec.mongouk2011.entities")
	}

}
