package com.foodplanner.mongoclient;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.Document;
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
	
	protected T getById(String field,String id)
	{		
		CodecRegistry pojoCodecRegistry = fromRegistries(this.mongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		this.collection = database.getCollection(collectionName,typeParameterClass);
		this.collection = this.collection.withCodecRegistry(pojoCodecRegistry);

		T doc = collection.find(eq(field, id)).first();
		return doc;
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
