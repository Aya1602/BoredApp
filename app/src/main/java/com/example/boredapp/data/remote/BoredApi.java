package com.example.boredapp.data.remote;

import com.example.boredapp.data.remote.models.Post;
import com.example.boredapp.data.remote.models.Task;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BoredApi {

    @GET("/api/activity")
    Call<Task> getTask(
            @Query("type") String type
    );

    @POST("/posts")
    Call<Post> createPost(
            @Body Post post
    );

    @GET("posts/")
    Call<ArrayList<Post>> getAllPost();

    @DELETE("posts/{postId}")
    Call<Post> deleteData(@Path("postId")int postId);

    @PUT("posts/{id}")
    Call<Post> updatePost(@Path("id")int id,
                               @Body Post postModel);
}
