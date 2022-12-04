package ru.dest.library.integration.vault;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import ru.dest.library.integration.Integration;

/**
 * Basic integration in Vault chat
 *
 * @since 1.0
 * @author DestKoder
 */
public final class VaultChatIntegration implements Integration {

    private Chat chat;

    @Override
    public boolean init(Server server) {
        if(server.getPluginManager().getPlugin("Vault") == null) return false;

        RegisteredServiceProvider<Chat> provider = server.getServicesManager().getRegistration(Chat.class);

        if(provider == null) return false;

        this.chat = provider.getProvider();

        return true;
    }

    /**
     * Get player prefix
     * @param player {@link Player} which prefix will be returned
     * @return prefix of pointed player
     */
    public String getPlayerPrefix(Player player) {
        return chat.getPlayerPrefix(player);
    }

    /**
     * Get player suffix
     * @param player {@link Player} which suffix will be returned
     * @return suffix of pointed player
     */
    public String getPlayerSuffix(Player player){
        return chat.getPlayerSuffix(player);
    }

    /**
     * Sets player prefix
     * @param player {@link Player} to set prefix for
     * @param prefix prefix which will be set
     */
    public void setPlayerPrefix(Player player, String prefix){
        chat.setPlayerPrefix(player, prefix);
    }

    /**
     * Sets player suffix
     * @param player {@link Player} to set suffix for
     * @param suffix suffix which will be set
     */
    public void setPlayerSuffix(Player player, String suffix){
        chat.setPlayerSuffix(player, suffix);
    }

    /**
     * Get Vault {@link Chat} provider
     * @return Vault {@link Chat} provider
     */
    public Chat provider(){return chat;}
}
