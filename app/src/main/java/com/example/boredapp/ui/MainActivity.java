package com.example.boredapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.boredapp.R;
import com.example.boredapp.data.remote.TaskClient;
import com.example.boredapp.data.remote.models.Post;
import com.example.boredapp.data.remote.models.Task;
import com.example.boredapp.databinding.ActivityMainBinding;
import com.example.boredapp.ui.adapters.PostAdapter;
import com.example.boredapp.ui.adapters.PostClickListener;
import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Double price;
    private String type;
    private TaskClient client;
    private PostAdapter adapter;
    public static final int POST = 1;
    public static final int UPDATE = 2;
    public static final String POST_KEY = "post1";
    public static final String UPDATE_KEY = "update2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        client = new TaskClient();

        initSpinner();
        initSlider();
        setListeners();
        initRecycler();
        getData();
    }

    private void initRecycler() {
        adapter = new PostAdapter();
        adapter.setListener(new PostClickListener() {
            @Override
            public void onLongCLick(int position) {
                Post model = adapter.getPostById(position);
                Integer id = model.getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("удалить запись " + model.getTitle() + "?");
                builder.setNegativeButton("отмена", null);
                builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        client.deletePost(id, new TaskClient.PostCallback() {
                            @Override
                            public void success(Post model) {
                                Log.e("delete", "success: ");
                                adapter.deletePost(position);
                            }
                        });
                    }
                });
                builder.show();
            }

            @Override
            public void onClick(int position) {
                Post post = adapter.getPostById(position);
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                intent.putExtra(POST_KEY, UPDATE);
                intent.putExtra(UPDATE_KEY, post.getId());
                intent.putExtra("title", post.getTitle());
                intent.putExtra("content", post.getContent());
                intent.putExtra("group", post.getGroup());
                intent.putExtra("id", post.getId());
                startActivity(intent);
            }
        });
        binding.postsRecycler.setAdapter(adapter);
    }

    private void getData() {
        client.getAllPosts((TaskClient.ListCallback) list -> adapter.setPostData(list));
    }

    private void setListeners() {
        binding.nextBtn.setOnClickListener(view -> {
            client.getTask(binding.typeSpinner.getSelectedItem().toString(), task -> {
                binding.tvTask.setText(task.getTask());
            });
        });
        binding.createPotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                intent.putExtra(POST_KEY,POST);
                startActivity(intent);
            }
        });
    }

    private void initSlider() {
        binding.priceSlider.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull RangeSlider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull RangeSlider slider) {
                price = Double.valueOf(slider.getValues().get(0));
            }
        });
    }

    private void initSpinner() {
        String[] types = new String[]{
                "education",
                "recreational",
                "social",
                "diy",
                "charity",
                "cooking",
                "relaxation",
                "music",
                "busywork"};

        ArrayAdapter<String> typesAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                types
        );

        binding.typeSpinner.setAdapter(typesAdapter);
    }
}