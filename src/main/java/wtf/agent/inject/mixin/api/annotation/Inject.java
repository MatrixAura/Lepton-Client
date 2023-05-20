package wtf.agent.inject.mixin.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    String method();
    String descriptor() default "()V";
}
