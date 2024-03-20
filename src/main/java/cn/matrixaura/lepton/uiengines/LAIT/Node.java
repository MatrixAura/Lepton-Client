package cn.matrixaura.lepton.uiengines.LAIT;

import java.util.LinkedList;

public abstract class Node {
    public Node parent = null;
    public final String type;
    public String id = "";
    public final LinkedList<Node> body = new LinkedList<>();

    public Node(String type) {
        this.type = type;
    }

    protected abstract void run();

    public void runAll() {
        this.run();
        body.forEach(Node::runAll);
    }
}
