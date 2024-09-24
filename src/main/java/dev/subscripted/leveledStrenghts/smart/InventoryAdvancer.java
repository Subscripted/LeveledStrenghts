package dev.subscripted.leveledStrenghts.smart;

import dev.subscripted.leveledStrenghts.handler.ItemBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum InventoryAdvancer {
    LINE_1(0), LINE_2(9), LINE_3(18), LINE_4(27), LINE_5(36), LINE_6(45);

    final int startingIndex;

    InventoryAdvancer(int startingIndex) {
        this.startingIndex = startingIndex;
    }


    public static void fillInventoryLine(Inventory inventory, InventoryAdvancer line, ItemStack item) {
        int startingIndex = line.getStartingIndex();
        for (int i = startingIndex; i < startingIndex + 9; i++) {
            inventory.setItem(i, item);
        }
    }

    public static void fillNulledInventory(ItemBuilder itemBuilder, Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, itemBuilder.build());
        }
    }

    public static void makePattern(Inventory inventory, ItemBuilder itemStack) {
        int[] slots = {9, 10, 1, 2, 3, 4, 5, 6, 7, 16, 17, 26, 35, 44, 43, 52, 51, 50, 49, 48, 47, 46, 37, 36, 27, 18, 9};

        for (int slot : slots) {
            if (slot < inventory.getSize()) {
                inventory.setItem(slot, itemStack.build());
            }
        }
    }

    /**
     *
     * @Information Only Works for GUI's with MAX size (6*9)
     * @param inventory Value of the Inventory that should be used
     * @param rand Item for the Rand of the UI
     * @param corner Item for the Corner of the UI
     */

    public static void makePattern_new(Inventory inventory, ItemBuilder rand, ItemBuilder corner) {
        int[] iRandSlot = {2,3,6,7,18,27,47,48,50,51,35,26,39,30,21,12,14,23,32,41};
        int[] iCornerSlot = {9,0,1,4,7,8,17,53,52,49,46,44,36, 45};

        for (int slot : iRandSlot) {
            if (slot < inventory.getSize()) {
                inventory.setItem(slot, rand.build());
            }
        }
        for (int slot : iCornerSlot) {
            if (slot < inventory.getSize()) {
                inventory.setItem(slot, corner.build());
            }
        }
    }

    public static void fillCorners(Inventory inventory, ItemBuilder itemStack) {
        int size = inventory.getSize();
        int rows = size / 9;

        if (rows < 1) {
            return;
        }

        int[] cornerSlots = {
                0, 8,
                (rows - 1) * 9, (rows - 1) * 9 + 8
        };

        for (int slot : cornerSlots) {
            if (slot < size) {
                inventory.setItem(slot, itemStack.build());
            }
        }
    }
}