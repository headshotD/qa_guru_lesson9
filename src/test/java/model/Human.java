package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Human {
private String title;
private List<Destiny> destiny;


    public List<Hobbies> getHobbies() {
        return hobbies;
    }

    private List<Hobbies> hobbies;


public String getTitle(){
    return title;
}
    public List<Destiny> getDestiny() {
        return destiny;
    }
}
