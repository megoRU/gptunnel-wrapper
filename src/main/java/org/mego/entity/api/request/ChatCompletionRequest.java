package org.mego.entity.api.request;

import org.mego.entity.api.ChatRequest;

public class ChatCompletionRequest extends APIRequest {

    public ChatCompletionRequest(ChatRequest chatRequest) {
        super("https://gptunnel.ru/v1/chat/completions", RequestMethod.POST, chatRequest);
    }
}