package ru.dest.library.integration.placeholderapi;

import org.bukkit.Server;
import org.jetbrains.annotations.NotNull;
import ru.dest.library.integration.Integration;

public final class PlaceholdersIntegration implements Integration {

    public static String REGISTRY_NAME = "PlaceholderAPI";

    @Override
    public boolean init(Server server) {
        return server.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    public void registerPlaceholderProvider(@NotNull final PlaceholdersProvider provider){
        provider.register();
    }
}
