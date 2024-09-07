package org.mego.entity.api.request;

import org.mego.entity.api.AssistantRequest;

public class ChatAssistantRequest extends APIRequest {

    public ChatAssistantRequest(AssistantRequest assistantRequest) {
        super("https://gptunnel.ru/v1/assistant/chat", RequestMethod.POST, assistantRequest);
    }
}