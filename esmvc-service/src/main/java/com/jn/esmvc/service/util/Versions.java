package com.jn.esmvc.service.util;

import com.jn.esmvc.service.ClientWrapper;
import com.jn.esmvc.service.request.cat.action.CatNodesResponse;
import com.jn.esmvc.service.request.cat.action.NodeRuntime;
import com.jn.langx.util.Emptys;
import com.jn.langx.util.Strings;
import com.jn.langx.util.collection.Pipeline;
import com.jn.langx.util.function.Function;
import org.elasticsearch.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Versions {
    private static final Logger logger = LoggerFactory.getLogger(Versions.class);

    public static boolean checkVersion(ClientWrapper wrapper) {
        List<String> nodesVersions = null;
        try {
            CatNodesResponse response = wrapper.cat().nodes(null, null);
            nodesVersions = Pipeline.of(response.getNodes()).map(new Function<NodeRuntime, String>() {
                @Override
                public String apply(NodeRuntime nodeRuntime) {
                    return nodeRuntime.getVersion();
                }
            }).distinct().asList();
        } catch (Throwable ex) {
            logger.warn("Error occur when get servers version with the {}", wrapper);
        }

        if (Emptys.isNotEmpty(nodesVersions)) {
            if (nodesVersions.size() > 1) {
                logger.error("has multiple version: {}", Strings.join(",", nodesVersions));
                return false;
            } else {
                String version = nodesVersions.get(0);
                if (Version.CURRENT.toString().equalsIgnoreCase(version)) {
                    logger.info("======Client version: {}, Server version: {}======", Version.CURRENT.toString(), version);
                    return true;
                } else {
                    logger.warn("======Client version: {}, Server version: {}======", Version.CURRENT.toString(), version);
                    return false;
                }
            }
        }

        logger.warn("Can't find the server version");
        return false;
    }
}
