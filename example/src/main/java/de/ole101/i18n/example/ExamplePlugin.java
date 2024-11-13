package de.ole101.i18n.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.ole101.i18n.api.configurations.TranslationConfiguration;
import de.ole101.i18n.api.services.TranslationService;
import de.ole101.i18n.example.common.GuiceModule;
import lombok.extern.log4j.Log4j2;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.Set;

import static net.kyori.adventure.text.Component.text;

@Log4j2
public class ExamplePlugin extends JavaPlugin {

    private static final Key TRANSLATION_KEY = Key.key("example:i18n");
    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    private static final Set<Locale> SUPPORTED_LOCALES = Set.of(Locale.ENGLISH, Locale.GERMAN);

    private TranslationService translationService;

    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(new GuiceModule());
        this.translationService = injector.getInstance(TranslationService.class);

        TranslationConfiguration configuration = TranslationConfiguration.create(TRANSLATION_KEY, DEFAULT_LOCALE, SUPPORTED_LOCALES);

        this.translationService.initialize(configuration);
        SUPPORTED_LOCALES.forEach(locale -> this.translationService.registerBundle("common", locale));

        Component translatable = Component.translatable("test.translation", text("123"));

        Bukkit.getConsoleSender().sendMessage(translatable);
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(translatable));
    }
}
