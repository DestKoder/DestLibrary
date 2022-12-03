package ru.dest.library.spigot;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface SpigotItem {

    /**
     * Set nbt tag to item with String value
     * @param key - tag name
     * @param value - tag value
     */
    void setTag(String key, String value);

    /**
     * Set nbt tag to item with integer value
     * @param key - tag name
     * @param value - tag value
     */
    void setTag(String key, int value);

    /**
     * Set nbt tag to item with double value
     * @param key - tag name
     * @param value - tag value
     */
    void setTag(String key, double value);

    /**
     * Set nbt tag to item with float value
     * @param key - tag name
     * @param value - tag value
     */
    void setTag(String key, float value);

    /**
     * Set nbt tag to item with short integer value
     * @param key - tag name
     * @param value - tag value
     */
    void setTag(String key, short value);

    /**
     * Set nbt tag to item with big integer value
     * @param key - tag name
     * @param value - tag value
     */
    void setTag(String key, long value);

    /**
     * Set nbt tag to item with byte value
     * @param key - tag name
     * @param value - tag value
     */
    void setTag(String key, byte value);

    /**
     * Set nbt tag to item with integer array value
     * @param key - tag name
     * @param value - tag value
     */
    void setTag(String key, int[] value);

    /**
     * Set nbt tag to item with byte array value
     * @param key - tag name
     * @param value - tag value
     */
    void setTag(String key, byte[] value);

    /**
     * Get String nbt tag value
     * @param key - tag name
     * @return value of tag, or null if tag exists
     */
    @Nullable
    String getStringTagValue(String key);

    /**
     * Get Integer nbt tag value
     * @param key - tag name
     * @return value of tag, or null if tag exists
     */
    @Nullable
    Integer getIntegerTagValue(String key);

    /**
     * Get short integer nbt tag value
     * @param key - tag name
     * @return value of tag, or null if tag exists
     */
    @Nullable
    Short getShortTagValue(String key);

    /**
     * Get big integer nbt tag value
     * @param key - tag name
     * @return value of tag, or null if tag exists
     */
    @Nullable
    Long getLongTagValue(String key);

    /**
     * Get double nbt tag value
     * @param key - tag name
     * @return value of tag, or null if tag exists
     */
    @Nullable
    Double getDoubleTagValue(String key);
    /**
     * Get float nbt tag value
     * @param key - tag name
     * @return value of tag, or null if tag exists
     */
    @Nullable
    Float getFloatTagValue(String key);
    /**
     * Get byte nbt tag value
     * @param key - tag name
     * @return value of tag, or null if tag exists
     */
    @Nullable
    Byte getByteTagValue(String key);
    /**
     * Get integer array nbt tag value
     * @param key - tag name
     * @return value of tag, or empty arrays if tag doesn't exist
     */
    int[] getIntArrayTagValue(String key);

    /**
     * Get byte arrays nbt tag value
     * @param key - tag name
     * @return value of tag, or empty arrays if tag doesn't exist
     */
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
