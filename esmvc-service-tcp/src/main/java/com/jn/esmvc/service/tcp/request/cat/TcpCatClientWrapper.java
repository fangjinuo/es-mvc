package com.jn.esmvc.service.tcp.request.cat;

import com.jn.esmvc.service.request.cat.CatClientWrapper;
import com.jn.esmvc.service.request.cat.action.CatNodeAttrsRequest;
import com.jn.esmvc.service.request.cat.action.CatNodeAttrsResponse;
import com.jn.esmvc.service.request.cat.action.CatNodesRequest;
import com.jn.esmvc.service.request.cat.action.CatNodesResponse;
import com.jn.esmvc.service.tcp.TcpClientWrapper;

public class TcpCatClientWrapper implements CatClientWrapper<TcpClientWrapper,Object> {
    private TcpClientWrapper clientWrapper;

    public TcpCatClientWrapper(TcpClientWrapper clientWrapper){
        this.clientWrapper = clientWrapper;
    }

    @Override
    public TcpClientWrapper getClientWrapper() {
        return clientWrapper;
    }

    @Override
    public CatNodesResponse nodes(Object o, CatNodesRequest request) {
        return null;
    }

    @Override
    public CatNodeAttrsResponse nodeattrs(Object o, CatNodeAttrsRequest request) {
        return null;
    }
}
