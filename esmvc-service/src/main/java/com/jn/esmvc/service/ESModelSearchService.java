
package com.jn.esmvc.service;

import com.jn.esmvc.model.AbstractESModel;
import com.jn.esmvc.service.scroll.ScrollContextCache;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;

public interface ESModelSearchService<MODEL extends AbstractESModel> extends IESModelService<MODEL> {

    void setScrollCache(ScrollContextCache scrollContextCache);
    void setScrollDuration(long durationInMills);

    /**
     * Search API: count
     *
     * @param bodyBuilder search body builder
     * @return matched results
     * @throws IOException throws it when network or tcp error
     */
    long count(SearchSourceBuilder bodyBuilder) throws IOException;

    /**
     * Search API: search search
     *
     * @param bodyBuilder search body builder
     * @return matched results
     * @throws IOException throws it when network or tcp error
     */
    List<MODEL> search(SearchSourceBuilder bodyBuilder) throws IOException;

    /**
     * Search API: multi search
     *
     * @param bodyBuilder search body builder
     * @return matched results
     * @throws IOException throws it when network or tcp error
     */
    List<MODEL> union(SearchSourceBuilder... bodyBuilder) throws IOException;

    /**
     * Query DSL: match_all
     *
     * @param bodyBuilder search body builder
     * @return matched results
     * @throws IOException throws it when network or tcp error
     */
    List<MODEL> all(SearchSourceBuilder bodyBuilder) throws IOException;

    /**
     * Query DSL: match_none
     *
     * @param bodyBuilder search body builder
     * @return matched results
     * @throws IOException throws it when network or tcp error
     */
    List<MODEL> none(SearchSourceBuilder bodyBuilder) throws IOException;

    /**
     * Clear scroll by scrollId
     * @param scrollId
     */
    void clearScroll(String scrollId);
}
