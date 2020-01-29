
package com.devildart.herokutestapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class $4 {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("study_type")
    @Expose
    private String studyType;
    @SerializedName("parameter_name")
    @Expose
    private String parameterName;
    @SerializedName("min_value")
    @Expose
    private Integer minValue;
    @SerializedName("max_value")
    @Expose
    private Integer maxValue;
    @SerializedName("default_value")
    @Expose
    private Integer defaultValue;

    /**
     * No args constructor for use in serialization
     * 
     */
    public $4() {
    }

    /**
     * 
     * @param minValue
     * @param studyType
     * @param maxValue
     * @param defaultValue
     * @param parameterName
     * @param type
     */
    public $4(String type, String studyType, String parameterName, Integer minValue, Integer maxValue, Integer defaultValue) {
        super();
        this.type = type;
        this.studyType = studyType;
        this.parameterName = parameterName;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.defaultValue = defaultValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Integer defaultValue) {
        this.defaultValue = defaultValue;
    }

}
