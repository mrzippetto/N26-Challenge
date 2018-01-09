package com.n26.test.mvitolo.resources.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

/**
 * Created by Marco on 07/01/2018.
 */
@JsonDeserialize(builder = AutoValue_TransactionData.Builder.class)
@AutoValue
public abstract class TransactionData {

    @JsonProperty("amount")
    public abstract Double amount();

    @JsonProperty("timestamp")
    public abstract Long timestamp();

    public static Builder builder() {
        return new AutoValue_TransactionData.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("amount")
        public abstract Builder amount(Double amount);

        @JsonProperty("timestamp")
        public abstract Builder timestamp(Long timestamp);

        public abstract TransactionData build();
    }
}
