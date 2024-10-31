package de.ole101.i18n.example.common;

import com.google.inject.AbstractModule;
import de.ole101.i18n.api.services.TranslationService;
import de.ole101.i18n.api.services.impl.TranslationServiceImpl;

public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TranslationService.class).to(TranslationServiceImpl.class).asEagerSingleton();
    }
}
