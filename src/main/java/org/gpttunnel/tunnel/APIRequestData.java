package org.gpttunnel.tunnel;

import org.gpttunnel.utils.JsonUtil;

public interface APIRequestData {

    default String toJson() {
        return JsonUtil.toJson(this);
    }
}