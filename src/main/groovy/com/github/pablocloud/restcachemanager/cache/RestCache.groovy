package com.github.pablocloud.restcachemanager.cache

import com.fasterxml.jackson.databind.ObjectMapper
import com.mashape.unirest.http.Unirest
import org.springframework.cache.Cache

import java.util.concurrent.Callable

class RestCache implements Cache {

    String endpoint
    String name

    RestCache(String endpoint, String name){
        this.endpoint = endpoint
        this.name = name
    }

    @Override
    String getName() {
        return name
    }

    @Override
    Object getNativeCache() {
        return Unirest
    }

    @Override
    RestCache.ValueWrapper get(Object key) {
        return null
    }

    @Override
    <T> T get(Object key, Class<T> type) {
        return new ObjectMapper().readValue(Unirest.get(endpoint + '/' + name + '/' + key).asString().body, T)
    }

    @Override
    <T> T get(Object key, Callable<T> valueLoader) {
        return new ObjectMapper().readValue(Unirest.get(endpoint + '/' + name + '/' + key).asString().body, T)
    }

    @Override
    void put(Object key, Object value) {
        Unirest.post(endpoint + '/' + name + '/' + key).body
    }

    @Override
    RestCache.ValueWrapper putIfAbsent(Object key, Object value) {
        return null
    }

    @Override
    void evict(Object key) {
        Unirest.delete(endpoint + '/' + name + '/' + key).body
    }

    @Override
    void clear() {
        Unirest.delete(endpoint + '/' + name).body
    }

}
