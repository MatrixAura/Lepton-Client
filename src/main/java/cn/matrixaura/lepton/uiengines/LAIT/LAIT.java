package cn.matrixaura.lepton.uiengines.LAIT;

import cn.matrixaura.lepton.module.NonModuleBus;
import cn.matrixaura.lepton.util.file.FileUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.util.Objects;

public class LAIT {

    private final Node program;
    private Runnable prevFunc;
    private Runnable postFunc;

    private LAIT(Node program) {
        if (!Objects.equals(program.type, "Program")) throw new RuntimeException("不是根节点");
        this.program = program;
    }

    public static LAIT parse(String path) {
        return new LAIT(LAITParser.parse(new JsonParser().parse(FileUtils.readPath(path)).getAsJsonObject()));
    }

    public static LAIT parse(JsonObject source) {
        return new LAIT(LAITParser.parse(source));
    }

    public LAIT register() {
        NonModuleBus.renderList.add(this);
        return this;
    }

    public void render() {
        prevFunc.run();
        program.runAll();
        postFunc.run();
    }

    public LAIT setPrevFunc(Runnable runnable) {
        prevFunc = runnable;
        return this;
    }

    public LAIT setPostFunc(Runnable runnable) {
        postFunc = runnable;
        return this;
    }

}
