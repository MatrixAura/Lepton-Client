package cn.matrixaura.lepton.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
    String name();

    String description() default "";

    Category category();

    int key() default 0;

    boolean enabled() default false;

    boolean canToggle() default true;
}
