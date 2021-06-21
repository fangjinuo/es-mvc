package com.jn.esmvc.service.request.cat.action;

import com.jn.langx.util.Emptys;
import com.jn.langx.util.Objs;
import com.jn.langx.util.Preconditions;
import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.collection.StringMap;
import com.jn.langx.util.collection.StringMapAccessor;
import com.jn.langx.util.function.Consumer;
import com.jn.langx.util.function.Predicate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CatNodeAttrsResponse {
    private final Set<NodeInfo> nodes = Collects.emptyHashSet(true);

    public Set<NodeInfo> getNodes() {
        return nodes;
    }

    public void setNodes(List<Map<String, String>> nodes) {
        Collects.forEach(nodes, new Consumer<Map<String, String>>() {
            @Override
            public void accept(Map<String, String> stringStringMap) {
                addNodeAttr(stringStringMap);
            }
        });
    }

    public void addNode(NodeInfo nodeInfo) {
        if (nodeInfo != null) {
            nodes.add(nodeInfo);
        }
    }

    public void addNodeAttr(Map<String, String> attr, String... filterProps) {
        Preconditions.checkNotNull(attr.get("attr"));
        Preconditions.checkNotNull(attr.get("value"));
        Map<String, String> filteredNode = new LinkedHashMap<String, String>();
        Set<Map.Entry<String, String>> set = attr.entrySet();
        Collects.forEach(set, new Consumer<Map.Entry<String, String>>() {
            @Override
            public void accept(Map.Entry<String, String> entry) {
                String key = CatNodeAttrsMetrics.getStandardMetric(entry.getKey());
                filteredNode.put(key, entry.getValue());
            }
        });


        NodeInfo matched = Collects.findFirst(CatNodeAttrsResponse.this.nodes, new Predicate<NodeInfo>() {
            @Override
            public boolean test(NodeInfo nodeInfo) {
                if (filteredNode.get("id") != null) {
                    return Objs.equals(filteredNode.get("id"), nodeInfo.getId());
                }
                if (filteredNode.get("name") != null) {
                    return Objs.equals(filteredNode.get("name"), nodeInfo.getName());
                }

                if (filteredNode.get("host") != null ) {
                    if(Objs.equals(filteredNode.get("host"), nodeInfo.getHost())) {

                        if (filteredNode.get("port") != null && filteredNode.get("pid") != null) {
                            return Objs.equals(filteredNode.get("host"), nodeInfo.getHost())
                                    && Objs.equals(filteredNode.get("port"), nodeInfo.getPort() + "")
                                    && Objs.equals(filteredNode.get("pid"), nodeInfo.getProcessId() + "");
                        }else{
                            if (Emptys.isEmpty(filterProps)) {
                                return true;
                            }
                        }
                    }
                }

                return false;
            }
        });
        if (matched == null) {
            matched = new NodeInfo();
        }

        StringMapAccessor mapAccessor = new StringMapAccessor(new StringMap(filteredNode));

        if (filteredNode.get("id") != null) {
            matched.setId(filteredNode.get("id"));
        }
        if (filteredNode.get("name") != null) {
            matched.setName(filteredNode.get("name"));
        }
        if (filteredNode.get("host") != null) {
            matched.setHost(filteredNode.get("host"));
        }
        if (filteredNode.get("ip") != null) {
            matched.setIp(filteredNode.get("ip"));
        }
        if (filteredNode.get("port") != null) {
            matched.setPort(mapAccessor.getInteger("port"));
        }
        if (filteredNode.get("pid") != null) {
            matched.setProcessId(mapAccessor.getInteger("pid"));
        }
        matched.addAttr(filteredNode.get("attr"), filteredNode.get("value"));
        addNode(matched);


    }
}
