package com.example.b3tempolive2526.model;

import java.util.List;
import javax.annotation.processing.Generated;

import com.example.b3tempolive2526.TempoColor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class TempoDaysLeft {

    @SerializedName("errors")
    @Expose
    public List< Object> errors;
    @SerializedName("content")
    @Expose
    public List<Content> content;

    @Generated("jsonschema2pojo")
    public class Content {

        @SerializedName("typeJourEff")
        @Expose
        public TempoColor typeJourEff;
        @SerializedName("libelle")
        @Expose
        public String libelle;
        @SerializedName("nombreJours")
        @Expose
        public Integer nombreJours;
        @SerializedName("premierJour")
        @Expose
        public String premierJour;
        @SerializedName("dernierJour")
        @Expose
        public String dernierJour;
        @SerializedName("premierJourExclu")
        @Expose
        public Object premierJourExclu;
        @SerializedName("dernierJourExclu")
        @Expose
        public Object dernierJourExclu;
        @SerializedName("nombreJoursTires")
        @Expose
        public Integer nombreJoursTires;
        @SerializedName("etat")
        @Expose
        public String etat;

    }

}