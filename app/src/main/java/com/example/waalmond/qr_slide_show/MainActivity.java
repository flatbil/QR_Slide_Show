package com.example.waalmond.qr_slide_show;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private int RESULT_LOAD_IMG;
    TextView textView;
    Boolean toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RESULT_LOAD_IMG = 1;
        textView = findViewById(R.id.textView);
        textView.setText("Click the button.");
        toggle = false;
    }

    public void onClick(View view) {
        if(!toggle) {
            textView.setText("You did it!");
            getImageFromAlbum();
            toggle = true;
        } else {
            textView.setText("do it again");
            toggle = false;
        }
    }
    private void getImageFromAlbum(){
        try {
            Intent intent = new Intent();
// Show only images, no videos or anything else
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMG);
//

        }catch(Exception exp){
            Log.i("Error",exp.toString());
        }
    }
    @Override
    protected void onActivityResult(int requestCode,  int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri uri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.imageView3);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
