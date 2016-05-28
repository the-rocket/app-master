package com.daniyar.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

import butterknife.Bind;

/**
 * Created by alashov on 28/04/16.
 */
public class PickerActivity extends BaseActivity {

    private final static int REQUEST_PHOTO = 100;

    private Photo mPhoto = new Photo();
    private boolean textempty = false;

    private RecyclerViewAdapter mRecyclerViewAdapter = new RecyclerViewAdapter();


    @Bind(R.id.title) EditText titleView;
    @Bind(R.id.add) Button addView;
    @Bind(R.id.back) Button backView;
    @Bind(R.id.preview) ImageView previewView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PHOTO && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "ok " + data.getData(), Toast.LENGTH_LONG).show();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                ImageView image = new ImageView(getApplicationContext());
                image.setImageBitmap(bitmap);
                PickerActivity.this.setContentView(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Glide.with(this).load(mPhoto.getUri()).into(previewView);
        }
    }

    private void init() {
        initViews();
    }

    private void initViews() {
        backView.setEnabled(false);
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, REQUEST_PHOTO);
            }
        });

        titleView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPhoto.setTitle(s.toString());
                if (s.length() > 0)
                    textempty = true;
                else
                    textempty = false;
                if(textempty == true) {
                    backView.setEnabled(true);
                    backView.setClickable(true);
                }
                else {
                    backView.setEnabled(false);
                    backView.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void finish() {
        try {
            mRecyclerViewAdapter.add();
        } catch (NullPointerException e) {
            Log.d("Null error", "korocgeskapdk");
        }
        super.finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_picker;
    }
}
