package ru.dest.library.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;

import static ru.dest.library.utils.ChatUtils.parseColor;

public class PluginLogger {

    private final String prefixFormat;

    public PluginLogger(@NotNull String prefixFormat, final @NotNull PluginDescriptionFile desc){
        if(desc.getPrefix() != null) {
            this.prefixFormat = parseColor(prefixFormat.replace("%pluginName%", desc.getPrefix()));
        }else{
            this.prefixFormat = parseColor(prefixFormat.replace("%pluginName%", desc.getName()));
        }
    }

    private void sendToConsole(String msg){
        Bukkit.getConsoleSender().sendMessage(msg);
    }

    public void info(String @NotNull ... message){
        for(String s : message){
            sendToConsole(prefixFormat + ChatColor.GREEN + s);
        }
    }

    public void warning(String @NotNull ... message){
        for(String s : message){
            sendToConsole(prefixFormat + ChatColor.GOLD + s);
        }
    }

    public void error(Exception @NotNull ... exceptions){
        for(Exception e : exceptions){
            sendToConsole(prefixFormat + ChatColor.RED + "Error occupied " + e.getMessage());
            e.printStackTrace();
        }
    }
}
