package com.jinmark.sys.config.redis;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 
 * @author QC
 * @ClassName RedisCacheConfig
 * @Description TODO(redis 缓存配置) 
 * @date 2017年8月17日上午10:33:03
 * 注意：RedisCacheConfig这里也可以不用继承：CachingConfigurerSupport，也就是直接一个普通的Class就好了；
 *
 * 这里主要我们之后要重新实现 key的生成策略，只要这里修改KeyGenerator，其它位置不用修改就生效了。
 *
 * 普通使用普通类的方式的话，那么在使用@Cacheable的时候还需要指定KeyGenerator的名称;这样编码的时候比较麻烦。
 */
@Configuration
@EnableCaching//启用缓存，这个注解很重要；
public class RedisCacheConfig extends CachingConfigurerSupport {

	private final static Logger logger = LoggerFactory.getLogger(RedisCacheConfig.class);
	
	/**
	 * 
	 * @Title cacheManager
	 * @Description TODO(缓存管理器) 
	 * @param redisTemplate
	 * @return
	 * @return CacheManager  返回类型 
	 * @throws
	 */
	@Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
       CacheManager cacheManager = new RedisCacheManager(redisTemplate);
       return cacheManager;
    }

	
	/**
	 * 自定义key.此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
	 */
	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		logger.info("RedisCacheConfig.keyGenerator()");
		return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                logger.info("keyGenerator=" + sb.toString());
                return sb.toString();
            }

        };
	}
	
	
	@Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(factory);
		//key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
	    //所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
	    //或者JdkSerializationRedisSerializer序列化方式;
	    RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
	    redisTemplate.setKeySerializer(redisSerializer);
	    redisTemplate.setHashKeySerializer(redisSerializer);
	    return redisTemplate;
    }
	
}
