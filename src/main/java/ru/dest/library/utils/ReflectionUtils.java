package ru.dest.library.utils;

import org.bukkit.Bukkit;

public final class ReflectionUtils {
    public static String getCraftBukkitVersion() {
        return cb().split("\\.")[3];
    }

    /**
     * Returns the real packagename for the org.bukkit.craftbukkit package
     * @return the real packagename for the org.bukkit.craftbukkit package
     * @since 1.0
     */
    public static String cb() {
        return Bukkit.getServer().getClass().getPackage().getName();
    }

    public static String getPackageName(Object nmsObject) {
        return nmsObject != null ? nmsObject.getClass().getPackage().getName() : "";
    }
}
