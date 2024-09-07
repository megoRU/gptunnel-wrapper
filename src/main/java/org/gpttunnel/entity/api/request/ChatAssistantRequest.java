package org.gpttunnel.entity.api.request;

import org.gpttunnel.entity.api.AssistantRequest;

public class ChatAssistantRequest extends APIRequest {

    public ChatAssistantRequest(AssistantRequest assistantRequest) {
        super("https://gptunnel.ru/v1/assistant/chat", RequestMethod.POST, assistantRequest);
    }
}