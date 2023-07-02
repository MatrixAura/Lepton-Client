package cn.matrixaura.lepton.listener.bus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author aesthetical
 * @since 04/27/23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Listener {

    /**
     * The event priority
     *
     * @return the event priority
     */
    int eventPriority() default 0;

    /**
     * If to still receive events that have been canceled
     *
     * @return if to receive canceled events
     */
    boolean receiveCanceled() default false;

}
