package lol.aabss.skick.skript;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;
import uk.co.mistyknives.kick4j.events.impl.data.EventLivestream;

public class Types {
    static{
        Classes.registerClass(new ClassInfo<>(EventChannel.class, "kickchannel")
                .user("kick ?channel?")
                .name("kick channel")
                .description("Represents a Kick event channel.")
                .since("1.0")
                .defaultExpression(new EventValueExpression<>(EventChannel.class))
                .parser(new Parser<EventChannel>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return false;
                    }

                    @Override
                    public @NotNull String toVariableNameString(EventChannel channel) {
                        return channel.getSlug();
                    }

                    @Override
                    public @NotNull String toString(EventChannel channel, int flags) {
                        return toVariableNameString(channel);
                    }
                })
        );

        Classes.registerClass(new ClassInfo<>(EventLivestream.class, "kicklivestream")
                .user("kick ?live ?stream?")
                .name("kick livestream")
                .description("Represents a Kick event livestream.")
                .since("1.0")
                .defaultExpression(new EventValueExpression<>(EventLivestream.class))
                .parser(new Parser<EventLivestream>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return false;
                    }

                    @Override
                    public @NotNull String toVariableNameString(EventLivestream stream) {
                        return stream.getSessionTitle();
                    }

                    @Override
                    public @NotNull String toString(EventLivestream stream, int flags) {
                        return toVariableNameString(stream);
                    }
                })
        );

    }
}
