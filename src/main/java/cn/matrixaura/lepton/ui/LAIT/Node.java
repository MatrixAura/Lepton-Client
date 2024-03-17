package cn.matrixaura.lepton.ui.LAIT;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Node {
    private final String type;
    private String id = "";
    private final ArrayList<Node> body = new ArrayList<>();

    public Node(String type) {
        this.type = type;
    }
}
