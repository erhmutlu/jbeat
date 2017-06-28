package org.erhmutlu.jbeat.api;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by erhmutlu on 24/05/17.
 */
public class Registry<T> {

    protected Map<String, T> registry;

    public Registry() {
        registry = new ConcurrentHashMap<>();
    }

    public void put(String identifier, T t){
        registry.put(identifier, t);
    }

    public T get(String identifier){
        return registry.get(identifier);
    }

    public void remove(String identifier){
        registry.remove(identifier);
    }

    public Map<String, T> all(){
        return this.registry;
    }
}
