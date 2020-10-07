package com.assessment.hsbc.exchangeratesmicroservice.clients;



import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "base",
    "date",
    "gbp",
    "usd",
    "hkd"
})
public class ExchangeRates {

    @JsonProperty("base")
    private String base;
    @JsonProperty("date")
    private String date;
    @JsonProperty("gbp")
    private Double gbp;
    @JsonProperty("usd")
    private Double usd;
    @JsonProperty("hkd")
    private Double hkd;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    @JsonProperty("base")
    public void setBase(String base) {
        this.base = base;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("gbp")
    public Double getGbp() {
        return gbp;
    }

    @JsonProperty("gbp")
    public void setGbp(Double gbp) {
        this.gbp = gbp;
    }

    @JsonProperty("usd")
    public Double getUsd() {
        return usd;
    }

    @JsonProperty("usd")
    public void setUsd(Double usd) {
        this.usd = usd;
    }

    @JsonProperty("hkd")
    public Double getHkd() {
        return hkd;
    }

    @JsonProperty("hkd")
    public void setHkd(Double hkd) {
        this.hkd = hkd;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
