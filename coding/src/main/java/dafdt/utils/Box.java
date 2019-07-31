package dafdt.utils;


import dafdt.models.Rule;

import java.util.ArrayList;

public class Box{

    double[] box;
    public Rule rule;
    public ArrayList<Box> overlaps;
    public ArrayList<Box> disagrementoverlaps;
    double value;

    public Box(double[] box, double leafs, Rule rule) {
        this.box = box;
        overlaps = new ArrayList<Box>();
        disagrementoverlaps = new ArrayList<Box>();
        this.value = leafs;
        this.rule = rule;
    }

    public boolean overlaps(Box box) {
        boolean overlaps = false;

        if(overlapping2D(this.box, box.box)) overlaps = true;

        return overlaps;
    }

    boolean overlapping1D(double minA, double maxA, double minB, double maxB) {
        boolean overlaps = false;
        if((maxA >= minB) && (maxB >= minA)) overlaps = true;
        return overlaps;
    }

    public boolean overlapping2D(double[] box1, double[] box2) {
        boolean overlaps = false;
        if(overlapping1D(box1[0], box1[1], box2[0], box2[1]) && overlapping1D(box1[2], box1[3], box2[2], box2[3]) ) overlaps = true;
        return overlaps;
    }

}