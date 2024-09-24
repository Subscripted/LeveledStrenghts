package dev.subscripted.leveledStrenghts.gui;

import dev.subscripted.leveledStrenghts.Main;
import dev.subscripted.leveledStrenghts.handler.ItemBuilder;
import dev.subscripted.leveledStrenghts.smart.InventoryAdvancer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Defaults {

    final Main instance;

    /**
     * Opens the Main GUI
     * @param player The Player who opens the GUI
     */
    public static void o_MainGUI(Player player){
        UUID uuid = player.getUniqueId();

        /**
         * Building the Inventory
         */

        Inventory inventory = Bukkit.createInventory(player, 6*9, "Main Menu");

        /**
         * Building the Items that are set in the Inventory
         */

        ItemBuilder filler = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                .setDisplayName("");
        ItemBuilder corners = new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                .setDisplayName("");

        ItemBuilder fighting = new ItemBuilder(Material.IRON_SWORD)
                .setDisplayName("Fighting");
        ItemBuilder brewing = new ItemBuilder(Material.POTION)
                .setDisplayName("Brewing")
                .setPotionEffect(PotionEffectType.ABSORPTION, 0, 0)
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
        ItemBuilder farming = new ItemBuilder(Material.WHEAT)
                .setDisplayName("Farming");
        ItemBuilder enchanting = new ItemBuilder(Material.WHEAT)
                .setDisplayName("Enchanting")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES);;

        ItemBuilder repair = new ItemBuilder(Material.MACE)
                .setDisplayName("Repair")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES);;
        ItemBuilder fishing = new ItemBuilder(Material.FISHING_ROD)
                .setDisplayName("Fishing")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES);;
        ItemBuilder exp = new ItemBuilder(Material.EXPERIENCE_BOTTLE)
                .setDisplayName("Experience");
        ItemBuilder walking = new ItemBuilder(Material.LEATHER_BOOTS)
                .setDisplayName("Walking");

        ItemBuilder toplist = new ItemBuilder(Material.PLAYER_HEAD)
                .setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY3ZjJiYzQxMWRkMzhmMzExMWZlMWEzN2UxNzliZGNhYjY2ZTUwOWMyYWZmMjcwMjgxNGQ1ZTA3YTRmYWViNiJ9fX0=")
                .setDisplayName("Top");
        ItemBuilder locked = new ItemBuilder(Material.OMINOUS_TRIAL_KEY)
                .setDisplayName("Locked");
        ItemBuilder connectedskills = new ItemBuilder(Material.PLAYER_HEAD)
                .setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZjM2MyNDNmYzA4OTRhYTQwMjhkMzJiMTlhODMwYTJmY2FkYzI5MzI3MGI0Y2IzMmMxYmFlNDJjNzhjMDhiZSJ9fX0=")
                .setDisplayName("Connect");


        /**
         * Fills the Inventory before the Other Items get placed
         * in cause of overriding the other Items when set after
         */

        InventoryAdvancer.fillNulledInventory(filler, inventory);

        /**
         * Here we will set the Default scheme for the Main-UI
         */

        InventoryAdvancer.makePattern_new(inventory, filler, corners);

        /**
         * Now we set the Items in the Chosen order in the Inventory
         */

        inventory.setItem(11, fighting.build());
        inventory.setItem(15, repair.build());
        inventory.setItem(20, brewing.build());
        inventory.setItem(22, toplist.build());
        inventory.setItem(24, fishing.build());
        inventory.setItem(29, farming.build());
        inventory.setItem(31, locked.build());
        inventory.setItem(33, exp.build());
        inventory.setItem(38, enchanting.build());
        inventory.setItem(42, walking.build());
        inventory.setItem(49, connectedskills.build());


        player.openInventory(inventory);
    }



}
