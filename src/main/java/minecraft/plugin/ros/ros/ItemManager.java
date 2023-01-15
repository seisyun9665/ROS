package minecraft.plugin.ros.ros;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
    private static final String STICK_NAME = "§aROS STICK";
    private static final Material STICK_MATERIAL = Material.STICK;

    public static ItemStack getStick() {
        ItemStack item = new ItemStack(STICK_MATERIAL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(STICK_NAME);
        item.setItemMeta(meta);
        return item;
    }

    public static boolean isStick(ItemStack item) {
        return item != null && item.getType() == STICK_MATERIAL && item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(STICK_NAME);
    }
}
