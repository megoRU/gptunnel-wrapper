package org.gpttunnel.entity.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.gpttunnel.tunnel.APIRequestData;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssistantRequest implements APIRequestData {

    @JsonProperty("chatId")
    private String chatId;

    @JsonProperty("useWalletBalance")
    private boolean useWalletBalance;

    @JsonProperty("assistantCode")
    private String assistantCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("images")
    private List<String> images;
}