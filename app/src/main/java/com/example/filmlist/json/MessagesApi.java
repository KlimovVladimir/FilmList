package com.example.filmlist.json;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MessagesApi {
    @GET("films.json")
    Call<Films> messages();
}
