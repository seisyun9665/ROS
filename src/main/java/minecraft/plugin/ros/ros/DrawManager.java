package minecraft.plugin.ros.ros;

import org.bukkit.Location;

public class DrawManager {
    private static Location location;

    public static void setLocation(Location loc) {
        DrawManager.location = loc;
    }

    public static Location getLocation() {
        return location;
    }

    public static boolean draw(ROS plugin) {
        if (location == null) {
            return false;
        }
        //                      初期地点、     半径、    発生位置、 奥行き、全体フレーム、     回転数
        Draw.spiralRingParticle(location, 1, 1, 5, 10, 1, plugin);
        return true;
    }
}
