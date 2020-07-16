package com.foodplanner.mongoclient;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.LinkedList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public abstract class MongoClientBase<T> {
	String connectionString;
	String databasename;
	String collectionName;
	Class<T> typeParameterClass;
	MongoClientURI uri;
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<T> collection;

	protected MongoClientBase(String connectionString,String databasename,String collectionName,Class<T> typeParameterClass)
	{
		this.connectionString = connectionString;
		this.databasename = databasename;
		this.collectionName = collectionName;
		this.typeParameterClass = typeParameterClass;
		this.Initialize();
	}
	
	private void Initialize()
	{
		this.uri = new MongoClientURI(this.connectionString);
		this.mongoClient = new MongoClient(uri);

		this.database = mongoClient.getDatabase(databasename);

	}
	
	protected T getUniqueEntityByField(String field,String value)
	{		
		CodecRegistry pojoCodecRegistry = fromRegistries(this.mongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		this.collection = database.getCollection(collectionName,typeParameterClass);
		this.collection = this.collection.withCodecRegistry(pojoCodecRegistry);

		T doc = collection.find(eq(field, value)).first();
		return doc;
	}

	protected List<T> getMultipleEntitiesByField(String field,String value,int batchSize)
	{		
		CodecRegistry pojoCodecRegistry = fromRegistries(this.mongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		this.collection = database.getCollection(collectionName,typeParameterClass);
		this.collection = this.collection.withCodecRegistry(pojoCodecRegistry);

		List<T> docs = collection.find(eq(field, value)).batchSize(batchSize).into(new LinkedList<T>());
		return docs;
	}
	
	protected List<T> getMultipleEntitiesByFieldPrefix(String field,String value,int batchSize)
	{		
		CodecRegistry pojoCodecRegistry = fromRegistries(this.mongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		this.collection = database.getCollection(collectionName,typeParameterClass);
		this.collection = this.collection.withCodecRegistry(pojoCodecRegistry);

		List<T> docs = collection.find(regex(field, value+".*")).batchSize(batchSize).into(new LinkedList<T>());
		return docs;
	}
	
	protected void insertEntity(T entity)
	{
		CodecRegistry pojoCodecRegistry = fromRegistries(this.mongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		this.collection = database.getCollection(collectionName,typeParameterClass);
		this.collection = this.collection.withCodecRegistry(pojoCodecRegistry);

		this.collection.insertOne(entity);
	}
}
