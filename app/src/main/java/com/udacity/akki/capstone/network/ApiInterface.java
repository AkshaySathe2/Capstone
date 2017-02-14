package com.udacity.akki.capstone.network;

import com.udacity.akki.capstone.HomePageParameters;
import com.udacity.akki.capstone.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 836158 on 13-02-2017.
 */

public interface ApiInterface {

    /*@GET("user/{id}/home")
    Call<MoviesResponse> getHomeData(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);*/

    @GET("Users/{uid}.json")
    Call<User> getUserData(@Path("uid") String uid,@Query("auth") String token);

}
