package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Hobbies {
    public List<String> getHobbies() {
        return hobbies;
    }

    @JsonProperty("hobbies")
    List<String> hobbies;
}
