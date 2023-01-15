package minecraft.plugin.ros.ros;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    private static ROS plugin;

    Command(ROS plugin) {
        Command.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        switch (args.length) {
            case 1:
                switch (args[0]) {
                    case "reload":
                        plugin.reload(sender);
                        return true;
                    case "stick":
                        if (!(sender instanceof Player)) {
                            sender.sendMessage("this command only can use player.");
                            return true;
                        }

                        Player player = (Player) sender;
                        player.getInventory().addItem(ItemManager.getStick());
                        player.sendMessage("give stick to " + player.getName());
                        return true;
                }
                break;
        }
        sender.sendMessage("/ros reload");
        sender.sendMessage("/ros stick");
        return true;
    }
}
