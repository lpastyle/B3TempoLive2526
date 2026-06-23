package com.example.b3tempolive2526;

import com.example.b3tempolive2526.model.TempoDaysLeft;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IEdfApi {

    //https://api-commerce.edf.fr/commerce/activet/v1/saisons/search?option=TEMPO&dateReference=2025-06-26
    @GET("commerce/activet/v1/saisons/search")
    Call<TempoDaysLeft> getTempoDaysLeft(
            @Query("option") String option,
            @Query("dateReference") String dateReference
    );

    // https://api-commerce.edf.fr/commerce/activet/v1/calendrier-jours-effacement?option=TEMPO&dateApplicationBorneInf=2024-6-18&dateApplicationBorneSup=2025-6-18&identifiantConsommateur=src

}
