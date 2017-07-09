package com.diamond.diamond;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Files extends AppCompatActivity {

    private static final String ERROR_TAG = "files.error";


    /**
     * To check if external storage is mounted or not
     */
    // For writing external storage
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    // For reading external storage
    public boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;
    }

    // This won't be deleted once the user will delete the app
    public File getPublicAlbumStorageDir(String albumName) {
    // This is to create public picture directory in external storage
    File albumFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) , albumName);

   if(!albumFile.mkdirs()){
        Log.e(ERROR_TAG , "Directory not created");
    }
        return albumFile;
    }

    // This will be deleted once user will delete the app
    public File getPrivateAlbumStorageDir(Context context , String albumName){
        File albumFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) , albumName);

        if(!albumFile.mkdir()){
            Log.e(ERROR_TAG , "Directory not created");
        }
        return albumFile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);


        //Toast.makeText(getBaseContext() , this.getFilesDir().toString() , Toast.LENGTH_SHORT).show();


        // Common variables
        String filename = "newfile.txt";
        FileOutputStream writeToFile;
        FileInputStream readFromFile;
        TextView display = (TextView) findViewById(R.id.fileDisplay);


        // This is to store data and read data from the internal storage
        try {
            writeToFile = openFileOutput(filename, Context.MODE_PRIVATE);
            writeToFile.write("sf as1231232!@#@!#@#$#$%$".getBytes());
            writeToFile.close();

            // This is to read from that file
            readFromFile = openFileInput(filename);

            int c;
            String temp = "";
            while ((c = readFromFile.read()) != -1) {
                temp = temp + Character.toString((char) c);
            }
            readFromFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // This is to create the temp file
        File file;
        try {
            String tempFilename = Uri.parse("http://www.google.com/file/test/back/new.html").getLastPathSegment();
            file = File.createTempFile(tempFilename, null, this.getCacheDir());
            // Above will create new.html
        } catch (IOException e) {
            e.printStackTrace();
        }


        // This is to create new directory
        String path = this.getFilesDir().toString() + "/userfiles/check.txt";
        File f = new File(path);
        if (f.mkdirs()) {
            Toast.makeText(getBaseContext() , "created " + path , Toast.LENGTH_LONG).show();
            Toast.makeText(getBaseContext() , Long.toString(f.getFreeSpace()) , Toast.LENGTH_LONG).show();
        } else {
            // Toast.makeText(getBaseContext() , "failed" , Toast.LENGTH_LONG).show();
            f.delete();
        }




        // This is to get list of all the files and directories
        String full = this.getFilesDir().getParent();
        File l = new File(full);
        String[] fileDir = l.list();
        String list = "";

        for (String name : fileDir) {
            list = list + "\n" + name;
        }

        display.setText(list);

        Toast.makeText(getBaseContext(), full, Toast.LENGTH_LONG).show();

        // This is another test comment

    }

}