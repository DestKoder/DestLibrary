package ru.dest.library.spigot;

import org.bukkit.inventory.ItemStack;

public interface SpigotItem {

    void setTag(String key, String value);
    void setTag(String key, int value);
    void setTag(String key, double value);
    void setTag(String key, float value);
    void setTag(String key, short value);
    void setTag(String key, long value);
    void setTag(String key, byte value);
    void setTag(String key, int[] value);
    void setTag(String key, byte[] value);

    String getStringTagValue(String key);
    Integer getIntegerTagValue(String key);
    Short getShortTagValue(String key);
    Long getLongTagValue(String key);
    Double getDoubleTagValue(String key);
    Float getFloatTagValue(String key);
    Byte getByteTagValue(String key);

    int[] getIntArrayTagValue(String key);
    byte[] getByteArrayTagValue(String key);

    /**
     * Check's if item has a tag with given name
     * @param tagName name of tag
     * @return true if item has tag or false another case
     */
    boolean hasTag(String tagName);

    /**
     * @return return Item as bukkit ItemStack;
     */
    ItemStack getAsItemStack();
}
