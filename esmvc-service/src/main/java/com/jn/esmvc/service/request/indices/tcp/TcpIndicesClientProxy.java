package com.jn.esmvc.service.request.indices.tcp;

import com.jn.esmvc.service.request.TcpClientWrapper;
import com.jn.esmvc.service.request.indices.IndicesClientProxy;

public class TcpIndicesClientProxy implements IndicesClientProxy {
    private TcpClientWrapper tcpClient;
    public TcpIndicesClientProxy(TcpClientWrapper tcpClient){
        this.tcpClient = tcpClient;
    }
}
