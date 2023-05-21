package wtf.agent.inject.asm.wrapper.impl.world;

import wtf.agent.inject.asm.wrapper.Wrapper;

public class WorldClientWrapper extends Wrapper {
    private Object worldObj;

    public WorldClientWrapper() {
        super("net/minecraft/client/multiplayer/WorldClient");
    }

    public void setWorldObj(Object worldObj) {
        this.worldObj = worldObj;
    }
}
