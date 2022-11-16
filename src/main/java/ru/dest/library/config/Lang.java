package ru.dest.library.config;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.dest.library.obj.Entry;
import ru.dest.library.obj.Message;

import java.util.ArrayList;
import java.util.List;

import static ru.dest.library.utils.ChatUtils.applyPlaceholders;
import static ru.dest.library.utils.ChatUtils.parseColor;

public final class Lang<T extends JavaPlugin> extends Config<T>{

    private String prefix;
    private final List<Entry<String, String>> replacements = new ArrayList<>();

    public Lang(T plugin, String filePath) {
        super(plugin, filePath);
    }

    @Override
    public void loadData() {
        super.loadData();

        if(this.isSet("prefix")){
            prefix = getString("prefix");
        }else prefix = null;

        if(isSet("formats") && isConfigurationSection("formats")){
            for(String s : getConfigurationSection("formats").getKeys(false)){
                if(isConfigurationSection("formats."+s)) continue;
                this.replacements.add(new Entry<>(s, getString("formats."+s)));
            }
        }
    }

    @NotNull
    public String getSMessage(String key){
        return parseColor(getString(key));
    }

    @NotNull
    public String getSMessage(@NotNull String key, @NotNull Player parseFor){
        return applyPlaceholders(getSMessage(key), parseFor);
    }

    @NotNull
    public List<String> getSMessages(@NotNull String key){
        return parseColor(getStringList(key));
    }

    @NotNull
    public List<String> getSMessages(@NotNull String key,@NotNull Player parseFor){
        List<String> toReturn = new ArrayList<>();

        for(String s : getSMessages(key)){
            toReturn.add(applyPlaceholders(s, parseFor));
        }

        return toReturn;
    }

    @NotNull
    public Message getMessage(String key) {
        return new Message(getSMessage(key));
    }

    @NotNull
    public Message getMessage(String key, Player parseFor){
        return new Message(getSMessage(key, parseFor));
    }

    @NotNull
    public List<Message> getMessages(String key){
        List<Message> toReturn = new ArrayList<>();

        getSMessages(key).forEach(msg -> toReturn.add(new Message(msg)));

        return toReturn;
    }

    @NotNull
    public List<Message> getMessages(String key, Player parseFor){
        List<Message> toReturn = new ArrayList<>();

        getSMessages(key, parseFor).forEach(msg -> toReturn.add(new Message(msg)));

        return toReturn;
    }
}
