package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Destiny {
    private String name;
    private Integer age;
    private String from;
}
