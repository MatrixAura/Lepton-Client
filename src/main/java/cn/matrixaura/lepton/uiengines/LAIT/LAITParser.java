package cn.matrixaura.lepton.uiengines.LAIT;

import cn.matrixaura.lepton.uiengines.LAIT.nodes.render.RectangleNode;
import cn.matrixaura.lepton.uiengines.LAIT.nodes.render.TextNode;
import cn.matrixaura.lepton.util.json.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

class LAITParser {

    protected static Node parse(JsonObject obj) {
        float version = obj.get("api_version").getAsFloat();
        Node node = new Node();
        if (version == 1.0) {
            Object2Node(obj, node);
        }

        return node;
    }

    private static void Object2Node(JsonObject obj, Node parent) {
        JsonArray body = obj.get("body").getAsJsonArray();
        if (body.size() == 0) return;
        body.forEach(jsonElement -> {
            JsonObject nodeObj = jsonElement.getAsJsonObject();
            Node node = parseObject(nodeObj);
            parent.body.add(node);
            Object2Node(nodeObj, node);
        });
    }

    private static Node parseObject(JsonObject obj) {
        String type = obj.get("type").getAsString();
        switch (type) {
            case "Rectangle": {
                return JsonUtils.BeanGenerator.fromJson(obj, RectangleNode.class);
            }
            case "Text": {
                return JsonUtils.BeanGenerator.fromJson(obj, TextNode.class);
            }
            default: {
                throw new RuntimeException("Invalid type");
            }
        }
    }

}
