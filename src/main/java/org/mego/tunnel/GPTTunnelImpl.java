package org.mego.tunnel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.mego.entity.api.ChatRequest;
import org.mego.entity.api.AssistantRequest;
import org.mego.entity.api.request.APIRequest;
import org.mego.entity.api.request.ChatAssistantRequest;
import org.mego.entity.api.request.ChatCompletionRequest;
import org.mego.entity.api.response.ChatAssistant;
import org.mego.entity.api.response.ChatCompletion;
import org.mego.entity.exceptions.UnsuccessfulHttpException;
import org.mego.utils.JsonUtil;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class GPTTunnelImpl implements GPTTunnelAPI {


    private static final Logger LOGGER = Logger.getLogger(GPTTunnelImpl.class.getName());
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private final String token;

    protected GPTTunnelImpl(String token) {
        this.token = token;
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

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(apiRequest.getData().toJson());
        System.out.println(json);

        Request request = requestBuilder.build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                if (!response.isSuccessful())
                    throw new UnsuccessfulHttpException(response.code(), response.body().string());
                return JsonUtil.fromJson(responseBody, tClass);
            } else {
                String string = response.body().string();
                LOGGER.warning(string);
                throw new UnsuccessfulHttpException(response.code(), string);
            }
        }
    }
}