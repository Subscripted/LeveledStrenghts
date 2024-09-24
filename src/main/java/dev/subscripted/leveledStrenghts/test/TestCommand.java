package dev.subscripted.leveledStrenghts.test;

import dev.subscripted.leveledStrenghts.Main;
import dev.subscripted.leveledStrenghts.annotations.Command;
import dev.subscripted.leveledStrenghts.annotations.SubTabCompleteArgument;
import dev.subscripted.leveledStrenghts.annotations.TabCompleteArgument;
import dev.subscripted.leveledStrenghts.gui.Defaults;
import dev.subscripted.leveledStrenghts.handler.ItemBuilder;
import dev.subscripted.leveledStrenghts.smart.CustomSound;
import dev.subscripted.leveledStrenghts.smart.InventoryAdvancer;
import dev.subscripted.leveledStrenghts.smart.SoundLibrary;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TestCommand implements CommandExecutor {

    final Main instance;
    final SoundLibrary soundLibrary;

    @Override
    @Command(
            name = "skill",
            tabCompleter = true,
            tabCompletionArgs = {
                    @TabCompleteArgument(argIndex = 0, argText = "test", permission = "test.pitch1",
                            subTabCompletionArgs = {
                                    @SubTabCompleteArgument(argText = "Option A", permission = "test.optionA"),
                                    @SubTabCompleteArgument(argText = "Option B", permission = "test.optionB")
                            }),
                    @TabCompleteArgument(argIndex = 0, argText = "2.0", permission = "test.pitch2",
                            subTabCompletionArgs = {
                                    @SubTabCompleteArgument(argText = "Option C", permission = "test.optionC"),
                                    @SubTabCompleteArgument(argText = "Option D", permission = "test.optionD")
                            })
            }
    )
    public boolean onCommand(@NotNull CommandSender commandSender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This command can only be run by players.");
            return true;
        }

        Player player = (Player) commandSender;
        if (strings.length == 0) {
            player.sendMessage("Please provide a pitch value.");
            return false;
        }

        try {
            ItemBuilder itemBuilder = new ItemBuilder(Material.PLAYER_HEAD)
                    .setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZjM2MyNDNmYzA4OTRhYTQwMjhkMzJiMTlhODMwYTJmY2FkYzI5MzI3MGI0Y2IzMmMxYmFlNDJjNzhjMDhiZSJ9fX0=");
            ItemBuilder itemBuilder2 = new ItemBuilder(Material.PLAYER_HEAD)
                    .setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2YzZTdiY2E1YzY1MWJiYTI5Y2QzNTlkNWNkNDc0NDAyY2MyM2NhN2IzMDlkYzQ4NzM2NDM2YjlmMDU1YjkwNSJ9fX0=");
            player.getInventory().addItem(itemBuilder.build(), itemBuilder2.build());

            float pitch = Float.parseFloat(strings[0]);
            soundLibrary.playLibrarySound(player, CustomSound.OPEN_WITH_KEY, 1f, pitch);

            Defaults.o_MainGUI(player);
        } catch (NumberFormatException e) {
            player.sendMessage("Invalid pitch value.");
        }

        return true;
    }
}
