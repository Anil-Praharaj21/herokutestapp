
package com.devildart.herokutestapp.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class $1 {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("values")
    @Expose
    private List<Integer> values = null;
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
    public $1() {
    }

    /**
     * 
     * @param minValue
     * @param studyType
     * @param maxValue
     * @param defaultValue
     * @param values
     * @param parameterName
     * @param type
     */
    public $1(String type, List<Integer> values, String studyType, String parameterName, Integer minValue, Integer maxValue, Integer defaultValue) {
        super();
        this.type = type;
        this.values = values;
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

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
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
