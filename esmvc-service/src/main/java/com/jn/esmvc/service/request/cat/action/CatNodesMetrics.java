package com.jn.esmvc.service.request.cat.action;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://www.elastic.co/guide/en/elasticsearch/reference/current/cat-nodes.html
 */
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

        nameMapping.put("indexing.delete_current", "indexingDeleteCurrent");
        nameMapping.put("idc", "indexingDeleteCurrent");

        nameMapping.put("indexing.delete_time", "indexingDeleteTime");
        nameMapping.put("idti", "indexingDeleteTime");

        nameMapping.put("indexing.delete_total", "indexingDeleteTotal");
        nameMapping.put("idto", "indexingDeleteTotal");

        nameMapping.put("indexing.index_current", "indexingIndexCurrent");
        nameMapping.put("iic", "indexingIndexCurrent");

        nameMapping.put("indexing.index_time", "indexingIndexTime");
        nameMapping.put("iiti", "indexingIndexTime");

        nameMapping.put("indexing.index_total", "indexingIndexTotal");
        nameMapping.put("iito", "indexingIndexTotal");

        nameMapping.put("indexing.index_failed", "indexingIndexFailed");
        nameMapping.put("iif", "indexingIndexFailed");

        nameMapping.put("merges.current", "mergesCurrent");
        nameMapping.put("mc", "mergesCurrent");

        nameMapping.put("merges.current_docs", "mergesCurrentDocs");
        nameMapping.put("mcd", "mergesCurrentDocs");

        nameMapping.put("merges.current_size", "mergesCurrentSize");
        nameMapping.put("mcs", "mergesCurrentSize");

        nameMapping.put("merges.total", "mergesTotal");
        nameMapping.put("mt", "mergesTotal");

        nameMapping.put("merges.total_docs", "mergesTotalDocs");
        nameMapping.put("mtd", "mergesTotalDocs");

        nameMapping.put("merges.total_size", "mergesTotalSize");
        nameMapping.put("mts", "mergesTotalSize");

        nameMapping.put("merges.total_time", "mergesTotalTime");
        nameMapping.put("mtt", "mergesTotalTime");

        nameMapping.put("refresh.total", "refreshTotal");
        nameMapping.put("rto", "refreshTotal");

        nameMapping.put("refresh.time", "refreshTime");
        nameMapping.put("rti", "refreshTime");

        nameMapping.put("script.compilations", "scriptCompilations");
        nameMapping.put("scrcc", "scriptCompilations");

        nameMapping.put("script.cache_evictions", "scriptCacheEvictions");
        nameMapping.put("scrce", "scriptCacheEvictions");

        nameMapping.put("search.fetch_current", "searchFetchCurrent");
        nameMapping.put("sfc", "searchFetchCurrent");

        nameMapping.put("search.fetch_time", "searchFetchTime");
        nameMapping.put("sfti", "searchFetchTime");

        nameMapping.put("search.fetch_total", "searchFetchTotal");
        nameMapping.put("sfto", "searchFetchTotal");

        nameMapping.put("search.open_contexts", "searchOpenContexts");
        nameMapping.put("so", "searchOpenContexts");

        nameMapping.put("search.query_current", "searchQueryCurrent");
        nameMapping.put("sqc", "searchQueryCurrent");

        nameMapping.put("search.query_time", "searchQueryTime");
        nameMapping.put("sqti", "searchQueryTime");

        nameMapping.put("search.query_total", "searchQueryTotal");
        nameMapping.put("sqto", "searchQueryTotal");

        nameMapping.put("search.scroll_current", "searchScrollCurrent");
        nameMapping.put("scc", "searchScrollCurrent");

        nameMapping.put("search.scroll_time", "searchScrollTime");
        nameMapping.put("scti", "searchScrollTime");

        nameMapping.put("search.scroll_total", "searchScrollTotal");
        nameMapping.put("scto", "searchScrollTotal");

        nameMapping.put("segments.count", "segmentsCount");
        nameMapping.put("sc", "");

        nameMapping.put("segments.memory", "segmentsMemory");
        nameMapping.put("sm", "segmentsMemory");

        nameMapping.put("segments.index_writer_memory", "segmentsIndexWriterMemory");
        nameMapping.put("siwm", "segmentsIndexWriterMemory");

        nameMapping.put("segments.version_map_memory", "segmentsVersionMapMemory");
        nameMapping.put("svmm", "segmentsVersionMapMemory");

        nameMapping.put("segments.fixed_bitset_memory", "fixedBitsetMemory");
        nameMapping.put("sfbm", "fixedBitsetMemory");

        nameMapping.put("suggest.current", "suggestCurrent");
        nameMapping.put("suc", "suggestCurrent");

        nameMapping.put("suggest.time", "suggestTime");
        nameMapping.put("suti", "suggestTime");

        nameMapping.put("suggest.total", "suggestTotal");
        nameMapping.put("suto", "suggestTotal");
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
