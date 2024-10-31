package de.ole101.i18n.api;

import net.kyori.adventure.text.minimessage.MiniMessage;

public interface MiniMessageProvider {

    MiniMessage MM = MiniMessage.miniMessage();

    static MiniMessage miniMessage() {
        return MiniMessage.miniMessage();
    }
}
