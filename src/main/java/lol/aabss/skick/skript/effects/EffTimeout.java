package lol.aabss.skick.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;

public class EffTimeout extends Effect {

    Expression<String> banned;
    Expression<String> channel;
    Expression<String> reason;
    Expression<Integer> duration;

    static{
        Skript.registerEffect(EffTimeout.class,
                "[[s]kick] timeout [user] %string% from [channel] %string% [because [of] [reason] %-string%] for %integer% minutes",
                "[[s]kick] untimeout [user] %string% from [channel] %string%"
        );
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    protected void execute(@NotNull Event e) {
        if (duration != null){
            if (reason != null){
                client.chatrooms.cache.timeout(channel.getSingle(e), banned.getSingle(e), reason.getSingle(e), duration.getSingle(e));
            }
            else{
                client.chatrooms.cache.timeout(channel.getSingle(e), banned.getSingle(e), "Skick Timeout", duration.getSingle(e));
            }
        }
        else{
            client.chatrooms.cache.untimeout(channel.getSingle(e), banned.getSingle(e));
        }
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "kick " + ((duration == null) ? "untimeout" : "timeout") + " user";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(@NotNull Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parseResult) {
        if (client == null){
            Skript.error("Kick isn't logged in. (see SecKickLogin)");
            return false;
        }
        banned = (Expression<String>) exprs[0];
        channel = (Expression<String>) exprs[1];
        reason = (Expression<String>) exprs[2];
        duration = (Expression<Integer>) exprs[3];
        return true;
    }
}
