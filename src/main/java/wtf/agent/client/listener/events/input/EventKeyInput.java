package wtf.agent.client.listener.events.input;

public class EventKeyInput {
    private final int keyCode;

    public EventKeyInput(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
