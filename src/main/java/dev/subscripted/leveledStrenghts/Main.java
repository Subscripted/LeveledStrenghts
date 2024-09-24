package dev.subscripted.leveledStrenghts;


import dev.subscripted.leveledStrenghts.annotations.Command;
import dev.subscripted.leveledStrenghts.annotations.CommandAnnotation;
import dev.subscripted.leveledStrenghts.annotations.SubTabCompleteArgument;
import dev.subscripted.leveledStrenghts.annotations.TabCompleteArgument;
import dev.subscripted.leveledStrenghts.smart.SoundLibrary;
import dev.subscripted.leveledStrenghts.test.TestCommand;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Getter
    public Main instance;

    @Getter
    private final SoundLibrary library = new SoundLibrary();


    @Override

    public void onEnable() {
        instance = this;

        CommandAnnotation commandAnnotation = new CommandAnnotation(new TestCommand(instance, library));
        String commandName = commandAnnotation.getCommandName();
        if (commandName != null) {
            getCommand(commandName).setExecutor(commandAnnotation);
        } else {
            getLogger().severe("Failed to register command: No @Command annotation found.");
        }

    }


    @Override
    public void onDisable() {
    }
}
