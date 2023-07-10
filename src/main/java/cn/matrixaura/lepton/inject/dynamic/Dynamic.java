package cn.matrixaura.lepton.inject.dynamic;

public abstract class Dynamic {

    private final String name;

    public Dynamic(String name) {
        this.name = name;
    }

    public abstract byte[] dump() throws Exception;

    public String getName() {
        return name;
    }
}
