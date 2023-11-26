package lol.aabss.skick.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import lol.aabss.skick.Skick;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.Kick4JBuilder;
import uk.co.mistyknives.kick4j.auth.AuthCredential;
import uk.co.mistyknives.kick4j.events.EventType;
import uk.co.mistyknives.kick4j.events.impl.client.ReadyEvent;
import uk.co.mistyknives.kick4j.util.Logger;

import javax.annotation.Nullable;

public class EffKickLogin extends Effect {

    static {
        Skript.registerEffect(EffKickLogin.class,
                "[[s]kick] login [to [the] kick [api]] with username %string% [and] with [password] %string% to watch [over] [channel [id]] %integers%"
        );
    }

    Expression<String> username;
    Expression<String> password;
    Expression<Integer> channel;

    @Override
    protected void execute(@NotNull Event e) {
        Skick.client = Kick4JBuilder.builder()
                .credentials(new AuthCredential(username.toString(), password.toString()))
                .logType(Logger.DEBUG)
                .join(channel.getArray(e))
                .on(EventType.READY, (ReadyEvent event) -> Skick.getPlugin(Skick.class).getLogger().info("Logged in to Kick API!"))
                .build();
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
