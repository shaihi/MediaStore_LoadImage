package com.shaihi.mediastore_loadimage;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback<Uri>() {
                //Uri stands for: Uniform Resource Identifier
                @Override
                public void onActivityResult(Uri uri) {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        ImageView iv = findViewById(R.id.imagePreview);
                        iv.setImageURI(uri);
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                }
            });

        Button loadImages = findViewById(R.id.loadBtn);
        loadImages.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  // Launch the photo picker and let the user choose only images.
                  pickMedia.launch(new PickVisualMediaRequest.Builder()
                          .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                          .build());
              }
          });
    }
}