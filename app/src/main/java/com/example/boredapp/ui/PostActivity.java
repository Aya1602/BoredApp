package com.example.boredapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boredapp.data.remote.TaskClient;
import com.example.boredapp.data.remote.models.Post;
import com.example.boredapp.databinding.ActivityPostBinding;

import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private static final int MY_ID = 6;
    private TaskClient client;
    ActivityPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        client = new TaskClient();
        int intentID = getIntent().getIntExtra(MainActivity.POST_KEY, 0);
        int position = getIntent().getIntExtra(MainActivity.UPDATE_KEY, 0);
        String titleS = getIntent().getStringExtra("title");
        String contentS = getIntent().getStringExtra("content");
        int groupS = getIntent().getIntExtra("group", 0);
        int idS = getIntent().getIntExtra("id", 0);
        if (intentID == MainActivity.UPDATE) {
            binding.buttonSend.setText("update");
            binding.title.setText(titleS);
            binding.content.setText(contentS);
            binding.user.setText(String.valueOf(idS));
            binding.group.setText(String.valueOf(groupS));
        }
        binding.buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intentID == MainActivity.UPDATE) {
                    Post postModel = new Post(binding.title.getText().toString(),
                            binding.content.getText().toString(),
                            MY_ID,
                            Integer.parseInt(binding.group.getText().toString()));
                    client.updatePost(position, postModel, new TaskClient.PostCallback() {
                        @Override
                        public void success(Post model) {
                            startActivity(new Intent(PostActivity.this, MainActivity.class));
                        }
                    });
                } else if (intentID == MainActivity.POST) {
                    Post postModel = new Post(binding.title.getText().toString(),
                            binding.content.getText().toString(),
                            MY_ID,
                            Integer.parseInt(binding.group.getText().toString()));
                    client.post(postModel, new TaskClient.PostCallback() {
                        @Override
                        public void success(Post model) {
                            startActivity(new Intent(PostActivity.this, MainActivity.class));
                        }
                    });
                }

            }
        });
    }
}