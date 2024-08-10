package info.preva1l.prometheus.commands;

import info.preva1l.prometheus.commands.subcommands.SeasonalCrateEntrySubCommand;
import info.preva1l.prometheus.commands.subcommands.SeasonalCrateExitSubCommand;
import info.preva1l.prometheus.utils.TaskManager;
import info.preva1l.prometheus.utils.Text;
import info.preva1l.prometheus.utils.commands.BasicCommand;
import info.preva1l.prometheus.utils.commands.Command;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SeasonalCrateCommand extends BasicCommand {
    @Command(name = "monthly", aliases = {"seasonal"}, async = true, permission = "prometheus.monthly")
    public SeasonalCrateCommand(JavaPlugin plugin) {
        super(plugin);
        getSubCommands().add(new SeasonalCrateEntrySubCommand(plugin));
        getSubCommands().add(new SeasonalCrateExitSubCommand(plugin));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length >= 1) {
            if (subCommandExecutor(sender, args)) return;
            sender.sendMessage(Text.colorize("&6&l49Lifes &cCommand does not exist?"));
            return;
        }

        Player player = (Player) sender;

        World warpWorld = plugin.getServer().getWorld(plugin.getConfig().getString("monthly-crate.exit.world"));
        double warpX = plugin.getConfig().getDouble("monthly-crate.exit.x");
        double warpY = plugin.getConfig().getDouble("monthly-crate.exit.y");
        double warpZ = plugin.getConfig().getDouble("monthly-crate.exit.z");
        Location warpLoc = new Location(warpWorld, warpX, warpY, warpZ);
        warpLoc.setYaw((float) plugin.getConfig().getDouble("monthly-crate.exit.yaw"));
        TaskManager.Sync.run(() -> player.teleport(warpLoc));
    }
}
