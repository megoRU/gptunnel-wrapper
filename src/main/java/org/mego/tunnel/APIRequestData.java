package org.mego.tunnel;

import org.mego.utils.JsonUtil;

public interface APIRequestData {

    default String toJson() {
        return JsonUtil.toJson(this);
    }
}