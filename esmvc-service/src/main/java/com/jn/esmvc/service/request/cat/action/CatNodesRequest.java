package com.jn.esmvc.service.request.cat.action;

import com.jn.langx.annotation.Nullable;
import com.jn.langx.util.DataUnit;
import com.jn.langx.util.collection.Pipeline;
import com.jn.langx.util.function.Function;
import com.jn.langx.util.timing.TimeUnit2;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.tasks.Task;
import org.elasticsearch.tasks.TaskId;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CatNodesRequest extends ActionRequest {

    @Override
    public String getDescription() {
        return "GET /_cat/nodes";
    }

    @Nullable
    private DataUnit dataUnit;

    /**
     * json, yaml
     */
    private String format = "json";

    @Nullable
    private TimeUnit2 timeUnit;

    @Nullable
    private List<String> metrics;

    public DataUnit getDataUnit() {
        return dataUnit;
    }

    public void setDataUnit(DataUnit dataUnit) {
        this.dataUnit = dataUnit;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public TimeUnit2 getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit2 timeUnit) {
        this.timeUnit = timeUnit;
    }

    public List<String> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<String> metrics) {
        this.metrics = Pipeline.of(metrics).map(new Function<String, String>() {
            @Override
            public String apply(String metric) {
                if (Metrics.isValidMetric(metric)) {
                    return Metrics.getStandardMetric(metric);
                }
                return null;
            }
        }).clearNulls().asList();

    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }

    @Override
    public void setParentTask(String parentTaskNode, long parentTaskId) {

    }

    @Override
    public Task createTask(long id, String type, String action, TaskId parentTaskId, Map<String, String> headers) {
        return null;
    }


    private static class Metrics {
        private static final Map<String, String> nameMapping = new LinkedHashMap<String, String>();

        static {
            nameMapping.put("ip", "ip");
            nameMapping.put("i", "ip");

            nameMapping.put("heap.percent", "heapPercent");
            nameMapping.put("hp", "heapPercent");
            nameMapping.put("heapPercent", "heapPercent");

            nameMapping.put("ram.percent", "ramPercent");
            nameMapping.put("rp", "ramPercent");
            nameMapping.put("ramPercent", "ramPercent");

            nameMapping.put("file_desc.percent", "fileDescriptorPercent");
            nameMapping.put("fdp", "fileDescriptorPercent");
            nameMapping.put("fileDescriptorPercent", "fileDescriptorPercent");

            nameMapping.put("node.role", "nodeRole");
            nameMapping.put("r", "nodeRole");
            nameMapping.put("role", "nodeRole");
            nameMapping.put("nodeRole", "nodeRole");

            nameMapping.put("m", "master");
            nameMapping.put("master", "master");

            nameMapping.put("n", "name");
            nameMapping.put("name", "name");

            nameMapping.put("id", "nodeId");
            nameMapping.put("nodeId", "nodeId");

            nameMapping.put("p", "pid");
            nameMapping.put("pid", "pid");

            nameMapping.put("po", "port");
            nameMapping.put("port", "port");

            nameMapping.put("http_address", "http_address");
            nameMapping.put("http", "http_address");

            nameMapping.put("v", "version");
            nameMapping.put("version", "version");

            nameMapping.put("build", "build");
            nameMapping.put("b", "build");

            nameMapping.put("j", "jdk");
            nameMapping.put("jdk", "jdk");

            nameMapping.put("disk.total", "diskTotal");
            nameMapping.put("dt", "diskTotal");
            nameMapping.put("diskTotal", "diskTotal");

            nameMapping.put("disk.used", "diskUsed");
            nameMapping.put("du", "diskUsed");
            nameMapping.put("diskUsed", "diskUsed");

            nameMapping.put("disk.avail", "diskAvail");
            nameMapping.put("d", "diskAvail");
            nameMapping.put("disk", "diskAvail");
            nameMapping.put("diskAvail", "diskAvail");

            nameMapping.put("disk.used_percent", "diskUsedPercent");
            nameMapping.put("dup", "diskUsedPercent");
            nameMapping.put("diskUsedPercent", "diskUsedPercent");

            nameMapping.put("heap.current", "heapCurrent");
            nameMapping.put("hc", "heapCurrent");
            nameMapping.put("heapCurrent", "heapCurrent");

            nameMapping.put("ram.current", "ramCurrent");
            nameMapping.put("rc", "ramCurrent");
            nameMapping.put("ramCurrent", "ramCurrent");

            nameMapping.put("ram.max", "ramMax");
            nameMapping.put("rm", "ramMax");
            nameMapping.put("ramMax", "ramMax");

            nameMapping.put("file_desc.current", "fileDescriptorCurrent");
            nameMapping.put("fdc", "fileDescriptorCurrent");
            nameMapping.put("fileDescriptorCurrent", "fileDescriptorCurrent");

            nameMapping.put("file_desc.max", "fileDescriptorMax");
            nameMapping.put("fdm", "fileDescriptorMax");
            nameMapping.put("fileDescriptorMax", "fileDescriptorMax");

            nameMapping.put("cpu", "cpu");

            nameMapping.put("uptime", "uptime");

            nameMapping.put("load_1m", "load_1m");
            nameMapping.put("load_5m", "load_5m");
            nameMapping.put("load_15m", "load_15m");

            nameMapping.put("completion.size", "completionSize");
            nameMapping.put("cs", "completionSize");
            nameMapping.put("completionSize", "completionSize");

            nameMapping.put("fielddata.memory_size", "fielddataMemory");
            nameMapping.put("fm", "fielddataMemory");
            nameMapping.put("fielddataMemory", "fielddataMemory");

            nameMapping.put("fielddata.evictions", "fielddataEvictions");
            nameMapping.put("fe", "fielddataEvictions");
            nameMapping.put("fielddataEvictions", "fielddataEvictions");

            nameMapping.put("query_cache.memory_size", "queryCacheMemory");
            nameMapping.put("qcm", "queryCacheMemory");
            nameMapping.put("queryCacheMemory", "queryCacheMemory");

            nameMapping.put("query_cache.evictions", "queryCacheEvictions");
            nameMapping.put("qce", "queryCacheEvictions");
            nameMapping.put("queryCacheEvictions", "queryCacheEvictions");

            nameMapping.put("query_cache.hit_count", "queryCacheHitCount");
            nameMapping.put("qchc", "queryCacheHitCount");
            nameMapping.put("queryCacheHitCount", "queryCacheHitCount");

            nameMapping.put("query_cache.miss_count", "queryCacheMissCount");
            nameMapping.put("qcmc", "queryCacheMissCount");
            nameMapping.put("queryCacheMissCount", "queryCacheMissCount");

            nameMapping.put("request_cache.memory_size", "requestCacheMemory");
            nameMapping.put("rcm", "requestCacheMemory");
            nameMapping.put("requestCacheMemory", "requestCacheMemory");

            nameMapping.put("request_cache.evictions", "requestCacheEvictions");
            nameMapping.put("rce", "requestCacheEvictions");
            nameMapping.put("requestCacheEvictions", "requestCacheEvictions");

            nameMapping.put("request_cache.hit_count", "requestCacheHitCount");
            nameMapping.put("rchc", "requestCacheHitCount");
            nameMapping.put("requestCacheHitCount", "requestCacheHitCount");

            nameMapping.put("request_cache.miss_count", "requestCacheMissCount");
            nameMapping.put("rcmc", "requestCacheMissCount");
            nameMapping.put("requestCacheMissCount", "requestCacheMissCount");

            nameMapping.put("flush.total", "flushTotal");
            nameMapping.put("ft", "flushTotal");
            nameMapping.put("flushTotal", "flushTotal");

            nameMapping.put("flush.total_time", "flushTotalTime");
            nameMapping.put("ftt", "flushTotalTime");
            nameMapping.put("flushTotalTime", "flushTotalTime");

            nameMapping.put("get.current", "getCurrent");
            nameMapping.put("gc", "getCurrent");
            nameMapping.put("getCurrent", "getCurrent");

            nameMapping.put("get.time", "getTime");
            nameMapping.put("gti", "getTime");
            nameMapping.put("getTime", "getTime");

            nameMapping.put("get.total", "getTotal");
            nameMapping.put("gto", "getTotal");
            nameMapping.put("getTotal", "getTotal");

            nameMapping.put("get.exists_time", "getExistsTime");
            nameMapping.put("geti", "getExistsTime");
            nameMapping.put("getExistsTime", "getExistsTime");

            nameMapping.put("get.exists_total", "getExistsTotal");
            nameMapping.put("geto", "getExistsTotal");
            nameMapping.put("getExistsTotal", "getExistsTotal");

            nameMapping.put("get.missing_time", "getMissingTime");
            nameMapping.put("gmti", "getMissingTime");
            nameMapping.put("getMissingTime", "getMissingTime");

            nameMapping.put("get.missing_total", "getMissingTotal");
            nameMapping.put("gmto", "getMissingTotal");
            nameMapping.put("getMissingTotal", "getMissingTotal");
        }

        public static String getStandardMetric(String metric) {
            return nameMapping.get(metric);
        }

        public static boolean isValidMetric(String metric) {
            return nameMapping.containsKey(metric);
        }
    }
}
