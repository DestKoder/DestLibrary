package ru.dest.library.integration.vault;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import ru.dest.library.integration.Integration;

public final class VaultChatIntegration implements Integration {

    public static final String REGISTRY_NAME = "VaultChat";

    private Chat chat;

    public VaultChatIntegration() {}

    @Override
    public boolean init(Server server) {
        if(server.getPluginManager().getPlugin("Vault") == null) return false;

        RegisteredServiceProvider<Chat> provider = server.getServicesManager().getRegistration(Chat.class);

        if(provider == null) return false;

        this.chat = provider.getProvider();

        return true;
    }

    public String getPlayerPrefix(Player player) {
        return chat.getPlayerPrefix(player);
    }

    public String getPlayerSuffix(Player player){
        return chat.getPlayerSuffix(player);
    }

    public void setPlayerPrefix(Player player, String prefix){
        chat.setPlayerPrefix(player, prefix);
    }

    public void setPlayerSuffix(Player player, String suffix){
        chat.setPlayerSuffix(player, suffix);
    }

    public Chat provider(){return chat;}
}
