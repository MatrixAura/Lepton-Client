package cn.matrixaura.lepton.bind;

public class KeyBind {

    private int keyCode;
    private final Runnable action;

    public KeyBind(int keyCode, Runnable action) {
        this.keyCode = keyCode;
        this.action = action;
    }

    public void invoke() {
        action.run();
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

}
