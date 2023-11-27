package lol.aabss.skick.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Section;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.skriptlang.skript.lang.entry.EntryContainer;
import org.skriptlang.skript.lang.entry.EntryValidator;
import org.skriptlang.skript.lang.entry.util.ExpressionEntryData;
import uk.co.mistyknives.kick4j.Kick4JBuilder;
import uk.co.mistyknives.kick4j.auth.AuthCredential;
import uk.co.mistyknives.kick4j.events.EventType;
import uk.co.mistyknives.kick4j.util.Logger;

import javax.annotation.Nullable;
import java.util.List;

import static lol.aabss.skick.Skick.client;
import static lol.aabss.skick.Skick.instance;

@Name("Kick Login")
@Examples({
                "on load:",
                "\tlogin to kick:",
                "\t\tusername: oiiink",
                "\t\tpassword: OiiinkYT?!?!",
                "\t\tchannels: 7572455"})

public class SecKickLogin extends Section {

    static final EntryValidator.EntryValidatorBuilder ENTRY_VALIDATOR = EntryValidator.builder();

    Expression<String> username;
    Expression<String> password;
    Expression<Integer[]> channels;

    static {
        Skript.registerSection(SecKickLogin.class,
                "[[s]kick] login to [the] kick [api]"
        );
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("username", null, false, String.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("password", null, false, String.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("channels", null, false, Integer[].class));
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(@NotNull Expression<?>[] exprs,
                        int matchedPattern,
                        @NotNull Kleenean isDelayed,
                        @NotNull SkriptParser.ParseResult parseResult,
                        @NotNull SectionNode sectionNode,
                        @NotNull List<TriggerItem> triggerItems) {
        EntryContainer container = ENTRY_VALIDATOR.build().validate(sectionNode);
        if (container == null) return false;
        username = (Expression<String>) container.getOptional("username", false);
        if (this.username == null) return false;
        password = (Expression<String>) container.getOptional("password", false);
        if (this.password == null) return false;
        channels = (Expression<Integer[]>) container.getOptional("channels", false);
        return this.channels != null;
    }

    @Override
    protected @Nullable TriggerItem walk(@NotNull Event e) {
        client = Kick4JBuilder.builder()
                .credentials(new AuthCredential(username.toString(), password.toString()))
                .logType(Logger.DEBUG)
                .join(channels.getSingle(e))
                .on(EventType.READY, (event) ->
                        instance.getLogger().info("Logged in to Kick API!")
                )
                .build();
        client.login();
        return super.walk(e, false);
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "login to kick";
    }
}
