
package com.devildart.herokutestapp.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("criteria")
    @Expose
    private List<Criterium> criteria = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TestDatum() {
    }

    /**
     * 
     * @param color
     * @param criteria
     * @param name
     * @param id
     * @param tag
     */
    public TestDatum(Integer id, String name, String tag, String color, List<Criterium> criteria) {
        super();
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.color = color;
        this.criteria = criteria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Criterium> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criterium> criteria) {
        this.criteria = criteria;
    }

}
