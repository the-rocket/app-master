package com.daniyar.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    public final static String EXTRA_PHOTO = "EXTRA_PHOTO";
    private final static int REQUEST_CODE = 1010;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager llm;


    //@Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.fab) View actionButton;
    @Bind(R.id.my_recycle_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        initViews();

    }

    private void initViews() {

        mRecyclerView.hasFixedSize();
        llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start picker
                Intent intent = new Intent(MainActivity.this, PickerActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, String.format("Adapter has items: %d", mAdapter.getItemCount()), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

}
