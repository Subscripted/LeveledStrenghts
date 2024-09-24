package dev.subscripted.leveledStrenghts.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TabCompleteArgument {
    int argIndex();
    String argText();
    String permission() default "";
    SubTabCompleteArgument[] subTabCompletionArgs() default {};
}