package com.example.dog;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.jcraft.jsch.JSch;

import java.io.File;
import java.io.IOException;


public class use_page extends AppCompatActivity implements View.OnClickListener {
    private CheckBox lrc;
    private EditText roomNumber;
    private Button enter;
    private ImageView photo_box;

    private String video_path = "/storage/emulated/0/Pictures/VID_CAPTURED.mp4";

    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        lrc = findViewById(R.id.lrc);
        roomNumber = findViewById(R.id.roomNumber);
        enter = findViewById(R.id.use_enter);
        photo_box = findViewById(R.id.photo_box);

        enter.setOnClickListener(this);

        //set lrc check box
        lrc.setOnCheckedChangeListener((buttonView, isChecked) -> {
            roomNumber.setText("418");
            if (!isChecked){
                roomNumber.setText("");
            }
        });

        //get camera permission
        if(ContextCompat.checkSelfPermission(use_page.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(use_page.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

    }

    private void take_video() throws IOException {
        String room_num = roomNumber.getText().toString();
        if( room_num.matches("")){
            Toast.makeText(this, "plz enter room number", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, ""+room_num , Toast.LENGTH_SHORT).show();
            File video_cap = new File(video_path);
            fileUri = FileProvider.getUriForFile(use_page.this,
                    "com.example.homefolder.example.provider",video_cap);

            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, 100);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){

            Ssh_upload ssh_upload = new Ssh_upload(video_path);
            String message = ssh_upload.upload();

            //Toast.makeText(this, ""+data.getData().toString(), Toast.LENGTH_SHORT).show();
            /*Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            photo_box.setImageBitmap(bitmap);*/

        }
    }


    @Override
    public void onClick(View view) {
        if(view == enter){
            try {
                take_video();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
