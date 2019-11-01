/*
 *  Copyright (c) 2016
 *  BES Software Corporation.
 *  All Rights Reserved
 *      Revision History:
 *                                 Modification       Tracking
 *  Author (Email ID)              Date               Number               Description
 *  -------------------------------------------------------------------------------------------
 *  jinuo.fang                     2019-04-18                              Initial version
 *
 */

package com.jn.esmvc.model;

import com.jn.langx.util.Preconditions;

import java.util.List;

public class HighligthFields {

    public static HighlightField newHighlightField(String name, List<String> fragments) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(fragments);
        HighlightField result = new HighlightField(name);
        result.addFragment(fragments);
        return result;
    }
}
