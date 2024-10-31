package de.ole101.i18n.api.services;

import de.ole101.i18n.api.configurations.TranslationConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public interface TranslationService {

    void initialize(@NotNull TranslationConfiguration configuration);

    void registerBundle(@NotNull String namespace, @NotNull Locale locale);
}
