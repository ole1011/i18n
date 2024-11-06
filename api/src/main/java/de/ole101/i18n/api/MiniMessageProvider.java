package de.ole101.i18n.api;

import net.kyori.adventure.text.minimessage.MiniMessage;

/**
 * Interface for providing a MiniMessage instance.
 * This interface defines a constant and a static method for getting a MiniMessage instance.
 */
public interface MiniMessageProvider {

    /**
     * A constant MiniMessage instance.
     */
    MiniMessage MM = MiniMessage.miniMessage();

    /**
     * Returns a MiniMessage instance.
     *
     * @return a MiniMessage instance
     */
    static MiniMessage miniMessage() {
        return MiniMessage.miniMessage();
    }
}
