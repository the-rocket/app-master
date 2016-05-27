package com.daniyar.app;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Photo mPhoto;
    private Context context;
    List<E> myArrayList;

    //@Bind(R.id.my_recycle_view) RecyclerView mRecyclerView;

    public RecyclerViewAdapter() {
        myArrayList = new ArrayList<>();
    //    if (myArrayList != null)
    //    myArrayList.clear();
    }

    public static class E {
        private Uri uri;
        private String title;

        public  E() {
            uri = null;
            title = null;
        }

        public E set(Uri uri, String title) {
            this.uri = uri;
            this.title = title;
            return this;
        }

        public Uri getUri() {
            return uri;
        }
        public String getTitle() {
            return title;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.photo);
            mTextView = (TextView) itemView.findViewById(R.id.title);
        }
    }

    public void add() {
        Log.d("add function started","add");
        E newest = new E();
//        if (mPhoto.getUri() == null) {
//            mPhoto.setUri(Uri.parse("android.resource://com.daniyar.app/drawable/ic_add_white_18dp"));
//            Log.d("Uri equals to :", mPhoto.getUri().toString());
//        }
//      //  Log.d("Uri equals to :", mPhoto.getUri().toString());
        newest.set(mPhoto.getUri(), mPhoto.getTitle());
        myArrayList.add(newest);
    //    mRecyclerView.setAdapter(myArrayList);
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_photo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        //E ed = myArrayList.get(position);
        Glide.with(context).load(myArrayList.get(position).getUri()).into(holder.mImageView);
        holder.mTextView.setText(myArrayList.get(position).getTitle());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        if (myArrayList == null)
            return -1;
        else
            return myArrayList.size();
    }

}
