package ru.dest.library.spigot;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.dest.library.utils.ItemUtils;
import ru.dest.library.utils.ReflectionUtils;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;

public enum SpigotVersion {

    v1_12_R1("ru.dest.library.spigot.v1_12_R1.SpigotItem", "SKULL:3")
    ;

    private final String spigotItemClass;
    private final String headMaterial;

    SpigotVersion(String spigotItemClass, String headMaterial) {
        this.spigotItemClass = spigotItemClass;
        this.headMaterial = headMaterial;
    }

    @NotNull
    SpigotItem getSpigotItem(ItemStack item){
        try {
            return (SpigotItem) Class.forName(spigotItemClass).getDeclaredConstructor(ItemStack.class).newInstance(item);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Nullable
    public static SpigotVersion current(){
        String vName = ReflectionUtils.getCraftBukkitVersion();

        for(SpigotVersion version : values()){
            if(version.name().equals(vName)) return version;
        }

        return null;
    }

    public ItemStack getHeadItem() {
        return ItemUtils.createByMaterial(headMaterial);
    }
}
