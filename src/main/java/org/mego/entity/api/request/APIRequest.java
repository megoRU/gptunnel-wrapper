package org.mego.entity.api.request;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.mego.tunnel.APIRequestData;

@Getter
public abstract class APIRequest {

    private final String url;
    private final RequestMethod requestMethod;
    private final APIRequestData data;

    protected APIRequest(@NotNull String url, @NotNull RequestMethod requestMethod, APIRequestData data) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.data = data;
    }

    public enum RequestMethod {
        GET,
        POST
    }
}