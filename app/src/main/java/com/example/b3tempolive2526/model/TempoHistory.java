package com.example.b3tempolive2526.model;

import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class TempoHistory {

    @SerializedName("errors")
    @Expose
    public List<Object> errors;
    @SerializedName("content")
    @Expose
    public Content content;

    @Generated("jsonschema2pojo")
    public class Content {

        @SerializedName("dateApplicationBorneInf")
        @Expose
        public String dateApplicationBorneInf;
        @SerializedName("dateApplicationBorneSup")
        @Expose
        public String dateApplicationBorneSup;
        @SerializedName("dateHeureTraitementActivET")
        @Expose
        public String dateHeureTraitementActivET;
        @SerializedName("options")
        @Expose
        public List<Option> options;

    }

    @Generated("jsonschema2pojo")
    public class Option {

        @SerializedName("option")
        @Expose
        public String option;
        @SerializedName("calendrier")
        @Expose
        public List<TempoDate> calendrier;

    }

}