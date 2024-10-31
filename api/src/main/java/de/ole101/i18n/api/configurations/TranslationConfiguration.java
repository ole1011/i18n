package de.ole101.i18n.api.configurations;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Set;

public record TranslationConfiguration(Key translationKey, Locale defaultLocale, Set<Locale> supportedLocales) {

    public static TranslationConfiguration create(@NotNull Key translationKey, @NotNull Locale defaultLocale, @NotNull Set<Locale> supportedLocales) {
        return new TranslationConfiguration(translationKey, defaultLocale, supportedLocales);
    }
}
