package com.jn.esmvc.service.request.action;

import org.elasticsearch.action.ActionListener;

public class DelegatableActionListener<Response1, Response2> implements ActionListener<Response1> {
    private ActionListener<Response2> delegate;
    private ResponseAdapter<Response1, Response2> responseAdapter;

    public DelegatableActionListener(ActionListener<Response2> delegate, ResponseAdapter<Response1, Response2> responseAdapter ){
        this.delegate = delegate;
        this.responseAdapter = responseAdapter;
    }

    public ActionListener<Response2> getDelegate() {
        return delegate;
    }

    @Override
    public void onResponse(Response1 response1) {
        Response2 response2 = responseAdapter.apply(response1);
        delegate.onResponse(response2);
    }

    @Override
    public void onFailure(Exception e) {
        delegate.onFailure(e);
    }
}
