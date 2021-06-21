package com.jn.esmvc.service.tcp.request.indices;

import com.jn.esmvc.service.tcp.TcpClientWrapper;
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
