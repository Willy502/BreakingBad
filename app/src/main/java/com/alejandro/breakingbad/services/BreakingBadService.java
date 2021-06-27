package com.alejandro.breakingbad.services;

import com.alejandro.breakingbad.model.BBCharacter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BreakingBadService {

    @GET("api/characters")
    Call<List<BBCharacter>> getCharacters(@Query("limit") int limit, @Query("offset") int offset);

}
