package com.jn.esmvc.model;

import java.io.Serializable;

public interface Entity<ID> extends Serializable{
    ID getId0();
    void setId0(ID id);
    Class<ID> getId0Type();
}
