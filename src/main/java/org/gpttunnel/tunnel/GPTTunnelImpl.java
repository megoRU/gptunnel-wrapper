package org.gpttunnel.tunnel;

import okhttp3.*;
import org.gpttunnel.entity.api.AssistantRequest;
import org.gpttunnel.entity.api.ChatRequest;
import org.gpttunnel.entity.api.request.APIRequest;
import org.gpttunnel.entity.api.request.ChatAssistantRequest;
import org.gpttunnel.entity.api.request.ChatCompletionRequest;
import org.gpttunnel.entity.api.response.ChatAssistant;
import org.gpttunnel.entity.api.response.ChatCompletion;
import org.gpttunnel.entity.exceptions.UnsuccessfulHttpException;
import org.gpttunnel.utils.JsonUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GPTTunnelImpl implements GPTTunnelAPI {

    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();

    private static final Logger LOGGER = Logger.getLogger(GPTTunnelImpl.class.getName());
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private final String token;
    private final boolean isDevMode;

    protected GPTTunnelImpl(@NotNull String token, boolean isDevMode) {
        this.token = token;
        this.isDevMode = isDevMode;
    }

    @Override
    public ChatAssistant chatAssistant(@NotNull AssistantRequest assistantRequest) throws UnsuccessfulHttpException, IOException {
        return parseResponse(ChatAssistant.class, new ChatAssistantRequest(assistantRequest));
    }

    @Override
    public ChatCompletion chatCompletion(@NotNull ChatRequest chatRequest) throws UnsuccessfulHttpException, IOException {
        return parseResponse(ChatCompletion.class, new ChatCompletionRequest(chatRequest));
    }

    private <T extends APIObject> T parseResponse(Class<T> tClass, @NotNull APIRequest apiRequest) throws IOException, UnsuccessfulHttpException {
        Request.Builder requestBuilder = new Request.Builder()
                .url(apiRequest.getUrl())
                .addHeader("Authorization", token)
                .addHeader("Content-Type", "application/json");

        if (apiRequest.getRequestMethod() == APIRequest.RequestMethod.GET) {
            requestBuilder = requestBuilder.get();
        } else if (apiRequest.getRequestMethod() == APIRequest.RequestMethod.POST) {
            if (apiRequest.getData() != null) {
                requestBuilder.post(RequestBody.create(apiRequest.getData().toJson(), MEDIA_TYPE_JSON));
            } else {
                requestBuilder.post(RequestBody.create("{}", MEDIA_TYPE_JSON));
            }
        }

        if (isDevMode) LOGGER.info(apiRequest.getData().toJson());

        Request request = requestBuilder.build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                return JsonUtil.fromJson(responseBody, tClass);
            } else {
                String string = response.body().string();
                LOGGER.warning(string);
                throw new UnsuccessfulHttpException(response.code(), string);
            }
        }
    }
}