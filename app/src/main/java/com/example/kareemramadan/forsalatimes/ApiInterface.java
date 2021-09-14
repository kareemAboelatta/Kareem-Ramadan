package com.example.kareemramadan.forsalatimes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("{date}?address=address")
    public Call<Post> getPost(@Path("date") String date, @Query("address") String address);

}
//   https://api.aladhan.com/timingsByAddress/01-05-2021?address=Cairo
//   @Query("address") String address