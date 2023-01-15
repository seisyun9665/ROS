package minecraft.plugin.ros.ros;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ROS extends JavaPlugin implements Listener {

    private static EventManager eventManager;

    public void reload(CommandSender sender) {
        sender.sendMessage("Plugin Reloaded.");
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("ROS Enabled.");

        // コマンド登録
        Command command = new Command(this);
        getCommand("ros").setExecutor(command);

        // イベント登録
        ROS.eventManager = new EventManager(this);
        getServer().getPluginManager().registerEvents(ROS.eventManager, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("ROS Disabled.");
    }


}
