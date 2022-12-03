package ru.dest.library;

import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.Plugin;
import ru.dest.library.bukkit.BukkitPlugin;
import ru.dest.library.gui.GUI;
import ru.dest.library.integration.IntegrationManager;
import ru.dest.library.integration.placeholderapi.PlaceholdersIntegration;
import ru.dest.library.integration.vault.EconomyIntegration;
import ru.dest.library.integration.vault.VaultChatIntegration;
import ru.dest.library.utils.ReflectionUtils;

public final class DLibrary extends BukkitPlugin<DLibrary> implements Listener{
    private IntegrationManager integrationManager;

    @EventHandler
    public void handleGUIClick(InventoryClickEvent event){
        if(event.getClickedInventory() == null) return;
        if(event.getClickedInventory().getHolder() == null) return;

        if(!(event.getClickedInventory().getHolder() instanceof GUI)) return;

        ((GUI)event.getClickedInventory().getHolder()).onClick(event);
    }

    @EventHandler
    public void handleGUIDrag(InventoryDragEvent event){
        if(event.getInventory().getHolder() == null) return;

        if(!(event.getInventory().getHolder() instanceof GUI)) return;

        ((GUI)event.getInventory().getHolder()).onDrag(event);
    }

    @EventHandler
    public void handleGUIClose(InventoryCloseEvent event){
        if(event.getInventory().getHolder() == null) return;
        if(!(event.getInventory().getHolder() instanceof GUI)) return;

        ((GUI)event.getInventory().getHolder()).onClose(event);
    }

    @Override
    public void onEnable() {
        System.out.println(ReflectionUtils.getCraftBukkitVersion());

        integrationManager = new IntegrationManager();

        integrationManager.registerIntegration(PlaceholdersIntegration.REGISTRY_NAME, new PlaceholdersIntegration());
        integrationManager.registerIntegration(VaultChatIntegration.REGISTRY_NAME, new VaultChatIntegration());
        integrationManager.registerIntegration(EconomyIntegration.REGISTRY_NAME, new EconomyIntegration());

        utils().registerHandlers(this);
    }
    @Override
    public void onDisable() {
        HandlerList.unregisterAll((Plugin) this);
    }

    public IntegrationManager getIntegrations() {
        return integrationManager;
    }
}


