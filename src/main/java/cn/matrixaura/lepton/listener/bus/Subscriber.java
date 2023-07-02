package cn.matrixaura.lepton.listener.bus;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aesthetical
 * @since 04/27/23
 */
public class Subscriber {
    private static final Map<Method, Invokable> invokableCache = new HashMap<>();
    private static final Lookup LOOKUP = MethodHandles.lookup();

    private final int eventPriority;
    private final boolean receiveCanceled;

    private final Object subscriber;
    private final Invokable methodHandle;

    public Subscriber(Object subscriber, Method method, int eventPriority, boolean receiveCanceled) throws Throwable {
        this.subscriber = subscriber;
        this.eventPriority = eventPriority;
        this.receiveCanceled = receiveCanceled;

        if (!invokableCache.containsKey(method)) {
            // this part isnt my code, this is from bush's event bus
            // this invocation method is faster than plain reflection
            MethodType methodType = MethodType.methodType(Invokable.class);
            CallSite callSite = LambdaMetafactory.metafactory(
                    LOOKUP,
                    "invoke",
                    methodType.appendParameterTypes(subscriber.getClass()),
                    MethodType.methodType(void.class, Object.class),
                    LOOKUP.unreflect(method),
                    MethodType.methodType(void.class, method.getParameterTypes()[0])
            );
            methodHandle = (Invokable) callSite.getTarget().invoke(subscriber);
            invokableCache.put(method, methodHandle);
        } else {
            methodHandle = invokableCache.get(method);
        }
    }

    public void invoke(Object object) {
        methodHandle.invoke(object);
    }

    public int getEventPriority() {
        return eventPriority;
    }

    public boolean isReceiveCanceled() {
        return receiveCanceled;
    }

    public Object getSubscriber() {
        return subscriber;
    }

    private interface Invokable {
        void invoke(Object event);
    }
}
