package com.jn.esmvc.service.request.indices.tcp;

import com.jn.esmvc.service.request.TcpClientProxy;
import com.jn.esmvc.service.request.indices.IndicesClientProxy;

public class TcpIndicesClientProxy implements IndicesClientProxy {
    private TcpClientProxy tcpClient;
    public TcpIndicesClientProxy(TcpClientProxy tcpClient){
        this.tcpClient = tcpClient;
    }
}
