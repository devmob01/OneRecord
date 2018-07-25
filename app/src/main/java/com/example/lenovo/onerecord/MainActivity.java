package com.example.lenovo.onerecord;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    private Button startbtn , stopbtn, switchbtn ;
    private double[] temp ;
    private static final int REQUEST_RECORD = 988;

    private String outputfile="" ;
    private String outputfolder="" ;



    private static final String[] PERMISSIONS ={Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.INTERNET,Manifest.permission.RECORD_AUDIO};


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_RECORD:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // permissions granted.
                    createFolder("folder");
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbtn = (Button) findViewById(R.id.startid);
        stopbtn = (Button) findViewById(R.id.stopid);
        switchbtn = (Button) findViewById(R.id.switchid);
        temp = new double[100] ;

        /*
        we verify if we have the permission to read , write  , record ......
         */
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            Log.e("Permission", "Granted !");
        } else {
            Log.e("Permission", "Not Granted !");
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.RECORD_AUDIO)) {
                Log.e("Permission", "Not Granted ! C2! ");
            }
            else
                {
                Log.e("Permission", "ASK !");
                //REQUEST PERMISSION
                ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, REQUEST_RECORD);
            }
            outputfolder = createFolder("folder");

        }
            startbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });


    }






    public String createFolder(String foldername){

        String myfolder = Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/download/"+foldername ;
        File f = new File(myfolder);
        if (!f.exists()) {
            f.mkdir();
            if (!f.exists()) {
                Toast.makeText(this, "Folder will be created", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Folder Created ! ", Toast.LENGTH_SHORT).show();
            }
        }
        return myfolder ;
    }

    public void configureAudioRecord(){
        outputfile = Environment.getExternalStorageDirectory().getAbsolutePath()+"";
    }
}
