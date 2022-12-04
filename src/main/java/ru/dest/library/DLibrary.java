package ru.dest.library;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.Plugin;
import ru.dest.library.bukkit.BukkitPlugin;
import ru.dest.library.command.CommandInfo;
import ru.dest.library.cooldown.Cooldowns;
import ru.dest.library.gui.GUI;
import ru.dest.library.integration.placeholderapi.PlaceholdersProvider;
import ru.dest.library.utils.ReflectionUtils;

public final class DLibrary extends BukkitPlugin<DLibrary> implements Listener, CommandExecutor {

    private Cooldowns cooldowns;

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
        super.onEnable();
        System.out.println(ReflectionUtils.getCraftBukkitVersion());

        getPluginManager().registerEvents(this, this);

        commandRegistry().registerCommand(new CommandInfo(this, "test"));

        this.cooldowns = new Cooldowns(this);

        PlaceholdersProvider placeholders = new PlaceholdersProvider(getName(), "1.0", "DestKoder");

        placeholders.registerPlaceholder("test", player -> {
            return player.getName();
        });

        placeholders.register();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(cooldowns.isOnCooldown(p, "test")){
                sender.sendMessage("On cooldown");
                return true;
            }else {
                sender.sendMessage("Testing");
                cooldowns.setOnCooldown(p, "test", 20);
            }
        }

        return true;
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll((Plugin) this);
    }
}


