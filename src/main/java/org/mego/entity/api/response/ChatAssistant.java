package org.mego.entity.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mego.tunnel.APIObject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatAssistant implements APIObject {

    @JsonProperty("id")
    private String id;

    @JsonProperty("chatId")
    private String chatId;

    @JsonProperty("assistantId")
    private String assistantId;

    @JsonProperty("assistantCode")
    private String assistantCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("spendTokenCount")
    private int spendTokenCount;

    @JsonProperty("historyMessageCount")
    private int historyMessageCount;
}