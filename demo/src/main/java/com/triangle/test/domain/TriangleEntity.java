package com.triangle.test.domain;

import com.triangle.test.domain.enums.TriangleState;

import javax.persistence.*;

@Entity
@Table(name="TRIANGLES")
public class TriangleEntity extends AbstractEntity{

    @Id
    @GeneratedValue
    @Column(name="ID", precision=22)
    private long Id;

    @Column(name="EDGE_ONE")
    private double edgeOne;

    @Column(name="EDGE_TWO")
    private double edgeTwo;

    @Column(name="HYPOTENUSE")
    private double hypotenuse;

    @Column(name="STATE")
    @Enumerated(EnumType.ORDINAL)
    private TriangleState state;


    public TriangleEntity(double edgeOne, double edgeTwo, double hypotenuse, TriangleState state) {
        this.edgeOne = edgeOne;
        this.edgeTwo = edgeTwo;
        this.hypotenuse = hypotenuse;
        this.state = state;
    }

    public TriangleEntity() {
    }

    public double getEdgeOne() {
        return edgeOne;
    }

    public void setEdgeOne(double edgeOne) {
        this.edgeOne = edgeOne;
    }

    public double getEdgeTwo() {
        return edgeTwo;
    }

    public void setEdgeTwo(double edgeTwo) {
        this.edgeTwo = edgeTwo;
    }

    public double getHypotenuse() {
        return hypotenuse;
    }

    public void setHypotenuse(double hypotenuse) {
        this.hypotenuse = hypotenuse;
    }

    public TriangleState getState() {  return state;    }

    public void setState(TriangleState state) {   this.state = state;    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TriangleEntity{");
        sb.append("Id='").append(Id).append('\'');
        sb.append(", edgeOne=").append(edgeOne);
        sb.append(", edgeTwo=").append(edgeTwo);
        sb.append(", hypotenuse=").append(hypotenuse);
        sb.append('}');
        return sb.toString();
    }
}
