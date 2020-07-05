package com.jn.esmvc.examples.controller;

import com.jn.esmvc.examples.model.KnowledgeESModel;
import com.jn.esmvc.examples.service.rest.ESRestKnowledgeService;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/https/knowledges")
public class RestClientKnowledgeController {

    private ESRestKnowledgeService knowledgeService;

    @PostMapping
    public void add(@RequestBody KnowledgeESModel knowledge) throws IOException {
        knowledgeService.add(knowledge);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) throws IOException {
        knowledgeService.removeById(id);
    }

    @PatchMapping
    public void updateById(@RequestBody KnowledgeESModel knowledgeESModel) throws IOException {
        knowledgeService.updateById(knowledgeESModel.get_id(), knowledgeESModel);
    }

    @GetMapping("/{id}")
    public KnowledgeESModel getById(@PathVariable String id) throws IOException {
        return knowledgeService.getById(id);
    }

    @GetMapping
    public List<KnowledgeESModel> list() throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = new MatchAllQueryBuilder();
        sourceBuilder.from(0).size(100).query(queryBuilder);
        return knowledgeService.search(sourceBuilder);
    }

    public long total() throws IOException{
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = new MatchAllQueryBuilder();
        sourceBuilder.query(queryBuilder);
        return knowledgeService.count("_id", sourceBuilder);
    }

    @Autowired
    public void setKnowledgeService(ESRestKnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

}
