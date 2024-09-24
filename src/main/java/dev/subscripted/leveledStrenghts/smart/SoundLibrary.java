package dev.subscripted.leveledStrenghts.smart;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SoundLibrary {


    /**
     * Plays a custom sound for the given player.
     *
     * @param player       The player to play the sound for
     * @param customSound  The custom sound to be played
     * @param volume       The volume of the sound
     * @param pitch        The pitch of the sound
     */
    public void playLibrarySound(Player player, CustomSound customSound, float volume, float pitch) {
        if (player != null && customSound != null) {
            player.playSound(player.getLocation(), customSound.getSound(), volume, pitch);
        }
    }

    /**
     * Plays a custom sound for all online players.
     *
     * @param customSound  The custom sound to be played
     * @param volume       The volume of the sound
     * @param pitch        The pitch of the sound
     */
    public void playSoundForAll(CustomSound customSound, float volume, float pitch) {
        if (customSound != null) {
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                playLibrarySound(player, customSound, volume, pitch);
            }
        }
    }
}