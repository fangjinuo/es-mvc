package com.jn.esmvc.service.request.indices.tcp;

import com.jn.esmvc.service.request.TcpClientWrapper;
import com.jn.esmvc.service.request.indices.IndicesClientWrapper;

public class TcpIndicesClientWrapper implements IndicesClientWrapper<TcpClientWrapper> {
    private TcpClientWrapper tcpClient;

    @Override
    public TcpClientWrapper getClientWrapper() {
        return tcpClient;
    }

    public TcpIndicesClientWrapper(TcpClientWrapper tcpClient){
        this.tcpClient = tcpClient;
    }
}
