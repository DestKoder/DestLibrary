package ru.dest.library.database;

import org.apache.commons.lang3.Validate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import ru.dest.library.exception.MissingConfigurationException;
import ru.dest.library.exception.UnsupportedDatabaseTypeException;
import ru.dest.library.utils.ConfigUtils;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;

public class ConnectionSettings {

    public final String DB_TYPE, HOST, DATABASE, USERNAME, PASSWORD, PARAMS;
    public final Plugin plugin;
    public final int PORT;

    private ConnectionSettings(Plugin plugin, @NotNull String DB_TYPE,@Nullable String HOST,@NotNull String DATABASE,@Nullable String USERNAME,@Nullable String PASSWORD,@Nullable String PARAMS, int port) {
        Validate.notNull(DB_TYPE);
        Validate.notNull(DATABASE);

        this.DB_TYPE = DB_TYPE;
        this.HOST = HOST;
        this.DATABASE = DATABASE;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.PORT = port;
        this.plugin = plugin;
        this.PARAMS = PARAMS;

        System.out.println("Params: " + PARAMS);
    }

    private ConnectionSettings(Plugin plugin, String DB_TYPE, String HOST, String DATABASE, String USERNAME, @Nullable String PARAMS, String PASSWORD) {
        this.DB_TYPE = DB_TYPE;
        this.HOST = HOST;
        this.DATABASE = DATABASE;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.plugin = plugin;
        this.PORT = 3306;
        this.PARAMS = PARAMS;

        System.out.println("Params: " + PARAMS);
    }

    @NotNull
    public static ConnectionSettings loadFrom(@NotNull final Plugin plugin,@NotNull ConfigurationSection section) {
        if((!section.isSet("type") || !section.isString("type")) && (!section.isSet("database") && !section.isString("database"))) throw new MissingConfigurationException("Invalid configuration! One of keys (type, database) is missing");

        String type = section.getString("type");
        String db = section.getString("database");

        String host = ConfigUtils.getKeyStringValueOrNull(section, "host");
        String username = ConfigUtils.getKeyStringValueOrNull(section, "username");
        String password = ConfigUtils.getKeyStringValueOrNull(section, "password");
        String params = ConfigUtils.getKeyStringValueOrNull(section, "params");

        System.out.println(params);

        if(section.isSet("port") && section.isInt("port")){
            return new ConnectionSettings(plugin, type, host, db, username, password, params, section.getInt("port"));
        }else return new ConnectionSettings(plugin, type, host, db, username, password, params);
    }

    public ConnectionProvider createConnectionProvider() throws UnsupportedDatabaseTypeException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ConnectionProviderType type = ConnectionProviderType.forType(DB_TYPE);

        return type.getProvider(this);
    }
}
