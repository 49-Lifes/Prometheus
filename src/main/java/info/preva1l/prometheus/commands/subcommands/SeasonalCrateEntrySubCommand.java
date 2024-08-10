package info.preva1l.prometheus.commands.subcommands;

import info.preva1l.prometheus.utils.Text;
import info.preva1l.prometheus.utils.commands.BasicSubCommand;
import info.preva1l.prometheus.utils.commands.Command;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SeasonalCrateEntrySubCommand extends BasicSubCommand {
    @Command(name = "set-entry", permission = "prometheus.monthly.admin")
    public SeasonalCrateEntrySubCommand(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Location loc = player.getLocation();
        String world = loc.getWorld().getName();
        double locX = Math.floor(loc.getX());
        double locY = Math.floor(loc.getY());
        double locZ = Math.floor(loc.getZ());
        plugin.getConfig().set("monthly-crate.entry.x", locX);
        plugin.getConfig().set("monthly-crate.entry.y", locY);
        plugin.getConfig().set("monthly-crate.entry.z", locZ);
        plugin.getConfig().set("monthly-crate.entry.world", world);
        plugin.saveConfig();
        player.sendMessage(Text.colorize("&6&l49Lifes &fMonthly Crate Entry &aSuccessfully &fUpdated!"));
    }
}
