package ru.dest.library.devtools;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MemoryStorage<T> {

    protected final List<T> dataList;

    public MemoryStorage(){
        this.dataList = new ArrayList<>();;
    }

    public boolean contains(T obj) {
        return dataList.contains(obj);
    }

    public void remove(T obj){
        dataList.remove(obj);
    }

    public void removeIf(Predicate<T> checkFunction){
        dataList.removeIf(checkFunction);
    }

    public void add(T obj){
        dataList.add(obj);
    }

    public void forEach(Consumer<T> function){
        this.dataList.forEach(function);
    }

}
