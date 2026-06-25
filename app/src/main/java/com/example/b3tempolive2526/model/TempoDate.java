package com.example.b3tempolive2526.model;

import com.example.b3tempolive2526.TempoColor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class TempoDate {

    @SerializedName("dateApplication")
    @Expose
    public String dateApplication;
    @SerializedName("statut")
    @Expose
    public TempoColor statut;

}
