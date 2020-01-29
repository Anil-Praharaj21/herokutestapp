
package com.devildart.herokutestapp.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class $3 {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("values")
    @Expose
    private List<Float> values = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public $3() {
    }

    /**
     * 
     * @param values
     * @param type
     */
    public $3(String type, List<Float> values) {
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

    public List<Float> getValues() {
        return values;
    }

    public void setValues(List<Float> values) {
        this.values = values;
    }

}
