package com.triangle.test.model;

import com.fasterxml.jackson.annotation.*;
import com.triangle.test.domain.enums.TriangleState;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Triangle implements Serializable{

    private final static long serialVersionUID = 1L;

    private double edgeOne;
    private double edgeTwo;

    @JsonIgnore
    private double hypotenuse = 0;

    @JsonIgnore
    private TriangleState state = TriangleState.PRE_CALC;



    @JsonProperty("edgeOne")
    public double getEdgeOne() {
        return edgeOne;
    }

    @JsonAnySetter
    public void setEdgeOne(double edgeOne) {
        this.edgeOne = edgeOne;
    }


    @JsonProperty("edgeTwo")
    public double getEdgeTwo() {
        return edgeTwo;
    }

    @JsonAnySetter
    public void setEdgeTwo(double edgeTwo) {
        this.edgeTwo = edgeTwo;
    }

    public double getHypotenuse() {
        return hypotenuse;
    }

    public void setHypotenuse(double hypotenuse) {
        this.hypotenuse = hypotenuse;
    }

    public TriangleState getState() {
        return state;
    }

    public void setState(TriangleState state) {
        this.state = state;
    }


    @JsonCreator
    public Triangle(@JsonProperty(value = "edgeOne", required = true)double edgeOne, @JsonProperty(value = "edgeTwo", required = true)double edgeTwo) {
        this.edgeOne = edgeOne;
        this.edgeTwo = edgeTwo;
    }

    public Triangle() {
    }


    public Triangle(double edgeOne, double edgeTwo, double hypotenuse, TriangleState state) {
        this.edgeOne = edgeOne;
        this.edgeTwo = edgeTwo;
        this.hypotenuse = hypotenuse;
        this.state = state;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Triangle{");
        sb.append("edgeOne=").append(edgeOne);
        sb.append(", edgeTwo=").append(edgeTwo);
        sb.append(", hypotenuse=").append(hypotenuse);
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}
