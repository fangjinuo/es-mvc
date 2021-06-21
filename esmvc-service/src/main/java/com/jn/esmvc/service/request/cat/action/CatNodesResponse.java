package com.jn.esmvc.service.request.cat.action;

import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.function.Consumer;
import org.elasticsearch.action.ActionResponse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CatNodesResponse extends ActionResponse {
    private final List<NodeRuntime> nodes = Collects.emptyArrayList();

    public List<NodeRuntime> getNodes() {
        return nodes;
    }

    public void setNodes(List<Map<String, String>> nodes) {
        Collects.forEach(nodes, new Consumer<Map<String,String>>() {
            @Override
            public void accept(Map<String, String> node) {
                addNode(node);
            }
        });
    }

    public void addNode(Map<String, String> node) {
        Map<String, String> filteredNode = new LinkedHashMap<String, String>();
        Set<Map.Entry<String, String>> set = node.entrySet();
        Collects.forEach(set, new Consumer<Map.Entry<String, String>>() {
            @Override
            public void accept(Map.Entry<String, String> entry) {
                String key = CatNodesMetrics.getStandardMetric(entry.getKey());
                filteredNode.put(key, entry.getValue());
            }
        });
        this.nodes.add(new NodeRuntime(filteredNode));
    }

}
