package org.gpttunnel.entity.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @Builder.Default
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("maxContext")
    private int maxContext = 8;

    @JsonProperty("message")
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("images")
    private List<String> images;
}