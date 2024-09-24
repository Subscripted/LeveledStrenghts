package dev.subscripted.leveledStrenghts.smart;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Sound;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum CustomSound {

    SUCCESSFULL(Sound.ENTITY_PLAYER_LEVELUP),
    NOT_ALLOWED(Sound.ENTITY_VILLAGER_NO),
    NO_PERMISSION(Sound.BLOCK_ANVIL_PLACE),
    ACTIVATED(Sound.BLOCK_BEACON_ACTIVATE),
    DEACTIVATED(Sound.BLOCK_BEACON_DEACTIVATE),
    GUI_SOUND(Sound.UI_BUTTON_CLICK),
    WRONG_USAGE(Sound.UI_TOAST_IN),
    QUESTION(Sound.ENTITY_VILLAGER_TRADE),
    GLASS_GUI_BUILD(Sound.BLOCK_GLASS_PLACE),
    LOADING_FINISHED(Sound.BLOCK_LAVA_EXTINGUISH),
    LOBBY_HIDER_SWITCH(Sound.ENTITY_ARROW_HIT),
    GUI_OPEN(Sound.BLOCK_CHEST_OPEN),
    WARNING(Sound.ENTITY_WITHER_AMBIENT),
    CLAN_OPEN(Sound.ITEM_GOAT_HORN_SOUND_7),
    PAGE_TURN(Sound.ITEM_BOOK_PAGE_TURN),
    UPGRADING(Sound.UI_TOAST_CHALLENGE_COMPLETE),
    // Zum öffnen
    OPEN_WITH_KEY(Sound.BLOCK_VAULT_INSERT_ITEM),
    // Zuschließen
    CLOSE_WITH_KEY(Sound.BLOCK_VAULT_REJECT_REWARDED_PLAYER),

    BELL(Sound.BLOCK_BELL_USE),
    BELL2(Sound.BLOCK_BELL_RESONATE),

    MEMORY(Sound.BLOCK_PORTAL_TRIGGER),
    MEMORY2(Sound.BLOCK_END_PORTAL_FRAME_FILL),
    MEMORY3(Sound.BLOCK_END_PORTAL_SPAWN),

    DEATH(Sound.ENTITY_WITHER_DEATH);

    final Sound sound;

    /**
     * @param sound These are normal Minecraft Sounds, each paired with the perfect name for the given sound.
     */
    CustomSound(Sound sound) {
        this.sound = sound;
    }
}