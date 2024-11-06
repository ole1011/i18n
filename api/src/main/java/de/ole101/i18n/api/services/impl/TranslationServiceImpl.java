package de.ole101.i18n.api.services.impl;

import de.ole101.i18n.api.PluginTranslationRegistry;
import de.ole101.i18n.api.configurations.TranslationConfiguration;
import de.ole101.i18n.api.services.TranslationService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.kyori.adventure.translation.GlobalTranslator;
import net.kyori.adventure.translation.TranslationRegistry;
import net.kyori.adventure.util.UTF8ResourceBundleControl;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Implementation of the {@link TranslationService} interface.
 * This class provides methods to initialize the translation service and register resource bundles.
 */
@Slf4j
@Getter
public class TranslationServiceImpl implements TranslationService {

    private TranslationRegistry translationRegistry;
    private TranslationConfiguration configuration;

    /**
     * Initializes the translation service with the given configuration.
     *
     * @param configuration the translation configuration
     * @throws NullPointerException if the configuration is null
     */
    @Override
    public void initialize(@NotNull TranslationConfiguration configuration) {
        Objects.requireNonNull(configuration, "configuration must not be null");

        this.translationRegistry = new PluginTranslationRegistry(
                TranslationRegistry.create(configuration.translationKey())
        );
        this.translationRegistry.defaultLocale(configuration.defaultLocale());
        this.configuration = configuration;

        GlobalTranslator.translator().addSource(this.translationRegistry);
        log.info("Initialized translation service with key: {}", configuration.translationKey());
    }

    /**
     * Registers a resource bundle for the given namespace and locale.
     *
     * @param namespace the namespace of the resource bundle
     * @param locale    the locale of the resource bundle
     * @throws IllegalStateException if the translation service is not initialized
     */
    @Override
    public void registerBundle(@NotNull String namespace, @NotNull Locale locale) {
        if (this.translationRegistry == null || this.configuration == null) {
            throw new IllegalStateException("Translation service not initialized");
        }

        try {
            ResourceBundle bundle = ResourceBundle.getBundle(namespace, locale, UTF8ResourceBundleControl.get());
            this.translationRegistry.registerAll(locale, bundle, true);
            log.debug("Registered bundle {} for locale {}", namespace, locale);
        } catch (MissingResourceException exception) {
            log.error("Failed to load resource bundle: {} for locale: {}", namespace, locale, exception);
        }
    }
}
