package lol.aabss.skick.skript.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.api.chatrooms.payload.Chatter;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;

public class CondIsOwner extends Condition {

    Expression<String> user;
    Expression<String> channel;
    boolean is;

    static{
        Skript.registerCondition(CondIsMod.class,
                "%string% (is|are) [a] [channel] owner for %string%",
                "%string% (is|are)( not|n't) [a] [channel] owner for %string%"
        );
    }

    @Override
    public boolean check(@NotNull Event e) {
        Chatter chatter = client.chatrooms.cache.getChatter(channel.getSingle(e), user.getSingle(e));
        if (is){
            return chatter.isChannelOwner();
        }
        return !chatter.isChannelOwner();
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "user is owner";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(@NotNull Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parseResult) {
        if (client == null){
            Skript.error("Kick isn't logged in. (see SecKickLogin)");
            return false;
        }
        is = (matchedPattern == 0);
        user = (Expression<String>) exprs[0];
        channel = (Expression<String>) exprs[1];
        return true;
    }
}
