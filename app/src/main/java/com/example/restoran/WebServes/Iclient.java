package com.example.restoran.WebServes;

import com.example.restoran.Model.Comment;
import com.example.restoran.Model.Restaurant;
import com.example.restoran.Model.RsaultComment;
import com.example.restoran.Model.RsaultUser;
import com.example.restoran.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Iclient {
    @GET("showRestaurant")
    Call<List<Restaurant>> getRestaurants();

    @POST("addUser")
    Call<RsaultUser> addUser(@Body User user);

    @GET("loginUser/{username},{password}")
    Call<RsaultUser> loginUser(@Path("username") String username,@Path("password") String password);

    @GET("showComment/{id}")
    Call<List<Comment>> getComments(@Path("id")int id);

    @POST("addComment")
    Call<RsaultComment> addComment(@Body Comment comment);
}
