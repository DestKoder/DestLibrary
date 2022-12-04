package ru.dest.library.utils;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dest.library.exception.InvalidMaterialException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * This class contains some Utils for working with ItemStack's
 *
 * @since 1.0
 * @author DestKoder
 */

public final class ItemUtils {

    /**
     * Create a new {@link ItemStack} with given Material name
     * <p>
     *     Material name formats:
     *     - {material}
     *     - {material}:{data}
     *     - {material}:{data}:{amount}
     * </p>
     *
     * @param materialName - Name of material
     * @return new {@link ItemStack} with given material name and data
     */
    public static ItemStack createByMaterial(String materialName){
        String[] data;

        if(materialName.contains(":")){
            data = materialName.split(":");
        }else data = new String[]{materialName.toUpperCase()};

        Material mat = Material.getMaterial(data[0]);

        if(mat == null) throw new InvalidMaterialException(data[0]);

        if(data.length == 1){
            return new ItemStack(mat);
        }

        if(data.length == 2){
            return new ItemStack(mat, 1, Short.parseShort(data[1]));
        }

        return new ItemStack(mat, Integer.parseInt(data[2]), Short.parseShort(data[0]));
    }


    /**
     * Create {@link ItemStack} with given info
     * @param material - MaterialName of the item
     * @param name - Custom name of the item
     * @param lore - Custom lore of the item
     * @return new {@link ItemStack} with given info;
     */
    @NotNull
    public static ItemStack createItem(@NotNull String material, @Nullable String name, @Nullable List<String> lore){
        ItemStack item = createByMaterial(material);
        ItemMeta meta = item.getItemMeta();

        if(meta == null) return item;

        if(name != null) meta.setDisplayName(ChatUtils.parseColor(name));
        if(lore != null && !lore.isEmpty()) meta.setLore(ChatUtils.parseColor(lore));

        item.setItemMeta(meta);

        return item;
    }

    /**
     * Create {@link ItemStack} with given info
     * @param material - {@link Material} of the item
     * @param name - Custom name of the item
     * @param lore - Custom lore of the item
     * @return new {@link ItemStack} with given info;
     */
    @NotNull
    public static ItemStack createItem(@NotNull Material material, @Nullable String name, @Nullable List<String> lore){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if(meta == null) return item;

        if(name != null) meta.setDisplayName(ChatUtils.parseColor(name));
        if(lore != null && !lore.isEmpty()) meta.setLore(ChatUtils.parseColor(lore));

        item.setItemMeta(meta);

        return item;
    }

    /**
     * Check's if item is a potion
     * @param item {@link ItemStack} to check;
     */
    public static boolean isPotion(@NotNull ItemStack item){
        return item.getType() == Material.POTION || item.getType() == Material.SPLASH_POTION || item.getType() == Material.LINGERING_POTION;
    }

    /**
     * Apply potion data to a potion
     * @param potion Potion to add data
     * @param data data to add
     * @return potion with applied data
     */
    @NotNull
    public static ItemStack applyPotionData(@NotNull ItemStack potion, @NotNull ConfigurationSection data){
        Set<String> keys = data.getKeys(false);

        PotionMeta meta = (PotionMeta) potion.getItemMeta();

        assert meta != null;

        if(keys.contains("color")) {
            if(data.isConfigurationSection("color")){
                meta.setColor(ConfigUtils.getColor(Objects.requireNonNull(data.getConfigurationSection("color"))));
            }else {
                meta.setColor(Utils.getColorFromHexString(Objects.requireNonNull(data.getString("color"))));
            }
        }

        if(keys.contains("effects") && data.isList("effects")){
            for(String effect : data.getStringList("effects")){
                if(!effect.contains(":")) continue;
                String[] effectData = effect.toUpperCase().split(":");
                if(effectData.length != 3) continue;

                PotionEffectType type = PotionEffectType.getByName(effectData[0]);

                if(type == null) continue;

                meta.addCustomEffect(new PotionEffect(type, Integer.parseInt(effectData[2]), Integer.parseInt(effectData[1])), true);
            }
        }

        potion.setItemMeta(meta);

        return potion;
    }

    /**
     * Apply enchantments to item
     * @param item - item to apply
     * @param enchantments Section or map of enchantments and it's levels
     * @return given item with applied enchantments
     */
    @NotNull
    public static ItemStack applyEnchantments(@NotNull ItemStack item, ConfigurationSection enchantments){
        for(String s : enchantments.getKeys(false)){
            Enchantment ench = Enchantment.getByName(s);
            if(ench == null) continue;

            item.addUnsafeEnchantment(ench, enchantments.getInt(s));
        }

        return item;
    }

    /**
     * Apply enchantments to item
     * @param item - item to apply
     * @param enchantments Section or map of enchantments and it's levels
     * @return given item with applied enchantments
     */
    public static ItemStack applyEnchantments(@NotNull ItemStack item, @NotNull Map<String, Integer> enchantments){
        enchantments.forEach((name, level) -> {
            Enchantment ench = Enchantment.getByName(name);
            if(ench != null) item.addUnsafeEnchantment(ench, level);
        });

        return item;
    }
}
