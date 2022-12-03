package ru.dest.library.spigot.v1_13_R1;

import net.minecraft.server.v1_13_R2.ItemStack;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.jetbrains.annotations.Nullable;

public class SpigotItem implements ru.dest.library.spigot.SpigotItem {

    private ItemStack item;

    public SpigotItem(org.bukkit.inventory.ItemStack item){
        this.item = CraftItemStack.asNMSCopy(item);
    }

    @Override
    public void setTag(String key, String value){
        if (item.getTag() != null) {
            item.getTag().setString(key, value);
        }
    }

    @Override
    public void setTag(String key, int value){
        if (item.getTag() != null) {
            item.getTag().setInt(key, value);
        }
    }

    @Override
    public void setTag(String key, double value){
        if (item.getTag() != null) {
            item.getTag().setDouble(key, value);
        }
    }

    @Override
    public void setTag(String key, float value){
        if (item.getTag() != null) {
            item.getTag().setFloat(key, value);
        }
    }

    @Override
    public void setTag(String key, short value){
        if (item.getTag() != null) {
            item.getTag().setShort(key, value);
        }
    }

    @Override
    public void setTag(String key, long value){
        if (item.getTag() != null) {
            item.getTag().setLong(key, value);
        }
    }

    @Override
    public void setTag(String key, byte value){
        if (item.getTag() != null) {
            item.getTag().setLong(key, value);
        }
    }

    @Override
    public void setTag(String key, int[] value){
        if (item.getTag() != null) {
            item.getTag().setIntArray(key, value);
        }
    }

    @Override
    public void setTag(String key, byte[] value){
        if (item.getTag() != null) {
            item.getTag().setByteArray(key, value);
        }
    }

    @Override
    @Nullable
    public String getStringTagValue(String key){
        if(!hasTag(key)) return null;
        else return item.getTag().getString(key);
    }

    @Override
    @Nullable
    public Integer getIntegerTagValue(String key){
        if(!hasTag(key)) return null;
        else return item.getTag().getInt(key);
    }

    @Override
    @Nullable
    public Short getShortTagValue(String key){
        if(!hasTag(key)) return null;
        else return item.getTag().getShort(key);
    }

    @Override
    @Nullable
    public Long getLongTagValue(String key){
        if(!hasTag(key)) return null;
        else return item.getTag().getLong(key);
    }

    @Override
    @Nullable
    public Double getDoubleTagValue(String key){
        if(!hasTag(key)) return null;
        else return item.getTag().getDouble(key);
    }

    @Override
    @Nullable
    public Float getFloatTagValue(String key){
        if(!hasTag(key)) return null;
        else return item.getTag().getFloat(key);
    }

    @Override
    @Nullable
    public Byte getByteTagValue(String key){
        if(!hasTag(key)) return null;
        else return item.getTag().getByte(key);
    }

    @Override
    public int[] getIntArrayTagValue(String key){
        if(!hasTag(key)) return new int[0];
        else return item.getTag().getIntArray(key);
    }

    @Override
    public byte[] getByteArrayTagValue(String key){
        if(!hasTag(key)) return new byte[0];
        else return item.getTag().getByteArray(key);
    }

    /**
     * Check's if item has a tag with given name
     * @param tagName name of tag
     * @return true if item has tag or false another case
     */
    public boolean hasTag(String tagName){
        if (item.getTag() != null) {
            return item.getTag().hasKey(tagName);
        }else return false;
    }

    /**
     * @return return Item as bukkit ItemStack;
     */
    public org.bukkit.inventory.ItemStack getAsItemStack(){
        return CraftItemStack.asBukkitCopy(item);
    }
}
