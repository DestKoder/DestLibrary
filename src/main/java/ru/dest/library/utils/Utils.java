package ru.dest.library.utils;

import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Some utils for coding
 *
 * @author DestKoder
 * @since 1.0
 */
public final class Utils {

    /**
     * Converts hex String to Bukkit Color
     * @param hex {@link String} with hex code (#000000)
     * @return the resulting {@link Color}
     */
    public static Color getColorFromHexString(String hex){
        return Color.fromRGB(Integer.valueOf( hex.substring( 1, 3 ), 16 ),
                Integer.valueOf( hex.substring( 3, 5 ), 16 ),
                Integer.valueOf( hex.substring( 5, 7 ), 16 ));
    }

    public static String argsToMessage(String[] args){
        StringBuilder sb = new StringBuilder();

        for (String arg : args) {
            sb.append(arg);
            sb.append(' ');
        }

        return sb.toString().trim();
    }

    public static String argsToMessage(String[] args, int startPos){
        StringBuilder sb = new StringBuilder();

        for(int i =startPos ;i < args.length; i ++){
            sb.append(args[i]);
            sb.append(' ');
        }

        return sb.toString().trim();
    }

    /**
     * @return the number of seconds elapsed since 1 jan 1970
     */
    public static long getCurrentTimeInSeconds(){
        return System.currentTimeMillis()/1000;
    }

    public static void executeIfHas(Player player, String permission, Consumer<Player> function){
        if(player.hasPermission(permission)) function.accept(player);
    }

    public static long calcLeftTime(long expires){
        long current = getCurrentTimeInSeconds();

        if(current >= expires){
            return 0;
        }else return expires - current;
    }

}
