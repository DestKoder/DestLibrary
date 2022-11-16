package ru.dest.library.integration;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public final class IntegrationManager {

    private final Map<String, Integration> integrations = new HashMap<>();

    public void registerIntegration(String name, Integration integration){
        if(integration.init(Bukkit.getServer())){
            this.integrations.put(name, integration);
        }
    }

    public Integration getRegisteredIntegration(String name){
        return integrations.getOrDefault(name, null);
    }

}
