package info.preva1l.prometheus.commands.subcommands;

import info.preva1l.prometheus.utils.Text;
import info.preva1l.prometheus.utils.commands.BasicSubCommand;
import info.preva1l.prometheus.utils.commands.Command;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SeasonalCrateExitSubCommand extends BasicSubCommand {
    @Command(name = "set-exit", permission = "prometheus.monthly.admin")
    public SeasonalCrateExitSubCommand(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Location loc = player.getLocation();
        String world = loc.getWorld().getName();
        double locX = Math.floor(loc.getX()) + 0.5;
        double locY = Math.floor(loc.getY());
        double locZ = Math.floor(loc.getZ()) + 0.5;
        plugin.getConfig().set("monthly-crate.exit.x", locX);
        plugin.getConfig().set("monthly-crate.exit.y", locY);
        plugin.getConfig().set("monthly-crate.exit.z", locZ);
        plugin.getConfig().set("monthly-crate.exit.world", world);
        plugin.getConfig().set("monthly-crate.exit.yaw", loc.getYaw());
        plugin.saveConfig();
        player.sendMessage(Text.colorize("&6&l49Lifes &fMonthly Crate Exit &aSuccessfully &fUpdated!"));
    }
}
