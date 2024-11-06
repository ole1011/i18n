package de.ole101.i18n.api.configurations;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Set;


/**
 * Represents the configuration for translation services.
 *
 * @param translationKey   the key identifying the translation registry
 * @param defaultLocale    the default locale for translations
 * @param supportedLocales the set of locales supported for translations
 */
public record TranslationConfiguration(Key translationKey, Locale defaultLocale, Set<Locale> supportedLocales) {

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
        return new TranslationConfiguration(translationKey, defaultLocale, supportedLocales);
    }
}
