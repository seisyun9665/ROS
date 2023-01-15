package minecraft.plugin.ros.ros;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class EventManager implements Listener {
    private static ROS plugin;

    EventManager(ROS plugin) {
        EventManager.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action != Action.LEFT_CLICK_AIR && action != Action.RIGHT_CLICK_AIR) {
            return;
        }
        if (event.getHand() == EquipmentSlot.HAND) {
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();
            if (ItemManager.isStick(item)) {
                if (action == Action.LEFT_CLICK_AIR) {
                    // 左クリックの処理
                    // 螺旋図形の描画
                    if (!DrawManager.draw(plugin)) {
                        player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
                    }
                }
                if (action == Action.RIGHT_CLICK_AIR) {
                    // 右クリックの処理
                    // 描画地点を保存する
                    DrawManager.setLocation(player.getLocation().clone());
                    player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                }
            }
        }
    }
}
