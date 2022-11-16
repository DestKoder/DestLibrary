package ru.dest.library.integration.vault;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.plugin.RegisteredServiceProvider;
import ru.dest.library.integration.Integration;

public final class EconomyIntegration implements Integration {

    public static final String REGISTRY_NAME = "VaultEconomy";

    private Economy economy;

    @Override
    public boolean init(Server server) {
        if(server.getPluginManager().getPlugin("Vault") == null) return false;

        RegisteredServiceProvider<Economy> provider = server.getServicesManager().getRegistration(Economy.class);

        if(provider == null) return false;

        this.economy = provider.getProvider();

        return true;
    }

    public boolean takeMoney(OfflinePlayer player, double amount){
        return economy.withdrawPlayer(player, amount).transactionSuccess();
    }

    public boolean hasMoney(OfflinePlayer player, double amount){
        return economy.has(player, amount);
    }

    public boolean addMoney(OfflinePlayer player, double amount){
        return economy.depositPlayer(player, amount).transactionSuccess();
    }

    public Economy provider() {return economy;}
}
