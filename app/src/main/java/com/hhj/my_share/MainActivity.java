package com.hhj.my_share;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void share_txt(View view){
        Intent intent  =new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"这是一段分享文字");
        startActivity(Intent.createChooser(intent,"分享"));
    }

    public void share_img(View view){
        String filePath = ""; //分享图片的地址
        Intent intent  =new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
        startActivity(Intent.createChooser(intent,"分享图片"));
    }

    public void share_file(View view){
        Uri filePath = Uri.parse(getResourcesUri(R.drawable.ic_launcher_background));
        Intent intent  =new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent,"分享文件"));
    }


    private String getResourcesUri(@DrawableRes int id) {
        Resources resources = getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        Toast.makeText(this, "Uri:" + uriPath, Toast.LENGTH_SHORT).show();
        return uriPath;
    }
}
