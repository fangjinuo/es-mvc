package com.jn.esmvc.service.request.cat.action;

import java.util.LinkedHashMap;
import java.util.Map;

public class CatNodesMetrics {
    private static final Map<String, String> nameMapping = new LinkedHashMap<String, String>();

    static {
        nameMapping.put("i", "ip");

        nameMapping.put("heap.percent", "heapPercent");
        nameMapping.put("hp", "heapPercent");

        nameMapping.put("ram.percent", "ramPercent");
        nameMapping.put("rp", "ramPercent");

        nameMapping.put("file_desc.percent", "fileDescriptorPercent");
        nameMapping.put("fdp", "fileDescriptorPercent");

        nameMapping.put("node.role", "nodeRole");
        nameMapping.put("r", "nodeRole");
        nameMapping.put("role", "nodeRole");

        nameMapping.put("m", "master");

        nameMapping.put("n", "name");

        nameMapping.put("id", "nodeId");

        nameMapping.put("p", "pid");

        nameMapping.put("po", "port");

        nameMapping.put("http", "http_address");

        nameMapping.put("v", "version");

        nameMapping.put("b", "build");

        nameMapping.put("j", "jdk");

        nameMapping.put("disk.total", "diskTotal");
        nameMapping.put("dt", "diskTotal");

        nameMapping.put("disk.used", "diskUsed");
        nameMapping.put("du", "diskUsed");

        nameMapping.put("disk.avail", "diskAvail");
        nameMapping.put("d", "diskAvail");
        nameMapping.put("disk", "diskAvail");

        nameMapping.put("disk.used_percent", "diskUsedPercent");
        nameMapping.put("dup", "diskUsedPercent");

        nameMapping.put("heap.current", "heapCurrent");
        nameMapping.put("hc", "heapCurrent");

        nameMapping.put("ram.current", "ramCurrent");
        nameMapping.put("rc", "ramCurrent");

        nameMapping.put("ram.max", "ramMax");
        nameMapping.put("rm", "ramMax");

        nameMapping.put("file_desc.current", "fileDescriptorCurrent");
        nameMapping.put("fdc", "fileDescriptorCurrent");

        nameMapping.put("file_desc.max", "fileDescriptorMax");
        nameMapping.put("fdm", "fileDescriptorMax");

        nameMapping.put("load_1m", "load_1m");
        nameMapping.put("load_5m", "load_5m");
        nameMapping.put("load_15m", "load_15m");

        nameMapping.put("completion.size", "completionSize");
        nameMapping.put("cs", "completionSize");

        nameMapping.put("fielddata.memory_size", "fielddataMemory");
        nameMapping.put("fm", "fielddataMemory");

        nameMapping.put("fielddata.evictions", "fielddataEvictions");
        nameMapping.put("fe", "fielddataEvictions");

        nameMapping.put("query_cache.memory_size", "queryCacheMemory");
        nameMapping.put("qcm", "queryCacheMemory");

        nameMapping.put("query_cache.evictions", "queryCacheEvictions");
        nameMapping.put("qce", "queryCacheEvictions");

        nameMapping.put("query_cache.hit_count", "queryCacheHitCount");
        nameMapping.put("qchc", "queryCacheHitCount");

        nameMapping.put("query_cache.miss_count", "queryCacheMissCount");
        nameMapping.put("qcmc", "queryCacheMissCount");

        nameMapping.put("request_cache.memory_size", "requestCacheMemory");
        nameMapping.put("rcm", "requestCacheMemory");

        nameMapping.put("request_cache.evictions", "requestCacheEvictions");
        nameMapping.put("rce", "requestCacheEvictions");

        nameMapping.put("request_cache.hit_count", "requestCacheHitCount");
        nameMapping.put("rchc", "requestCacheHitCount");

        nameMapping.put("request_cache.miss_count", "requestCacheMissCount");
        nameMapping.put("rcmc", "requestCacheMissCount");

        nameMapping.put("flush.total", "flushTotal");
        nameMapping.put("ft", "flushTotal");

        nameMapping.put("flush.total_time", "flushTotalTime");
        nameMapping.put("ftt", "flushTotalTime");

        nameMapping.put("get.current", "getCurrent");
        nameMapping.put("gc", "getCurrent");

        nameMapping.put("get.time", "getTime");
        nameMapping.put("gti", "getTime");

        nameMapping.put("get.total", "getTotal");
        nameMapping.put("gto", "getTotal");

        nameMapping.put("get.exists_time", "getExistsTime");
        nameMapping.put("geti", "getExistsTime");

        nameMapping.put("get.exists_total", "getExistsTotal");
        nameMapping.put("geto", "getExistsTotal");

        nameMapping.put("get.missing_time", "getMissingTime");
        nameMapping.put("gmti", "getMissingTime");

        nameMapping.put("get.missing_total", "getMissingTotal");
        nameMapping.put("gmto", "getMissingTotal");
    }

    public static String getStandardMetric(String metric) {
        String name = nameMapping.get(metric);
        if (name == null) {
            name = metric;
        }
        return name;
    }

    public static boolean isValidMetric(String metric) {
        return nameMapping.containsKey(metric);
    }
}
