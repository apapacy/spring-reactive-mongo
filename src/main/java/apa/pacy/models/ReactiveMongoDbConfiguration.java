package apa.pacy.models;


import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

/**
 * Simple configuration that registers a {@link LoggingEventListener} to demonstrate mapping behavior when streaming
 * data.
 *
 * @author Mark Paluch
 */
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableReactiveMongoRepositories
@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
@RequiredArgsConstructor
class ReactiveMongoDbConfiguration extends AbstractReactiveMongoConfiguration {

	private Environment environment;

	@Bean
	public LoggingEventListener mongoEventListener() {
		return new LoggingEventListener();
	}

	@Override
	@Bean
	@DependsOn("mongoDbFactory")
	public MongoClient reactiveMongoClient() {
		//int port = environment.getProperty("local.mongo.port", Integer.class);
		return MongoClients.create(String.format("mongodb://localhost:%d", 27017));
	}

	@Override
	protected String getDatabaseName() {
		return "reactive";
	}
}
