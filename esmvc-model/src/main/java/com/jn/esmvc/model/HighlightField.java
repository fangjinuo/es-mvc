package com.jn.esmvc.model;

import com.jn.langx.util.Strings;
import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.function.Consumer;

import java.util.List;

public class HighlightField {
    private String name;
    private List<String> fragments = Collects.emptyArrayList();

    public HighlightField() {
    }

    public HighlightField(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFragments() {
        return fragments;
    }

    public void setFragments(List<String> fragments) {
        this.fragments = fragments;
    }

    public void addFragment(String fragment) {
        if (!Strings.isBlank(fragment)) {
            fragments.add(fragment);
        }
    }

    public void addFragment(List<String> fragments) {
        Collects.forEach(fragments, new Consumer<String>() {
            @Override
            public void accept(String fragment) {
                addFragment(fragment);
            }
        });
    }
}
