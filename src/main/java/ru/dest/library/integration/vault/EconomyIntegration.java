package ru.dest.library.integration.vault;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.plugin.RegisteredServiceProvider;
import ru.dest.library.integration.Integration;

/**
 * Basic integration in Vault economy
 *
 * @since 1.0
 * @author DestKoder
 */
public final class EconomyIntegration implements Integration {
    private Economy economy;

    @Override
    public boolean init(Server server) {
        if(server.getPluginManager().getPlugin("Vault") == null) return false;

        RegisteredServiceProvider<Economy> provider = server.getServicesManager().getRegistration(Economy.class);

        if(provider == null) return false;

        this.economy = provider.getProvider();

        return true;
    }

    /**
     * Take money from player balance
     * @param player {@link OfflinePlayer} or {@link org.bukkit.entity.Player} to take money from
     * @param amount amount of money which will be taken
     * @return true if action completed success or false another ways
     */
    public boolean takeMoney(OfflinePlayer player, double amount){
        return economy.withdrawPlayer(player, amount).transactionSuccess();
    }
    /**
     * Check's if player has a pointed amount on his balance
     * @param player {@link OfflinePlayer} or {@link org.bukkit.entity.Player} which will be checked
     * @param amount amount of money which will be checked
     * @return true if it has and false if not
     */
    public boolean hasMoney(OfflinePlayer player, double amount){
        return economy.has(player, amount);
    }

    /**
     * Add money to player balance
     * @param player {@link OfflinePlayer} or {@link org.bukkit.entity.Player} to add money for
     * @param amount amount of money which will be added
     * @return true if action completed success or false another ways
     */
    public boolean addMoney(OfflinePlayer player, double amount){
        return economy.depositPlayer(player, amount).transactionSuccess();
    }

    /**
     * Get the Vault {@link Economy} provider
     * @return Vault {@link Economy} provider
     */
    public Economy provider() {return economy;}
}
