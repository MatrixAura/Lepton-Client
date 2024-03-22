package cn.matrixaura.lepton.uiengines.LAIT;

import java.util.LinkedList;

public class Node {
    public transient Node parent = null;
    public final String type;
    public String id = "";
    public final LinkedList<Node> body = new LinkedList<>();

    protected Node(String type) {
        this.type = type;
    }

    protected Node() {
        this.type = "Program";
    }

    public void runAll() {
        body.forEach(Node::runAll);
    }
}
