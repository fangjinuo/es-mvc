package com.jn.esmvc.service.request.cat;

import com.jn.esmvc.service.request.cat.action.CatNodeAttrsResponse;
import com.jn.esmvc.service.request.cat.action.CatNodesRequest;
import com.jn.esmvc.service.request.cat.action.CatNodesResponse;
import com.jn.langx.annotation.Nullable;

import java.util.List;

public interface CatClientWrapper<WRAPPER,OPTIONS> {
    WRAPPER getClientWrapper();

    CatNodesResponse nodes(@Nullable OPTIONS options,@Nullable CatNodesRequest request);

    CatNodeAttrsResponse nodeattrs();
}
