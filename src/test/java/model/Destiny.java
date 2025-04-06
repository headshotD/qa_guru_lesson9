package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Destiny {
    private String name;
    @JsonProperty("AGE")
    private Integer AGE;
    private String from;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return AGE;
    }

    public String getFrom() {
        return from;
    }

}
