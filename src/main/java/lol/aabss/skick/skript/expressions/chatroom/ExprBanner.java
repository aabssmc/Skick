package lol.aabss.skick.skript.expressions.chatroom;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import lol.aabss.skick.events.chatroom.ChatterBanBukkit;
import lol.aabss.skick.events.chatroom.ChatterUnbanBukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.events.impl.chatroom.ChatterBanEvent;
import uk.co.mistyknives.kick4j.events.impl.chatroom.ChatterUnbanEvent;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;

public class ExprBanner extends SimpleExpression<Object> {

    static{
        Skript.registerExpression(ExprBanner.class, Object.class, ExpressionType.SIMPLE,
                "[the] [event-]banner"
        );
    }

    @Override
    protected @Nullable Object[] get(@NotNull Event e) {
        if (e instanceof ChatterBanBukkit){
            return new ChatterBanEvent.User[]{((ChatterBanBukkit) e).getEvent().getModerator()};
        }
        else if (e instanceof ChatterUnbanBukkit){
            return new ChatterUnbanEvent.User[]{((ChatterUnbanBukkit) e).getEvent().getModerator()};
        }
        return new Object[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @NotNull
    @Override
    public Class<?> getReturnType() {
        return ChatterBanEvent.User.class;
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the banner";
    }

    @Override
    public boolean init(@NotNull Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parseResult) {
        if (client == null){
            Skript.error("Kick isn't logged in. (see SecKickLogin)");
            return false;
        }
        if (!getParser().isCurrentEvent(ChatterBanBukkit.class, ChatterUnbanBukkit.class)) {
            Skript.error("Cannot use 'banner' outside of a ban/unban event", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }
}
