package wtf.agent.inject.asm.wrapper.impl.world;

import wtf.agent.inject.asm.wrapper.Wrapper;

public class Blocks extends Wrapper {
    private static Blocks instance;

    public Blocks() {
        super("net/minecraft/init/Blocks");
    }

    public Object getBlock(String name) {
        return null;
    }

    public static Blocks get() {
        if (instance == null) instance = new Blocks();
        return instance;
    }
}
