package org.mego.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mego.entity.api.ChatRequest;
import org.mego.entity.api.AssistantRequest;
import org.mego.entity.api.response.ChatAssistant;
import org.mego.entity.api.response.ChatCompletion;
import org.mego.entity.exceptions.UnsuccessfulHttpException;

import java.io.IOException;
import java.util.Objects;

public interface GPTTunnelAPI {

    /**
     * @param assistantRequest data
     * @return {@link ChatAssistant} data
     */
    ChatAssistant chatAssistant(@NotNull AssistantRequest assistantRequest) throws UnsuccessfulHttpException, IOException;

    /**
     * @param chatRequest data
     * @return {@link ChatRequest} data
     */
    ChatCompletion chatCompletion(@NotNull ChatRequest chatRequest) throws UnsuccessfulHttpException, IOException;

    class Builder {

        // Required
        private String token;

        /**
         * Password
         */
        @Contract("null -> fail")
        public Builder setToken(String token) {
            Objects.requireNonNull(token);
            this.token = token;
            return this;
        }

        /**
         * @throws IllegalArgumentException if some fields null
         */
        public GPTTunnelAPI build() {
            if (token == null)
                throw new IllegalArgumentException("token cannot be null!");
            else
                return new GPTTunnelImpl(token);
        }
    }
}