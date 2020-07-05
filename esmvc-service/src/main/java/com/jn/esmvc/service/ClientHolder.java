package com.jn.esmvc.service;

import com.jn.langx.util.struct.Holder;

public class ClientHolder<CLIENT> extends Holder<CLIENT> {

    public ESRestClient asESRestClient(){
        return (ESRestClient)this;
    }

}
