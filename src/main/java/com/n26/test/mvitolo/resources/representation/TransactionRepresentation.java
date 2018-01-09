package com.n26.test.mvitolo.resources.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

/**
 * Created by Marco on 08/01/2018.
 */
@JsonDeserialize(builder = AutoValue_TransactionRepresentation.Builder.class)
@AutoValue
public abstract class TransactionRepresentation {

    public static Builder builder() {
        return new AutoValue_TransactionRepresentation.Builder();
    }

    @JsonProperty("amount")
    public abstract Double amount();

    @JsonProperty("timestamp")
    public abstract Long timestamp();

    @AutoValue.Builder
    public abstract static class Builder {

        @JsonProperty("amount")
        public abstract Builder amount(Double amount);

        @JsonProperty("timestamp")
        public abstract Builder timestamp(Long timestamp);

        public abstract TransactionRepresentation build();
    }
}
