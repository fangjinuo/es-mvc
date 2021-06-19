package com.jn.esmvc.service.scroll;

import com.jn.esmvc.model.AbstractESModel;
import com.jn.esmvc.service.ClientWrapper;
import org.elasticsearch.search.Scroll;

import java.util.LinkedList;
import java.util.List;

public class ScrollContext<MODEL extends AbstractESModel> {
    private String scrollId;
    private Scroll scroll;
    private List<MODEL> scrolledModels = new LinkedList<>();
    private ClientWrapper clientProxy;

    public ScrollContext() {
    }

    public ScrollContext(String scrollId) {
        this.scrollId = scrollId;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public Scroll getScroll() {
        return scroll;
    }

    public void setScroll(Scroll scroll) {
        this.scroll = scroll;
    }

    public List<MODEL> getScrolledModels() {
        return scrolledModels;
    }

    public void setScrolledModels(List<MODEL> scrolledModels) {
        this.scrolledModels = scrolledModels;
    }

    public ClientWrapper getClientProxy() {
        return clientProxy;
    }

    public void setClientProxy(ClientWrapper clientProxy) {
        this.clientProxy = clientProxy;
    }
}
