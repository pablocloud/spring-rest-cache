package com.github.pablocloud.restcachemanager.cache

import org.springframework.cache.CacheManager

class RestCacheManager implements CacheManager {

    private String endpoint
    private HashMap<String, RestCache> map = new HashMap<>()

    RestCacheManager(String endpoint) {
        this.endpoint = endpoint
    }

    @Override
    RestCache getCache(String name) {
        if (!map.containsKey(name)) {
            map.put(name, new RestCache(endpoint, name))
        }
        return map.get(name)
    }

    @Override
    Collection<String> getCacheNames() {
        return map.keySet()
    }

}
