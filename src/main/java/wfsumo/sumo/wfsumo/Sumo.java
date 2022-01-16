package wfsumo.sumo.wfsumo;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import wfsumo.sumo.wfsumo.commands.Commands;
import wfsumo.sumo.wfsumo.listener.sumoListener;
import wfsumo.sumo.wfsumo.menu.acceptMenu;
import wfsumo.sumo.wfsumo.utils.CC;
import wfsumo.sumo.wfsumo.utils.YamlDoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public  class Sumo extends JavaPlugin {

    //
    //
    // SUMO PLUGIN BY ELTURRITO PLS IF YOU USE THIS SRC OR .JAR GIVE ME CREDITS
    // THANKS!.
    //
    @Getter public YamlDoc configFile;
    @Getter public static Sumo instance;

      public static String prefix = CC.translate("&f[&bSumo&f]");
     public static List<String> challengers = new ArrayList();
     public static List<String> recievers = new ArrayList();
     public static String[] arenas;
    public static Map<String, String> arenaStatus = new HashMap();
     public static Map<Player, String> playerInArena = new HashMap();

    @Override
    public void onEnable() {
        instance = this;

        this.configFile = new YamlDoc("config.yml");
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        configFile.save();
        if (configFile.getString("ARENAS") != "null" && configFile.getString("ARENAS") != null) {
            System.out.println(configFile.getString("ARENAS"));
            arenas = configFile.getString("ARENAS").split(",");
            configFile.set("arenas", configFile.getString("ARENAS").replaceAll("null,", ""));
            configFile.save();
        }

        if (configFile.getString("ARENAS") != "null" && configFile.getString("ARENAS") != null) {
            for (int i = 0; i < arenas.length; ++i) {
                if (arenas[i] != null && arenas[i] != "null") {
                    arenaStatus.put(arenas[i], "free");
                }
            }
            CC.console("&aChecking arenas...");
        }


        CC.console("&f&m---------&f[&9&lwfSumo&f]----------");
        CC.console("");
        CC.console("&aEnabling...");
        CC.console("&aLoading Handlers...");
        loadHandlers();
        CC.console("&aLoading Commands");
        loadCommands();
        CC.console("&aLoading PlayerData");
        CC.console("");
        CC.console("&aWaterfall Development, ElTurrito");
        CC.console("&f&m-------------------------");
    }


    @Override
    public void onDisable() {
        CC.console("&cShutdown");
        saveConfig();
    }

    public void loadHandlers() {
        PluginManager r = Bukkit.getPluginManager();
        r.registerEvents(new acceptMenu(), this);
        r.registerEvents(new sumoListener(), this);
    }

    public void loadCommands(){
        Commands commands = new Commands();
        this.getCommand(commands.cmd1).setExecutor(commands);
        this.getCommand(commands.cmd2).setExecutor(commands);
    }
}
