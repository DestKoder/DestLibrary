package ru.dest.library.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * This class represents a basic listing gui
 *
 * @since 1.0
 * @author DestKoder
 */
public class ItemListGUI extends GUI{

    protected List<ItemStack> items;
    protected List<Integer> emptySlots;

    protected final BiConsumer<InventoryClickEvent, ItemStack> onItemClickTemp;

    protected int page, pages;

    public ItemListGUI(GUITemplate template, Player opener, List<ItemStack> items, BiConsumer<InventoryClickEvent, ItemStack> onItemClick) {
        super(template, opener);
        this.items = items;
        this.emptySlots = getEmptySlots();
        this.page = 0;
        this.pages = items.size() % emptySlots.size() == 0 ? items.size() / emptySlots.size() : items.size() / emptySlots.size()+1;
        this.onItemClickTemp = onItemClick;

        this.refillContent();

        this.addClickHandler("nextPage", event -> {
            if(page < pages) {
                page++;
                refillContent();
            }
        });

        this.addClickHandler("prevPage", event -> {
            if(page > 0) {
                page--;
                refillContent();
            }
        });
    }

    private void refillContent(){
        this.clearContent();
        for(int i = 0; i < emptySlots.size(); i++){
            int item = i + (page == 0 ? 0 : page*emptySlots.size());

            if(item < items.size()){
               inventory.setItem(emptySlots.get(i), items.get(item));
            }
        }
    }
    private void clearContent(){
        for(int i : emptySlots){
            inventory.setItem(i, new ItemStack(Material.AIR));
        }
    }


    @Override
    public void handleClick(InventoryClickEvent event) {
        int slot = event.getSlot();

        if(inventory.getItem(slot) == null || inventory.getItem(slot).getType() == Material.AIR) return;
        if(this.onItemClickTemp == null) return;

        int itemPos = page == 0 ? slot :  this.page * this.emptySlots.size() + slot;

        if(items.size() < itemPos) {
            System.out.println("Internal error");
            return;
        }

        ItemStack item = items.get(itemPos);

        this.onItemClickTemp.accept(event, item);
    }

    @Override
    public void handleDrag(InventoryDragEvent event) {

    }
}
