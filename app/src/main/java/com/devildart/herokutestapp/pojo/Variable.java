
package com.devildart.herokutestapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variable {

    @SerializedName("$1")
    @Expose
    private $1 $1;
    @SerializedName("$2")
    @Expose
    private $2 $2;
    @SerializedName("$3")
    @Expose
    private $3 $3;
    @SerializedName("$4")
    @Expose
    private $4 $4;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Variable() {
    }

    /**
     * 
     * @param $4
     * @param $1
     * @param $2
     * @param $3
     */
    public Variable($1 $1, $2 $2, $3 $3, $4 $4) {
        super();
        this.$1 = $1;
        this.$2 = $2;
        this.$3 = $3;
        this.$4 = $4;
    }

    public $1 get$1() {
        return $1;
    }

    public void set$1($1 $1) {
        this.$1 = $1;
    }

    public $2 get$2() {
        return $2;
    }

    public void set$2($2 $2) {
        this.$2 = $2;
    }

    public $3 get$3() {
        return $3;
    }

    public void set$3($3 $3) {
        this.$3 = $3;
    }

    public $4 get$4() {
        return $4;
    }

    public void set$4($4 $4) {
        this.$4 = $4;
    }

}
