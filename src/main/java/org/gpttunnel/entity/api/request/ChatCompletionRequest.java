package org.gpttunnel.entity.api.request;

import org.gpttunnel.entity.api.ChatRequest;

public class ChatCompletionRequest extends APIRequest {

    public ChatCompletionRequest(ChatRequest chatRequest) {
        super("https://gptunnel.ru/v1/chat/completions", RequestMethod.POST, chatRequest);
    }
}