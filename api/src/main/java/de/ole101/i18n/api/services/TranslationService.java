package de.ole101.i18n.api.services;

import de.ole101.i18n.api.configurations.TranslationConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Interface for translation services.
 * This interface provides methods to initialize the translation service and register resource bundles.
 */
public interface TranslationService {

    /**
     * Initializes the translation service with the given configuration.
     *
     * @param configuration the translation configuration
     * @throws NullPointerException if the configuration is null
     */
    void initialize(@NotNull TranslationConfiguration configuration);

    /**
     * Registers a resource bundle for the given namespace and locale.
     *
     * @param namespace the namespace of the resource bundle
     * @param locale    the locale of the resource bundle
     * @throws IllegalStateException if the translation service is not initialized
     */
    void registerBundle(@NotNull String namespace, @NotNull Locale locale);
}
