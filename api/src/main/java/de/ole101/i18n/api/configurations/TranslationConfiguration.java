package de.ole101.i18n.api.configurations;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Set;

/**
 * Represents the configuration for translation services.
 *
 * @param translationKey   the key identifying the translation registry
 * @param defaultLocale    the default locale for translations
 * @param supportedLocales the set of locales supported for translations
 * @param miniMessage      the MiniMessage instance used for message processing
 */
public record TranslationConfiguration(Key translationKey, Locale defaultLocale, Set<Locale> supportedLocales,
                                       MiniMessage miniMessage) {

    /**
     * Creates a new instance of TranslationConfiguration with the specified translation key, default locale,
     * and supported locales.
     *
     * @param translationKey   the key identifying the translation registry, must not be null
     * @param defaultLocale    the default locale for translations, must not be null
     * @param supportedLocales the set of locales supported for translations, must not be null
     * @return a new TranslationConfiguration instance initialized with the given parameters
     */
    public static TranslationConfiguration create(@NotNull Key translationKey, @NotNull Locale defaultLocale, @NotNull Set<Locale> supportedLocales) {
        return new TranslationConfiguration(translationKey, defaultLocale, supportedLocales, MiniMessage.miniMessage());
    }

    /**
     * Creates a new instance of TranslationConfiguration with the specified parameters.
     *
     * @param translationKey   the key identifying the translation registry, must not be null
     * @param defaultLocale    the default locale for translations, must not be null
     * @param supportedLocales the set of locales supported for translations, must not be null
     * @param miniMessage      the MiniMessage instance used for message processing, must not be null
     * @return a new TranslationConfiguration instance initialized with the given parameters
     */
    public static TranslationConfiguration create(@NotNull Key translationKey, @NotNull Locale defaultLocale, @NotNull Set<Locale> supportedLocales, @NotNull MiniMessage miniMessage) {
        return new TranslationConfiguration(translationKey, defaultLocale, supportedLocales, miniMessage);
    }
}
