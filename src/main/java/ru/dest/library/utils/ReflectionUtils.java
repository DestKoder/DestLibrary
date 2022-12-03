package ru.dest.library.utils;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class ReflectionUtils {
    public static String getCraftBukkitVersion() {
        return cb().split("\\.")[3];
    }

    /**
     * Returns the real packagename for the org.bukkit.craftbukkit package
     * @return the real packagename for the org.bukkit.craftbukkit package
     * @since 1.0
     */
    @NotNull
    public static String cb() {
        return Bukkit.getServer().getClass().getPackage().getName();
    }

    @NotNull
    public static String getPackageName(Object nmsObject) {
        return nmsObject != null ? nmsObject.getClass().getPackage().getName() : "";
    }

    public static void loadAdditionalJarFile(File jarFile) {

    }
}
