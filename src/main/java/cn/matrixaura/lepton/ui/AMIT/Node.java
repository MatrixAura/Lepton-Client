package cn.matrixaura.lepton.ui.AMIT;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Node {
    private final String type;
    private String id = "";
    private Integer[] align = {0, 0};
    private Double[] offset = {0D, 0D};
    private ArrayList<Node> body = new ArrayList<>();
    private Boolean shown = true;

    public Node(String type) {
        this.type = type;
    }
}
