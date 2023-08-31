package cn.matrixaura.lepton.util.player;

public class Rotation {

    private final float rotationYaw;
    private final float rotationPitch;

    public Rotation(float yaw, float pitch) {
        this.rotationYaw = yaw;
        this.rotationPitch = pitch;
    }

    public float getRotationYaw() {
        return rotationYaw;
    }

    public float getRotationPitch() {
        return rotationPitch;
    }
}
