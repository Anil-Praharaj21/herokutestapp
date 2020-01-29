
package com.devildart.herokutestapp.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class $2 {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("values")
    @Expose
    private List<Integer> values = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public $2() {
    }

    /**
     * 
     * @param values
     * @param type
     */
    public $2(String type, List<Integer> values) {
        super();
        this.type = type;
        this.values = values;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

}
