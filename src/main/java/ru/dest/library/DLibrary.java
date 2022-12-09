package ru.dest.library;


import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import ru.dest.library.bukkit.BukkitPlugin;
import ru.dest.library.command.CommandData;
import ru.dest.library.command.RuntimePluginCommand;
import ru.dest.library.gui.GUI;
import ru.dest.library.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static ru.dest.library.utils.ChatUtils.parseColor;

public final class DLibrary extends BukkitPlugin<DLibrary> implements Listener{

    private static final List<PInfo> pls = new ArrayList<>();

    @EventHandler
    public void handleGUIClick(final InventoryClickEvent event){
        if(event.getClickedInventory() == null) return;
        if(event.getClickedInventory().getHolder() == null) return;

        if(!(event.getClickedInventory().getHolder() instanceof GUI)) return;

        ((GUI)event.getClickedInventory().getHolder()).onClick(event);
    }

    @EventHandler
    public void handleGUIDrag(final InventoryDragEvent event){
        if(event.getInventory().getHolder() == null) return;

        if(!(event.getInventory().getHolder() instanceof GUI)) return;

        ((GUI)event.getInventory().getHolder()).onDrag(event);
    }

    @EventHandler
    public void handleGUIClose(final InventoryCloseEvent event){
        if(event.getInventory().getHolder() == null) return;
        if(!(event.getInventory().getHolder() instanceof GUI)) return;

        ((GUI)event.getInventory().getHolder()).onClose(event);
    }

    @Override
    public void onEnabling() {
        System.out.println(ReflectionUtils.getCraftBukkitVersion());

        getPluginManager().registerEvents(this, this);

        commandRegistry().registerCommand(new C(this));
    }

    public static void r(PInfo info){
        pls.add(info);
    }
    public void fep(Consumer<PInfo> func) {
        pls.forEach(func);
    }
}

class C extends RuntimePluginCommand<DLibrary> {

    public C(DLibrary plugin) {
        super(plugin, "dpls", "Show libraries't plugins", "/dpls", new String[]{"destpl"});
    }

    @Override
    public void execute(@NotNull CommandData data, String[] arguments) {
        data.getSender().sendMessage(parseColor("&b&n==========[ &aDLibrary plugins &b&n]==========&r"));
        plugin.fep(i -> {
            data.getSender().sendMessage(i.toString());
        });
    }
}


