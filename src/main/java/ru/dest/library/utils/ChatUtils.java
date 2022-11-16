package ru.dest.library.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.dest.library.obj.Entry;

import java.util.ArrayList;
import java.util.List;

public final class ChatUtils {

    private static boolean hookPlaceholder;

    static {
        hookPlaceholder = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    public static String parseColor(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> parseColor(List<String> l){
        List<String> toReturn = new ArrayList<>();

        l.forEach(s -> toReturn.add(parseColor(s)));

        return toReturn;
    }

    /**
     * Sets placeholders to string, if PlaceholderAPI is installed
     * @param s {@link String} in which placeholders will be set
     * @param player {@link OfflinePlayer} for placeholders data
     * @return provided string with placeholders set
     */
    public static String applyPlaceholders(String s, OfflinePlayer player){
        if(!hookPlaceholder) return s;

        s = PlaceholderAPI.setPlaceholders(player, s);
        s = PlaceholderAPI.setBracketPlaceholders(player, s);

        return s;
    }

    /**
     * Format a message
     * @param message {@link String} message which will be formatted
     * @param format {@link List<Entry<String, String>} list of format keys & values
     * @return formatted message
     */
    public static String formatMessage(String message, List<Entry<String, String>> format){

        for(Entry<String, String> entry : format){
            message = message.replaceAll("\\{"+entry.getFirstVal()+"}", entry.getSecondVal());
        }

        return message;
    }

    /**
     * Sends message to CommandSender
     * @param sendFor {@link CommandSender} to whom the message will be sent
     * @param message {@link String} message which will be sended;
     */
    public static void sendMessage(CommandSender sendFor, String message){
        sendFor.sendMessage(parseColor(message));
    }

    /**
     * Sends message to Player
     * @param sendFor {@link Player} to whom the message will be sent
     * @param message {@link String} message which will be sended;
     */
    public static void sendMessage(Player sendFor, String message){
        sendFor.sendMessage(parseColor(message));
    }

    /**
     * Sends basecompontent-message to CommandSender
     * @param sendFor {@link CommandSender} to whom the message will be sent
     * @param message {@link BaseComponent} basecompontent-message which will be sended; Support HoverEffects, ClickableEvent etc.
     */
    public static void sendMessage(CommandSender sendFor, BaseComponent message){
        sendFor.spigot().sendMessage(message);
    }

    /**
     * Sends basecompontent-message to Player
     * @param sendFor {@link Player} to whom the message will be sent
     * @param message {@link BaseComponent} basecompontent-message which will be sended; Support HoverEffects, ClickableEvent etc.
     */
    public static void sendMessage(Player sendFor, BaseComponent message){
        sendFor.spigot().sendMessage(message);
    }

    /**
     * Sends basecompontent-message to Player with message position support
     * @param sendFor {@link Player} to whom the message will be sent
     * @param message {@link BaseComponent} basecompontent-message which will be sended; Support HoverEffects, ClickableEvent etc.
     * @param pos {@link ChatMessageType} position in which message will be showed
     */
    public static void sendMessage(Player sendFor, BaseComponent message, ChatMessageType pos){
        sendFor.spigot().sendMessage(pos, message);
    }

    /**
     * Sends message to Player with message position support
     * @param sendFor {@link Player} to whom the message will be sent
     * @param message {@link BaseComponent} message which will be sended; Support HoverEffects, ClickableEvent etc.
     * @param pos {@link ChatMessageType} position in which message will be showed
     */
    public static void sendMessage(Player sendFor, String message, ChatMessageType pos){
        sendFor.spigot().sendMessage(pos, new TextComponent(parseColor(message)));
    }
}
