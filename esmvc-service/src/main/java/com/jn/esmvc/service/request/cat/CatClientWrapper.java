package com.jn.esmvc.service.request.cat;

import com.jn.esmvc.service.request.cat.action.CatNodeAttrsResponse;
import com.jn.esmvc.service.request.cat.action.CatNodesRequest;
import com.jn.esmvc.service.request.cat.action.CatNodesResponse;

import java.util.List;

public interface CatClientWrapper<WRAPPER,OPTIONS> {
    WRAPPER getClientWrapper();

    CatNodesResponse nodes(OPTIONS options, CatNodesRequest request);

    CatNodeAttrsResponse nodeattrs();
}
