package minecraft.plugin.ros.ros;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Draw {
    public static void spiralRingParticle(Location location, double radius, double startDistance, double depth, int entireTime, int rotateCount, ROS plugin) {
        Vector n = location.clone().getDirection();
        // 螺旋を描画するベクトルに対して垂直なベクトルX、Yを求める（getCrossProductで外積を求めている）
        Vector X = n.clone().getCrossProduct(new Vector(0, 1, 0)).normalize();
        Vector Y = X.clone().getCrossProduct(location.getDirection()).normalize();
        // 描画回数を算出(32は半径1ごとの描画回数の増加割合。半径が10なら1周で320回描画を行う）
        double drawCount = radius * 32;
        // 一回の描画ごとに動く円の度数(ラジアン)
        double radianDelta = 360.0 / drawCount;
        // 一回の描画ごとに進む奥行きの距離
        double depthDelta = depth / drawCount / (double) rotateCount;
        // 1tick(ゲーム内最小時間単位)ごとに描画する回数(総計描画回数÷全体フレーム)
        double drawCountPerTick = drawCount * (double) rotateCount / (double) entireTime;

        // 描画処理
        for (int i = 0; (double) i < drawCount * (double) rotateCount; ++i) {
            int finalI = i;
            (new BukkitRunnable() {
                public void run() {
                    // 方程式C
                    Location drawLoc = location.clone().add(
                                    location.getDirection().multiply(depthDelta * (double) finalI + startDistance))
                            .add(X.clone().multiply(radius * Math.cos(Math.toRadians(radianDelta * (double) finalI))))
                            .add(Y.clone().multiply(radius * Math.sin(Math.toRadians(radianDelta * (double) finalI))));
                    drawOnePixel(drawLoc);
                }
            }).runTaskLater(plugin, (long) ((double) i / drawCountPerTick));
        }
    }

    static void drawOnePixel(Location location) {
        location.getWorld().spawnParticle(Particle.REDSTONE, location, 1);
    }
}
