
package com.devildart.herokutestapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Criterium {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("variable")
    @Expose
    private Variable variable;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Criterium() {
    }

    /**
     * 
     * @param variable
     * @param text
     * @param type
     */
    public Criterium(String type, String text, Variable variable) {
        super();
        this.type = type;
        this.text = text;
        this.variable = variable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

}
