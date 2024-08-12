package info.preva1l.prometheus;

import info.preva1l.prometheus.commands.HatCommand;
import info.preva1l.prometheus.commands.SeasonalCrateCommand;
import info.preva1l.prometheus.listeners.ItemFrameListener;
import info.preva1l.prometheus.listeners.SeasonalCrateListener;
import info.preva1l.prometheus.utils.commands.CommandManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class Prometheus extends JavaPlugin {
    @Getter private static Prometheus instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        reloadConfig();

        Stream.of(
                new ItemFrameListener(),
                new SeasonalCrateListener(this)
        ).forEach(it -> getServer().getPluginManager().registerEvents(it, this));

        Stream.of(
                new SeasonalCrateCommand(this),
                new HatCommand(this)
        ).forEach(CommandManager.getInstance()::registerCommand);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
