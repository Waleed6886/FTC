package com.example.ftc.ftc.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ftc.ftc.Model.Post;
import com.example.ftc.ftc.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nawif on 4/6/18.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    static List<Post> PostList;
    Context context;

    public PostAdapter(List<Post> PostList ,Context context){
       this.PostList=PostList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_recycleview_single_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Post post = PostList.get(position);
            holder.PostTitle.setText(post.getMetadata().getName());
            holder.PostDescription.setText(post.getDescription());
        Picasso.get().load(post.getMetadata()
                .getImgPath())
                .centerCrop()
                .resize(100,100)
                .into(holder.PostMainImg);
        holder.setOnclick(position);

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return PostList.size();
    }





    public class MyViewHolder extends RecyclerView.ViewHolder{
//        @BindView(R.id.PostImg)
        ImageView PostMainImg;
//        @BindView(R.id.PostMainTitle)
        TextView PostTitle;
//        @BindView(R.id.PostDescription)
        TextView PostDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this,itemView);
            PostMainImg = itemView.findViewById(R.id.PostImg);
            PostTitle= itemView.findViewById(R.id.PostMainTitle);
            PostDescription=itemView.findViewById(R.id.PostDescription);

        }

        void setOnclick(final int postion){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.w("onClick","clicked"+postion);
                    Intent i = new Intent(context,PostDetails.class);
                    i.putExtra("post",postion+"");
                    context.startActivity(i);
                }
            });
        }
    }

}
