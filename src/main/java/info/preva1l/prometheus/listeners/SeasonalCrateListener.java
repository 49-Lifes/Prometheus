package info.preva1l.prometheus.listeners;

import info.preva1l.prometheus.Prometheus;
import info.preva1l.prometheus.utils.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SeasonalCrateListener implements Listener {
    private final Prometheus plugin;
    public SeasonalCrateListener(Prometheus plugin) {
        this.plugin = plugin;
        TaskManager.Async.runTask(this::drawParticles, 20L);
    }

    private void drawParticles() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            World world = Bukkit.getWorld(plugin.getConfig().getString("monthly-crate.entry.world"));
            if (!player.getWorld().equals(world)) continue;
            double entryX = Math.floor(plugin.getConfig().getDouble("monthly-crate.entry.x"));
            double entryY = Math.floor(plugin.getConfig().getDouble("monthly-crate.entry.y"));
            double entryZ = Math.floor(plugin.getConfig().getDouble("monthly-crate.entry.z"));

            for (int i = 0; i <= 1; i++) {
                for (double j = 0; j <= 2; j += 0.5) {
                    for (double k = 0; k <= 1; k++) {
                        player.spawnParticle(Particle.SCRAPE, entryX + i, entryY + j, entryZ + k, 2, 0, 0, 0, 0);
                        player.spawnParticle(Particle.WAX_ON, entryX + i, entryY + j - 0.1, entryZ + k, 2, 0, 0, 0, 0);
                        player.spawnParticle(Particle.WAX_OFF, entryX + i, entryY + j + 0.1, entryZ + k, 2, 0, 0, 0, 0);

                        // Connect the top and bottom
                        if (j == 0 || j == 2) {
                            player.spawnParticle(Particle.SCRAPE, entryX + i, entryY + j, entryZ + k, 2, 0, 0, 0, 0);
                            player.spawnParticle(Particle.WAX_ON, entryX + i, entryY + j, entryZ + k, 2, 0, 0, 0, 0);
                            player.spawnParticle(Particle.WAX_OFF, entryX + i, entryY + j, entryZ + k, 2, 0, 0, 0, 0);
                        }
                    }
                }
            }

//                for (int i = 0; i <= 1; i++) {
//                    for (double j = 0; j <= 2; j += 0.5) {
//                        for (double k = 0; k <= 1; k++) {
//                            player.spawnParticle(Particle.SCRAPE, entryX + i, entryY + j, entryZ + k, 2, 0, 0, 0, 0);
//                            player.spawnParticle(Particle.WAX_ON, entryX + i, entryY + j - 0.1, entryZ + k, 2, 0, 0, 0, 0);
//                            player.spawnParticle(Particle.WAX_OFF, entryX + i, entryY + j + 0.1, entryZ + k, 2, 0, 0, 0, 0);
//                        }
//                    }
//                }
                //TODO: fix bc its broken af lol
                for (double i : new double[]{0, 2, 0.5, 2, 0.5, 2, 1, 2}) {
                    double xOffset = (i == 1 || i == 2) ? 0.5 : 0; // Adjust the offset for the third and sixth particles
                    double zOffset = (i == 4 || i == 5) ? 1 : 0; // Adjust the offset for the fifth and sixth particles
                    player.spawnParticle(Particle.SCRAPE, entryX + xOffset, entryY + i, entryZ + zOffset, 2, 0, 0, 0, 0);
                    player.spawnParticle(Particle.WAX_OFF, entryX + xOffset, entryY + i - 0.1, entryZ + zOffset, 2, 0, 0, 0, 0);
                    player.spawnParticle(Particle.WAX_ON, entryX + xOffset, entryY + i + 0.1, entryZ + zOffset, 2, 0, 0, 0, 0);
                }

        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        TaskManager.Async.run(() -> {
            Player player = e.getPlayer();
            World entryWorld = plugin.getServer().getWorld(plugin.getConfig().getString("monthly-crate.entry.world"));
            double entryX = Math.floor(plugin.getConfig().getDouble("monthly-crate.entry.x"));
            double entryY = Math.floor(plugin.getConfig().getDouble("monthly-crate.entry.y"));
            double entryZ = Math.floor(plugin.getConfig().getDouble("monthly-crate.entry.z"));

            Location entryLoc = new Location(entryWorld, entryX, entryY, entryZ);
            Location playerLoc = new Location(player.getWorld(), Math.floor(player.getLocation().getX()), Math.floor(player.getLocation().getY()), Math.floor(player.getLocation().getZ()));
            if (playerLoc.equals(entryLoc)) {
                World warpWorld = plugin.getServer().getWorld(plugin.getConfig().getString("monthly-crate.exit.world"));
                double warpX = plugin.getConfig().getDouble("monthly-crate.exit.x");
                double warpY = plugin.getConfig().getDouble("monthly-crate.exit.y");
                double warpZ = plugin.getConfig().getDouble("monthly-crate.exit.z");
                Location warpLoc = new Location(warpWorld, warpX, warpY, warpZ);
                warpLoc.setYaw((float) plugin.getConfig().getDouble("monthly-crate.exit.yaw"));
                TaskManager.Sync.run(() -> player.teleport(warpLoc));
            }
        });
    }
}
