package de.ole101.i18n.api;

import de.ole101.i18n.api.configurations.TranslationConfiguration;
import lombok.AllArgsConstructor;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.ParsingException;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.translation.TranslationRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

/**
 * A wrapper class for the TranslationRegistry that provides additional functionality
 * for translating components using MiniMessage.
 */
@AllArgsConstructor
public class PluginTranslationRegistry implements TranslationRegistry {

    private final TranslationRegistry delegate;
    private final TranslationConfiguration configuration;

    @Override
    public boolean contains(@NotNull String key) {
        return this.delegate.contains(key);
    }

    @Override
    public @Nullable MessageFormat translate(@NotNull String key, @NotNull Locale locale) {
        return this.delegate.translate(key, locale);
    }

    @Override
    public void defaultLocale(@NotNull Locale locale) {
        this.delegate.defaultLocale(locale);
    }

    @Override
    public void register(@NotNull String key, @NotNull Locale locale, @NotNull MessageFormat format) {
        this.delegate.register(key, locale, format);
    }

    @Override
    public void unregister(@NotNull String key) {
        this.delegate.unregister(key);
    }

    @Override
    public @NotNull Key name() {
        return this.delegate.name();
    }

    @Override
    public @Nullable Component translate(@NotNull TranslatableComponent component, @NotNull Locale locale) {
        MessageFormat translationFormat = this.delegate.translate(component.key(), locale);
        if (translationFormat == null) {
            return null;
        }

        String miniMessageString = translationFormat.toPattern();

        Component resultingComponent;
        if (component.arguments().isEmpty()) {
            resultingComponent = this.configuration.miniMessage().deserialize(miniMessageString);
        } else {
            resultingComponent = this.configuration.miniMessage().deserialize(miniMessageString, new ArgumentTag(component.arguments()));
        }

        if (component.children().isEmpty()) {
            return resultingComponent;
        } else {
            return resultingComponent.children(component.children());
        }
    }

    /**
     * A TagResolver implementation for resolving argument tags in MiniMessage strings.
     */
    private record ArgumentTag(@NotNull List<? extends ComponentLike> argumentComponents) implements TagResolver {

        private static final String NAME = "argument";
        private static final String ALIAS = "arg";

        /**
         * Resolves a tag in a MiniMessage string.
         *
         * @param name      the name of the tag
         * @param arguments the arguments for the tag
         * @param ctx       the context for the tag
         * @return the resolved Tag
         * @throws ParsingException if the tag cannot be resolved
         */
        @Override
        public @NotNull Tag resolve(@NotNull String name, @NotNull ArgumentQueue arguments, @NotNull Context ctx) throws ParsingException {
            int index = arguments.popOr("No argument number provided")
                    .asInt().orElseThrow(() -> ctx.newException("Invalid argument number", arguments));

            if (index < 0 || index >= this.argumentComponents.size()) {
                throw ctx.newException("Invalid argument number", arguments);
            }

            return Tag.inserting(this.argumentComponents.get(index));
        }

        /**
         * Checks if the given name matches either the NAME or ALIAS constants.
         *
         * @param name the name to be checked
         * @return true if the name matches either NAME or ALIAS; false otherwise
         */
        @Override
        public boolean has(@NotNull String name) {
            return name.equals(NAME) || name.equals(ALIAS);
        }
    }
}
