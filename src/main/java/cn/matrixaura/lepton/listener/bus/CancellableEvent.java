package cn.matrixaura.lepton.listener.bus;

/**
 * @author aesthetical
 * @since 04/27/23
 */
public class CancellableEvent extends Event {
    private boolean canceled;

    public boolean isCanceled() {
        return canceled;
    }

    public void cancel() {
        canceled = true;
    }
}
