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

    private boolean fullId = false;

    public boolean isFullId() {
        return fullId;
    }

    public void setFullId(boolean fullId) {
        this.fullId = fullId;
    }

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
                if (CatNodesMetrics.isValidMetric(metric)) {
                    return CatNodesMetrics.getStandardMetric(metric);
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
}
