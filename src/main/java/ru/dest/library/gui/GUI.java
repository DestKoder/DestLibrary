package ru.dest.library.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static ru.dest.library.utils.ChatUtils.applyPlaceholders;
import static ru.dest.library.utils.ChatUtils.parseColor;

/**
 * This class represents a basic chest-inventory GUI
 *
 * @since 1.0
 * @author DestKoder
 */
public abstract class GUI implements InventoryHolder {

    protected final GUITemplate template;
    protected final Inventory inventory;
    protected final Player opener;

    protected final Map<String, Consumer<InventoryClickEvent>> clickHandlers = new HashMap<>();

    private Consumer<InventoryCloseEvent> onCloseHandler = null;

    public GUI(@NotNull GUITemplate template, @NotNull Player opener) {
        this.template = template;
        this.opener = opener;
        this.inventory = Bukkit.createInventory(this, template.getSlots(), applyPlaceholders(parseColor(template.getTitle()), opener));

        template.getItems().forEach((slots, item) -> {
            slots.forEach(slot -> {
                inventory.setItem(slot, item);
            });
        });
    }

    /**
     * Add handler on slot specified in config
     * @param configName name of slot in config
     * @param consumer handler
     */
    protected void addClickHandler(String configName, Consumer<InventoryClickEvent> consumer){
        clickHandlers.put(configName, consumer);
    }

    protected void setOnClose(Consumer<InventoryCloseEvent> onClose){
        this.onCloseHandler = onClose;
    }

    protected void removeOnClose(){
        this.onCloseHandler = null;
    }

    public abstract void handleClick(InventoryClickEvent event);
    public abstract void handleDrag(InventoryDragEvent event);

    public final void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        int slot = event.getSlot();

        template.getHandlers().keySet().forEach(slots -> {
            if(slots.contains(slot)){
                System.out.println("Slot found!");
                template.getHandlers().get(slots).accept(event);
            }
        });

        template.getSystemHandlers().keySet().forEach(name -> {
            if(clickHandlers.containsKey(name) && template.getSystemHandlers().get(name) == slot) {
                clickHandlers.get(name).accept(event);
            }
        });

        this.handleClick(event);
    }

    public final void onDrag(InventoryDragEvent event){
        event.setCancelled(true);
        this.handleDrag(event);
    }

    protected List<Integer> getEmptySlots(){
        List<Integer> toReturn = new ArrayList<>();

        for(int i =0; i < inventory.getSize(); i++) {
            if(inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) toReturn.add(i);
        }
        return toReturn;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public final void onClose(InventoryCloseEvent event) {
        if(onCloseHandler != null) onCloseHandler.accept(event);
    }

    public void show(){
        this.opener.openInventory(this.inventory);
    }
}
