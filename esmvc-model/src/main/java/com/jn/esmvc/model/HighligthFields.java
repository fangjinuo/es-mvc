package com.jn.esmvc.model;

import com.jn.langx.util.Preconditions;

import java.util.List;

public class HighligthFields {

    public static HighlightField newHighlightField(String name, List<String> fragments) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(fragments);
        HighlightField result = new HighlightField(name);
        result.addFragment(fragments);
        return result;
    }
}
