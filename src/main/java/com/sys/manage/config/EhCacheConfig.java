package com.sys.manage.config;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * 缓存配置
 */
@Configuration
public class EhCacheConfig {

	/**
	 * ehcache 主要的管理器
	 * @param ehCacheManagerFactoryBean
	 * @return
	 */
	@Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean){
       return new EhCacheCacheManager(ehCacheManagerFactoryBean.getObject());
    }
	
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("ehcache/ehcache.xml"));
		cacheManagerFactoryBean.setShared(true);
		return cacheManagerFactoryBean;
	}
}
