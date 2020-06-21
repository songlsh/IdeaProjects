package com.sls.jdk8.treeMap;

import com.sls.bean.Person;

import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TreeMapModel<S, P> extends TreeMap {

    private static final Lock lock = new ReentrantLock();


    public void put(String key, Person person){
        lock.lock();
        try {
           this.put(key,person);
        }finally {
            lock.unlock();
        }

    }
    public Person  get(String key){
        lock.lock();
        try {
          return this.get(key);
        }finally {
            lock.unlock();
        }
    }
}
