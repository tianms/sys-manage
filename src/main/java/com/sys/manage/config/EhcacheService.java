package com.sys.manage.config;

import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 缓存类，将系统参数等常用且不会发生改变的数据放入缓存
 *
 * @author huy
 */
@Service
@Component
public class EhcacheService implements CommandLineRunner {

    @Autowired
    private EhCacheCacheManager cacheManager;

    private static final String CACHE_KEY = "cache";

    /**
     * 在系统启动时将常用且不常变的系统参数加载到缓存中
     */
    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("系统启动进入EhCache====加载");

    }

    /**
     *
     * 根据key获取数据
     *
     * @Description: 如果返回null则没有key
     *
     * @author tianms
     * @date 2019/12/10 21:48
     * @param  key
     * @return java.lang.Object
     */
    public Object get (String key) {
        Element element = cacheManager.getCacheManager().getCache(CACHE_KEY).get(key);
        if (element == null) {
            return null;
        } else {
            return element.getObjectValue();
        }
    }

    /**
     *
     * 存入缓存，没有超时时间
     *
     * @Description: 不设置超时时间，不过期
     *
     * @author tianms
     * @date 2019/12/10 21:50
     * @param  key
     * @param  value
     * @return void
     */
    public void putCacheValue (String key, Object value) {
        putWithTime(key, value, 0);
    }

    /**
     *
     * 存入key添加超时时间
     *
     * @Description: 设置超时时间，如果超过超时时间key失效
     *
     * @author tianms
     * @date 2019/12/10 21:50
     * @param  key
     * @param  value
     * @param  timeOut      超时时间，秒
     * @return void
     */
    public void putWithTime (String key, Object value, Integer timeOut) {
        Element element = new Element(key, value);
        if (timeOut > 0) {
            element.setTimeToIdle(timeOut);
            element.setTimeToLive(timeOut);
        }
        cacheManager.getCacheManager().getCache(CACHE_KEY).put(element);
    }

    /**
     *
     * 删除key
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/10 21:49
     * @param  key
     * @return void
     */
    public void removeKey (String key) {
        cacheManager.getCacheManager().getCache(CACHE_KEY).remove(key);
    }

    /**
     *
     * 获取缓存中所有的key
     *
     * @author tianms
     * @date 2019/12/10 21:49
     * @param
     * @return java.util.List<java.lang.String>
     */
    public List<String> getKeys() {
        return cacheManager.getCacheManager().getCache(CACHE_KEY).getKeys();
    }

    /**
     *
     * 判断key值是否存在
     *
     * @Description: 存在返回true，不存在返回false
     *
     * @author tianms
     * @date 2019/12/10 21:49
     * @param  key
     * @return boolean
     */
    public boolean isExist (String key) {
        if (get(key) != null) {
            return true;
        }
        return false;
    }
}
