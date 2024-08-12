package info.preva1l.prometheus.commands;

import info.preva1l.prometheus.utils.commands.BasicCommand;
import info.preva1l.prometheus.utils.commands.Command;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class HatCommand extends BasicCommand {
    @Command(name = "hat", permission = "prometheus.hat")
    public HatCommand(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();
        ItemStack itemInHand = inventory.getItemInMainHand();
        ItemStack itemInHelmet = inventory.getHelmet();

        if (itemInHand.getType() == Material.AIR) {
            player.sendMessage("&6&l49Lifes &cYou must hold an item!");
            return;
        }

        inventory.setHelmet(itemInHand);
        inventory.setItemInMainHand(itemInHelmet);
    }
}
