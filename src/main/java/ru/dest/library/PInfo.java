package ru.dest.library;

import static ru.dest.library.utils.ChatUtils.parseColor;

public class PInfo {

    private final String name, version, author;

    public PInfo(String name, String version, String author) {
        this.name = name;
        this.version = version;
        this.author = author;
    }

    @Override
    public String toString(){
        return parseColor("&b"+ name + " &7v&b"+version+" &7by&b "+ author + "&r");
    }
}
