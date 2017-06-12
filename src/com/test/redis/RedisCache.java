package com.test.redis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import redis.clients.jedis.exceptions.JedisConnectionException;




/**
 * 使用第三方内存数据库作为二级缓存
 * @author ASUS
 *
 */
public class RedisCache implements Cache{
	private static JedisConnectionFactory jedisConnectionFactory;
	
	
	private final String id;
	
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public RedisCache (final String id)
	{
		if(id == null)
		{
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}
	
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		JedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			connection.flushDb();
			connection.flushAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(connection != null)
			{
				connection.close();
			}
		}
		
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public Object getObject(Object key) {
		// TODO Auto-generated method stub
		System.out.println(id);
		Object result = null;
		JedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = serializer.deserialize(connection.get(serializer.serialize(key)));
		} catch (JedisConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(connection != null)
			{
				connection.close();
			}
		}
		return result;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		// TODO Auto-generated method stub
		return this.readWriteLock;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		int result = 0;
		JedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			result = Integer.valueOf(connection.dbSize().toString());
		} catch (JedisConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(connection != null)
			{
				connection.close();
			}
		}
		return result;
	}

	@Override
	public void putObject(Object key, Object value) {
		// TODO Auto-generated method stub
		JedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			connection.set(serializer.serialize(key), serializer.serialize(value));
		} catch (JedisConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(connection != null)
			{
				connection.close();
			}
		}
	}

	@Override
	public Object removeObject(Object key) {
		// TODO Auto-generated method stub
		JedisConnection connection = null;
		Object result = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = connection.expire(serializer.serialize(key), 0);
		} catch (JedisConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(connection != null)
			{
				connection.close();
			}
		}
		return result;
	}


	public static JedisConnectionFactory getJedisConnectionFactory() {
		return jedisConnectionFactory;
	}


	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		RedisCache.jedisConnectionFactory = jedisConnectionFactory;
	}
	
}
