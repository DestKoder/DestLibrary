package ru.dest.library.utils;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class CheckUtils {

    private static final Pattern FLOATING_NUMBER = Pattern.compile("^((-?\\d+)[,.](\\d+$))");
    private static final Pattern NUMBER = Pattern.compile("^-?\\d+");
    private static final Pattern BOOLEAN = Pattern.compile("^(false|true)$");

    /**
     * Checks is location one has same coordinates with location two
     * @param one {@link Location} first location
     * @param two {@link Location} second location;
     * @return true if first location has same coordinates with second location or false another ways
     */
    public static boolean locEquals(@NotNull Location one,@NotNull Location two){
        return one.getWorld().equals(two.getWorld()) && one.getBlockX() == two.getBlockX() && one.getBlockY() == two.getBlockY() && one.getBlockZ() == two.getBlockZ();
    }

    /**
     * Checks if string hold a number
     * @param s {@link String} which need to check
     * @return true if string is number or false another ways
     */
    public static boolean isInteger(String s) {
        return NUMBER.matcher(s).matches();
    }

    /**
     * Checks if string hold a number with floating point
     * @param s {@link String} which need to check
     * @return true if string is number or false another ways
     */
    public static boolean isFloatingNumber(String s){
        return FLOATING_NUMBER.matcher(s).matches();
    }

    /**
     * Checks if string hold a boolean value
     * @param s {@link String} which need to check
     * @return true if string is number or false another ways
     */
    public static boolean isBoolean(String s){
        return BOOLEAN.matcher(s).matches();
    }
}
