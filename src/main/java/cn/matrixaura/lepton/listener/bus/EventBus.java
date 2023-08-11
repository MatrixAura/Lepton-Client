package cn.matrixaura.lepton.listener.bus;

import io.netty.util.internal.ConcurrentSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author aesthetical
 * @since 04/27/23
 */
public class EventBus {

    // the event bus logger, separate from the logger
    private static final Logger logger = LogManager.getLogger("EventBus");

    // a map of the event class -> a list of all of its subscribers
    private final Map<Class<?>, List<Subscriber>> eventSubscriberMap = new ConcurrentHashMap<>();

    // the currently registered subscriber objects
    private final Set<Object> subscriptions = new ConcurrentSet<>();

    /**
     * Dispatches an event
     *
     * @param event the event to dispatch
     * @return if the event was canceled (only if extended from CancellableEvent)
     */
    public boolean dispatch(Event event) {
        List<Subscriber> subscribers = eventSubscriberMap.getOrDefault(event.getClass(), null);
        if (subscribers != null && !subscribers.isEmpty()) {
            boolean canceled = false;
            for (Subscriber subscriber : subscribers) {
                if (!subscriber.isReceiveCanceled() && canceled) {
                    continue;
                }

                try {
                    subscriber.invoke(event);
                } catch (Exception e) {
                    logger.error("Failed to dispatch event to {}:", subscriber.getSubscriber());
                    e.printStackTrace();
                }

                if (CancellableEvent.class.isAssignableFrom(event.getClass().getSuperclass())) {
                    canceled = ((CancellableEvent) event).isCanceled();
                }
            }

            return canceled;
        }

        return false;
    }

    /**
     * Subscribes an object to receive events
     *
     * @param subscriber the subscriber
     */
    public void subscribe(Object subscriber) {
        if (!subscriptions.contains(subscriber)) {
            subscriptions.add(subscriber);

            for (Method method : subscriber.getClass().getDeclaredMethods()) {
                if (!method.isAnnotationPresent(Listener.class) || method.getParameterCount() != 1 || method.getReturnType() != void.class) {
                    continue;
                }

                method.setAccessible(true);

                Listener listener = method.getDeclaredAnnotation(Listener.class);

                Class<?> eventClass = method.getParameterTypes()[0];
                List<Subscriber> subscribers = eventSubscriberMap.computeIfAbsent(eventClass, (s) -> new CopyOnWriteArrayList<>());

                try {
                    subscribers.add(new Subscriber(subscriber, method, listener.eventPriority(), listener.receiveCanceled()));
                    eventSubscriberMap.put(eventClass, subscribers);
                } catch (Throwable e) {
                    logger.error("Failed to subscribe method {} in {}:", method, subscriber);
                    e.printStackTrace();
                }
            }

            eventSubscriberMap.forEach((c, k) -> {
                sortSubscribers(k);
                eventSubscriberMap.put(c, k);
            });
        }
    }

    /**
     * Unsubscribes an event
     *
     * @param subscriber the subscriber
     */
    public void unsubscribe(Object subscriber) {
        if (subscriptions.contains(subscriber)) {
            for (Class<?> eventClass : eventSubscriberMap.keySet()) {
                List<Subscriber> subscribers = eventSubscriberMap.get(eventClass);
                subscribers.removeIf(sub -> sub.getSubscriber().equals(subscriber));

                if (subscribers.isEmpty()) {
                    eventSubscriberMap.remove(eventClass);
                } else {
                    sortSubscribers(subscribers);
                    eventSubscriberMap.put(eventClass, subscribers);
                }
            }

            subscriptions.remove(subscriber);
        }
    }

    /**
     * Sorts subscribers least to greatest to their priority
     *
     * @param in the list of subscribers to sort
     */
    private void sortSubscribers(List<Subscriber> in) {
        in.sort(Comparator.comparingInt((s) -> -s.getEventPriority()));
    }
}
