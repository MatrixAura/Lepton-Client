package wtf.agent.client.listener.bus;

/**
 * @author aesthetical
 * @since 04/27/23
 */
public class CancelableEvent {
    private boolean canceled;

    public boolean isCanceled() {
        return canceled;
    }

    public void cancel() {
        canceled = true;
    }
}
