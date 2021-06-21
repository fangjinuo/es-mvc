package com.jn.esmvc.service.request.cat.action;

import com.jn.langx.annotation.Nullable;
import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.collection.Pipeline;
import com.jn.langx.util.function.Function;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.tasks.Task;
import org.elasticsearch.tasks.TaskId;

import java.util.List;
import java.util.Map;

public class CatNodeAttrsRequest extends ActionRequest {

    @Override
    public String getDescription() {
        return "GET /_cat/nodeattrs";
    }

    private static final List<String> DEFAULT_METRIC = Collects.newArrayList("id", "name", "host", "ip", "port", "pid", "attr", "value");
    private String format = "json";
    @Nullable
    private List<String> metrics = DEFAULT_METRIC;


    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<String> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<String> metrics) {
        this.metrics = Pipeline.of(metrics)
                .addAll(DEFAULT_METRIC)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String metric) {
                        if (CatNodesMetrics.isValidMetric(metric)) {
                            return CatNodesMetrics.getStandardMetric(metric);
                        }
                        return null;
                    }
                }).clearNulls()
                .distinct()
                .asList();
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

}
