package com.n26.test.mvitolo.resources.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

/**
 * Created by Marco on 09/01/2018.
 */
@JsonDeserialize(builder = AutoValue_StatisticRepresentation.Builder.class)
@AutoValue
public abstract class StatisticRepresentation {

    @JsonProperty("sum")
    public abstract Double sum();

    @JsonProperty("avg")
    public abstract Double avg();

    @JsonProperty("max")
    public abstract Double max();

    @JsonProperty("min")
    public abstract Double min();

    @JsonProperty("count")
    public abstract Long count();

    public static Builder builder() {
        return new AutoValue_StatisticRepresentation.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        @JsonProperty("sum")
        public abstract Builder sum(Double sum);

        @JsonProperty("avg")
        public abstract Builder avg(Double avg);

        @JsonProperty("max")
        public abstract Builder max(Double max);

        @JsonProperty("min")
        public abstract Builder min(Double min);

        @JsonProperty("count")
        public abstract Builder count(Long count);

        public abstract StatisticRepresentation build();
    }
}
