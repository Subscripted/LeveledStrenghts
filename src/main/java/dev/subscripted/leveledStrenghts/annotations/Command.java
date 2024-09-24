package dev.subscripted.leveledStrenghts.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String name();
    String permission() default "";
    boolean tabCompleter() default false;
    TabCompleteArgument[] tabCompletionArgs() default {};
}
