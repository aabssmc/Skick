package lol.aabss.skick.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;

public class ExprUserID extends SimpleExpression<Integer> {

    Expression<String> user;
    Expression<String> channel;

    static{
        Skript.registerExpression(ExprUserID.class, Integer.class, ExpressionType.COMBINED,
                "[the] id of [user] %string% from [channel] %string%",
                "[user] %string%'s id from [channel] %string%"
        );
    }


    @Override
    protected @Nullable Integer[] get(@NotNull Event e) {
        return new Integer[]{client.chatrooms.cache.getChatter(channel.getSingle(e), user.getSingle(e)).getId()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @NotNull
    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "id of user";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(@NotNull Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parseResult) {
        if (client == null){
            Skript.error("Kick isn't logged in. (see SecKickLogin)");
            return false;
        }
        user = (Expression<String>) exprs[0];
        channel = (Expression<String>) exprs[1];
        return true;
    }
}
