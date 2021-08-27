package com.example.boredapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boredapp.R;
import com.example.boredapp.data.remote.models.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    ArrayList<Post> postData = new ArrayList<>();
    PostClickListener listener;

    public void setListener(PostClickListener listener) {
        this.listener = listener;
    }

    public void setPostData(ArrayList<Post> list){
        postData = list;
        notifyDataSetChanged();
    }

    public void deletePost(int position){
        postData.remove(position);
        notifyItemRemoved(position);
    }

    public Post getPostById(int position){
        return postData.get(position);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_view_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(postData.get(position));
    }

    @Override
    public int getItemCount() {
        return postData.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.post_title);
            description = itemView.findViewById(R.id.post_desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onLongCLick(getAdapterPosition());
                    return true;
                }
            });
        }

        public void bind(Post post){
            this.title.setText(post.getTitle());
            this.description.setText(post.getContent());
        }

    }

}

