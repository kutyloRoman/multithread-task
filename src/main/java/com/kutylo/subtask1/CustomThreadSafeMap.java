package com.kutylo.subtask1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class CustomThreadSafeMap<K, V> implements Map<K, V> {
    private ReentrantLock lock = new ReentrantLock();

    private Set<Map.Entry<K, V>> entrySet = new HashSet<>();

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        lock.lock();
        entrySet.add(new Entry(key, value));
        lock.unlock();
        return value;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        lock.lock();
        Set<V> values = new HashSet<>();
        for (Map.Entry<K, V> entry : entrySet) {
            values.add(entry.getValue());
        }
        lock.unlock();

        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet;
    }

    public static class Entry<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            this.value = value;
            return value;
        }
    }
}
