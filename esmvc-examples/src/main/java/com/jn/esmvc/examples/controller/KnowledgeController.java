package com.jn.esmvc.examples.controller;

import com.jn.esmvc.examples.model.KnowledgeESModel;
import com.jn.esmvc.examples.service.ESKnowledgeService;
import com.jn.esmvc.examples.service.rest.ESRestKnowledgeService;
import com.jn.esmvc.examples.service.tcp.ESTcpKnowledgeService;
import com.jn.esmvc.service.util.ESRequests;
import com.jn.langx.util.pagination.PagingResult;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/knowledges")
public class KnowledgeController {

    private ESRestKnowledgeService restKnowledgeService;
    private ESTcpKnowledgeService tcpKnowledgeService;

    @PostMapping
    public void add(@RequestParam(defaultValue = "true") boolean rest, @RequestBody KnowledgeESModel knowledge) throws IOException {
        getService(rest).add(knowledge);
    }

    private ESKnowledgeService getService(boolean rest) {
        return rest ? restKnowledgeService : tcpKnowledgeService;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@RequestParam(defaultValue = "true") boolean rest, @PathVariable String id) throws IOException {
        getService(rest).removeById(id);
    }

    @PatchMapping
    public void updateById(@RequestParam(defaultValue = "true") boolean rest, @RequestBody KnowledgeESModel knowledgeESModel) throws IOException {
        getService(rest).updateById(knowledgeESModel.get_id(), knowledgeESModel);
    }

    @GetMapping("/{id}")
    public KnowledgeESModel getById(@RequestParam(defaultValue = "true") boolean rest, @PathVariable String id) throws IOException {
        return getService(rest).getById(id);
    }

    @GetMapping
    public PagingResult<KnowledgeESModel> list(@RequestParam(defaultValue = "true") boolean rest) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = new MatchAllQueryBuilder();
        sourceBuilder.from(0).size(100).query(queryBuilder);
        getService(rest).search(sourceBuilder);
        return ESRequests.ES_PAGING.get().getResult();
    }

    public long total(@RequestParam(defaultValue = "true") boolean rest) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = new MatchAllQueryBuilder();
        sourceBuilder.query(queryBuilder);
        return getService(rest).count("_id", sourceBuilder);
    }

    @Autowired
    public void setRestKnowledgeService(ESRestKnowledgeService restKnowledgeService) {
        this.restKnowledgeService = restKnowledgeService;
    }

    @Autowired
    public void setTcpKnowledgeService(ESTcpKnowledgeService tcpKnowledgeService) {
        this.tcpKnowledgeService = tcpKnowledgeService;
    }
}
