package ru.dest.library.integration;

import org.bukkit.Server;

/**
 * This interface indicates that class is integration
 *
 * @since 1.0
 * @author DestKoder
 */
public interface Integration {
    boolean init(Server server);
}
