package ru.dest.library.devtools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class MemoryMap<KEY, VALUE> {

    protected final Map<KEY, VALUE> map;

    public MemoryMap() {
        this.map = new HashMap<>();
    }

    public void add(KEY key, VALUE value){
        this.map.put(key, value);
    }

    public VALUE get(KEY key) {
        return map.getOrDefault(key, null);
    }

    public boolean hasKey(KEY key){
        return map.containsKey(key);
    }

    public boolean hasValue(VALUE value){
        return map.containsValue(value);
    }

    public void forEach(BiConsumer<KEY, VALUE> consumer){
        map.forEach(consumer);
    }

    public Set<KEY> keys() {
        return map.keySet();
    }

    public Set<VALUE> values(){
        return new HashSet<>(map.values());
    }
}
