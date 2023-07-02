package cn.matrixaura.lepton.inject.asm.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    String method();

    String descriptor() default "()V";
}
