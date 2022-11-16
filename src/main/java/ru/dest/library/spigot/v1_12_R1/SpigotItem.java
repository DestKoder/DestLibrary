package ru.dest.library.spigot.v1_12_R1;

import net.minecraft.server.v1_12_R1.ItemStack;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;

public final class SpigotItem implements ru.dest.library.spigot.SpigotItem {
    private ItemStack item;

    public SpigotItem(org.bukkit.inventory.ItemStack item){
        this.item = CraftItemStack.asNMSCopy(item);
    }

    public void setTag(String key, String value){
        if (item.getTag() != null) {
            item.getTag().setString(key, value);
        }
    }

    public void setTag(String key, int value){
        if (item.getTag() != null) {
            item.getTag().setInt(key, value);
        }
    }

    public void setTag(String key, double value){
        if (item.getTag() != null) {
            item.getTag().setDouble(key, value);
        }
    }

    public void setTag(String key, float value){
        if (item.getTag() != null) {
            item.getTag().setFloat(key, value);
        }
    }

    public void setTag(String key, short value){
        if (item.getTag() != null) {
            item.getTag().setShort(key, value);
        }
    }

    public void setTag(String key, long value){
        if (item.getTag() != null) {
            item.getTag().setLong(key, value);
        }
    }

    public void setTag(String key, byte value){
        if (item.getTag() != null) {
            item.getTag().setLong(key, value);
        }
    }

    public void setTag(String key, int[] value){
        if (item.getTag() != null) {
            item.getTag().setIntArray(key, value);
        }
    }

    public void setTag(String key, byte[] value){
        if (item.getTag() != null) {
            item.getTag().setByteArray(key, value);
        }
    }

     public String getStringTagValue(String key){
         if(!hasTag(key)) return null;
         else return item.getTag().getString(key);
     }

     public Integer getIntegerTagValue(String key){
         if(!hasTag(key)) return null;
         else return item.getTag().getInt(key);
     }

    public Short getShortTagValue(String key){
        if(!hasTag(key)) return null;
        else return item.getTag().getShort(key);
    }

    public Long getLongTagValue(String key){
        if(!hasTag(key)) return null;
        else return item.getTag().getLong(key);
    }

    public Double getDoubleTagValue(String key){
        if(!hasTag(key)) return null;
        else return item.getTag().getDouble(key);
    }

    public Float getFloatTagValue(String key){
        if(!hasTag(key)) return null;
        else return item.getTag().getFloat(key);
    }

    public Byte getByteTagValue(String key){
        if(!hasTag(key)) return null;
        else return item.getTag().getByte(key);
    }

    public int[] getIntArrayTagValue(String key){
        if(!hasTag(key)) return new int[0];
        else return item.getTag().getIntArray(key);
    }

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
