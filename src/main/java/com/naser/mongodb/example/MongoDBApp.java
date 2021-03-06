package com.naser.mongodb.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Small MongoDB application that uses spring data to interact with MongoDB.
 * 
 * @author Naser Ahmed
 */
public class MongoDBApp {

    static final Logger logger = LoggerFactory.getLogger(MongoDBApp.class);

    public static void main( String[] args ) {
		logger.info("Bootstrapping MongoDemo application");

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");

        PersonRepository personRepository = context.getBean(PersonRepository.class);

        // cleanup person collection before insertion
        personRepository.dropPersonCollection();

        //create person collection
        personRepository.createPersonCollection();

        for(int i=0; i<5; i++) {
            personRepository.insertPerson();
        }

        personRepository.logAllPersons();
        logger.info("Average age of a person is: {}", personRepository.getAvarageAgeOfPerson());

        logger.info("Finished MongoDemo application");
	}
}
