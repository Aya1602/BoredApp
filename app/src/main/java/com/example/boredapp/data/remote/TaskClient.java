package com.example.boredapp.data.remote;

import android.util.Log;

import com.example.boredapp.data.remote.models.Post;
import com.example.boredapp.data.remote.models.Task;

import java.sql.Types;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskClient {

    public void getTask(String types,
                        Callback callback){
        RetrofitBuilder.getInstance().getTask(types)
                .enqueue(new retrofit2.Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        if (response.isSuccessful() && response.body() != null){
                            callback.success(response.body());
                        }else {
                            callback.failure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        callback.failure(t.getLocalizedMessage());
                    }
                });
    }

    public void getAllPosts(ListCallback callback){
        RetrofitBuilder.getInstance().getAllPost().enqueue(new retrofit2.Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                if (response.isSuccessful() && response.body() != null){
                    callback.success(response.body());
                }else {
                    callback.failure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                callback.failure(t.getLocalizedMessage());
            }
        });
    }

    public void deletePost(int id,PostCallback callback){
        RetrofitBuilder.getInstance().deleteData(id)
                .enqueue(new retrofit2.Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.isSuccessful() && response.body() != null){
                            callback.success(response.body());
                        }else {
                            callback.failure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        callback.failure(t.getLocalizedMessage());
                    }
                });
    }

    public void post(Post post,PostCallback callback){
        RetrofitBuilder.getInstance().createPost(post)
                .enqueue(new retrofit2.Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.isSuccessful() && response.body() != null){
                            callback.success(response.body());
                        }else {
                            callback.failure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        callback.failure(t.getLocalizedMessage());
                    }
                });
    }


    public void updatePost(int id,Post post,PostCallback callback){
        RetrofitBuilder.getInstance().updatePost(id,post)
                .enqueue(new retrofit2.Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.isSuccessful() && response.body() != null){
                            callback.success(response.body());
                        }else {
                            callback.failure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        callback.failure(t.getLocalizedMessage());
                    }
                });
    }

    public interface Callback{
        void success(Task task);
        default void failure(String msg) {
            Log.d("TAG", "failure: " + msg);
        }
    }

    public interface ListCallback{
        void success(ArrayList<Post> list);
        default void failure(String msg) {
            Log.d("TAG", "failure: " + msg);
        }
    }

    public interface PostCallback{
        void success(Post model);
        default void failure(String msg) {
            Log.d("TAG", "failure: " + msg);
        }
    }
}
