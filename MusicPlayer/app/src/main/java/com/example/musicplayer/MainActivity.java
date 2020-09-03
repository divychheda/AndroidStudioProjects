package com.example.musicplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView musicList;
    String[] mysongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicList = (ListView)findViewById(R.id.songlist);
            askPermission();
    }

    public void askPermission()
    {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        displayList();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }
    public ArrayList<File> findmp3(File file)
    {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        try {
            for(File song1:files) {
                if (song1.isDirectory() && !song1.isHidden())
                    arrayList.addAll(findmp3(song1));
                else {
                    if (song1.getName().endsWith(".mp3"))
                        arrayList.add(song1);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return arrayList;
    }
    void displayList(){
        final ArrayList<File> songsFound = findmp3(Environment.getExternalStorageDirectory());
        mysongs=new String[songsFound.size()];
        for(int i =0;i<songsFound.size();i++ )
        {
            mysongs[i] = songsFound.get(i).getName();
        }
        ArrayAdapter<String> adpt = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mysongs);
        musicList.setAdapter(adpt);

    }


}