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

public class EffBan extends Effect {

    Expression<String> banned;
    Expression<String> channel;
    Expression<String> reason;
    boolean unban;

    static{
        Skript.registerEffect(EffTimeout.class,
                "[[s]kick] ban [user] %string% from [channel] %string% [because [of] [reason] %-string%]",
                "[[s]kick] unban [user] %string% from [channel] %string%"
        );
    }

    @Override
    protected void execute(@NotNull Event e) {
        if (!unban){
            if (reason != null){
                client.chatrooms.cache.ban(channel.getSingle(e), banned.getSingle(e), reason.getSingle(e));
            }
            else{
                client.chatrooms.cache.ban(channel.getSingle(e), banned.getSingle(e), "Skick Ban");
            }
        }
        else{
            client.chatrooms.cache.unban(channel.getSingle(e), banned.getSingle(e));
        }
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "kick " + (unban ? "unban" : "ban") + " user";
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
        if (matchedPattern == 1){
            unban = true;
        }
        return true;
    }
}
