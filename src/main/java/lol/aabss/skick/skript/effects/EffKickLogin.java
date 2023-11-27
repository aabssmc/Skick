package lol.aabss.skick.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.Kick4JBuilder;
import uk.co.mistyknives.kick4j.auth.AuthCredential;
import uk.co.mistyknives.kick4j.events.EventType;
import uk.co.mistyknives.kick4j.util.Logger;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;
import static lol.aabss.skick.Skick.instance;

public class EffKickLogin extends Effect {

    static {
        Skript.registerEffect(EffKickLogin.class,
                "[[s]kick] login [to [the] kick [api]] with username %string% [and] with [password] %string% to watch [over] [channel] %integers%"
        );
    }

    Expression<String> username;
    Expression<String> password;
    Expression<Integer> channel;

    @Override
    protected void execute(@NotNull Event e) {
        client = Kick4JBuilder.builder()
                .credentials(new AuthCredential(username.toString(), password.toString()))
                .logType(Logger.DEBUG)
                .join(channel.getArray(e))
                .on(EventType.READY, (event) ->
                        instance.getLogger().info("Logged in to Kick API!")
                )
                .build();
        client.login();
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "kick login";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(@NotNull Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parseResult) {
        username = (Expression<String>) exprs[0];
        password = (Expression<String>) exprs[1];
        channel = (Expression<Integer>) exprs[2];
        return true;
    }
}
