package info.preva1l.prometheus.utils.commands;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public abstract class BasicSubCommand {
    private final Command info;
    public JavaPlugin plugin;

    public BasicSubCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        this.info = Arrays.stream(this.getClass().getConstructors())
                .filter(method -> method.getAnnotation(Command.class) != null)
                .map(method -> method.getAnnotation(Command.class)).findFirst().orElse(null);

        if (info == null) {
            throw new RuntimeException("BasicSubCommand constructor must be annotated with @Command");
        }
    }

    public abstract void execute(CommandSender sender, String[] args);

    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}