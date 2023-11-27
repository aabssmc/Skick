package lol.aabss.skick.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.api.chatrooms.payload.Banned;

import javax.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static lol.aabss.skick.Skick.client;

public class ExprAllBans extends SimpleExpression<String> {

    Expression<String> channel;

    static{
        Skript.registerExpression(ExprAllBans.class, String.class, ExpressionType.SIMPLE,
                "[all] [[of] the] ban(s|ned users) from [channel] %string%"
        );
    }

    @Override
    protected @Nullable String[] get(@NotNull Event e) {
        List<String> users = new ArrayList<>();
        for (Banned user : client.chatrooms.cache.getBans(channel.getSingle(e))){
            users.add(user.getBannedUser().getUsername());
        }
        return users.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @NotNull
    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "all bans";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(@NotNull Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parseResult) {
        if (client == null){
            Skript.error("Kick isn't logged in. (see SecKickLogin)");
            return false;
        }
        channel = (Expression<String>) exprs[0];
        return true;
    }
}
