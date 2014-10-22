package com.example.frida.wmp;

/**
 * Created by Frida on 2014-09-24.
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

//Kategori f�ltet i formul�ret ska fyllas i av bulletpoint val
public class FragmentPG2 extends Fragment {
    private static final int CAM_REQUEST_CODE = 1001;
    private RadioGroup fillCategory;
    private RadioButton radio1Btn;
    private EditText fillTitle;
    private EditText fillPrice;
    private Button okpg2;
    private ImageView cam;
    private Button camBtn;
    private Bitmap picture;
    private DatabaseController data;
    private Button button;

    private String name;
    private String imgName;
    private int price;
    private String category;
    private String title;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private SharedPreferences sharedPreferences;
    private Uri fileUri;
   // private String price;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        name = fillTitle.getEditableText().toString();
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(name != null){
            fillTitle.setText(name);
        }

        View view = inflater.inflate(R.layout.pg2, container, false);
        fillCategory = (RadioGroup) view.findViewById(R.id.radioGroup_pg2);
        fillTitle = (EditText) view.findViewById(R.id.fill_title_pg2);
        fillPrice = (EditText) view.findViewById(R.id.fill_price_pg2);
        okpg2 = (Button) view.findViewById(R.id.ok_pg2);
        camBtn = (Button) view.findViewById(R.id.camera_button);
        button = (Button) view.findViewById(R.id.button1);

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        cam = (ImageView) view.findViewById(R.id.camera);
        Button startPage = (Button) view.findViewById(R.id.start_pg2);
        Button incomePage = (Button) view.findViewById(R.id.income_pg2);
        Button resultPage = (Button) view.findViewById(R.id.result_pg2);

        int selectedId = fillCategory.getCheckedRadioButtonId();
        radio1Btn = (RadioButton) view.findViewById(selectedId);

        // data = new DatabaseController(getActivity());
        DatabaseController.init(getActivity());
        MyListener myListener = new MyListener();

        startPage.setOnClickListener(myListener);
        incomePage.setOnClickListener(myListener);
        resultPage.setOnClickListener(myListener);
        okpg2.setOnClickListener(myListener);
        camBtn.setOnClickListener(myListener);
        button.setOnClickListener(myListener);
        radio1Btn.setOnClickListener(myListener);

        return view;
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.start_pg2)
                ((MainActivity) getActivity())
                        .changeFragments(new FragmentPG1(), "pg1");
            else if (v.getId() == R.id.income_pg2)
                ((MainActivity) getActivity())
                        .changeFragments(new FragmentPG3(), "pg3");
            else if (v.getId() == R.id.result_pg2)
                ((MainActivity) getActivity())
                        .changeFragments(new FragmentPG4(), "pg4");
            else if (v.getId() == R.id.button1) {
                DatabaseController.cleanExp();

            } else {
                if (v.getId() == R.id.ok_pg2) {
                    int selectedId = fillCategory.getCheckedRadioButtonId();
                    radio1Btn = (RadioButton) getActivity()
                            .findViewById(selectedId);

                    title = fillTitle.getText().toString();
                    //int price = 0;
                    // try {
                    String temp = fillPrice.getText().toString();

                    price = Integer.parseInt(temp.trim());
                    //om priset inte skrivs in felhantering


                    // } catch (Exception e) {
                    // TODO: handle exception
                    //}

                    category = radio1Btn.getText().toString();

                    //String image = BitMapToString(picture);

                    Expense exp = new Expense(title, price, category,fileUri.getPath()); // , imgName, imgName sätter om värdet till imgName längre ner i koden??
                    DatabaseController.addExpense(exp);  //ta bort image enbart ha i shared prefs kalla på den i expenseadapter

                } else if (v.getId() == R.id.camera_button) {

                    Intent getPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("filelocation", fileUri.getPath());
                    editor.commit();



                    startActivityForResult(getPhoto, 1001);



                    //if (imgName != "") {


                        //UTGIFTER LÄGGS INTE IN LÄNGRE ENDAST LÄNKEN TILL IMGNAME LAGRAS

                        //Toast.makeText(
                             //   getActivity(),
                             //   Integer.toString(DatabaseController.getExpense()
                                   //     .getPrice()), Toast.LENGTH_SHORT).show();
                      //  System.out.println(imgName);

                        //kolla så imgName får ett värde. Lagras strängen i databasen?

                   // }


                }


            }





            }
        }





    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    public void saveToDatabase() {


    }
    public void getPicInfo(int requestCode, int resultCode, Intent data){
        if(requestCode == CAM_REQUEST_CODE){ //kollar så det är från kameran jag får resultat
            if(resultCode == MEDIA_TYPE_IMAGE){ //kollar så att jag fått data

                fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);  // create a file to save the video
                data.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name
                Uri image = data.getData();//Hämtar datan och lägger det i ett Uri objekt
               // imgName = image.getPath(); //lagrar sökväg till bilden i en sträng
            }
        }
    }



}
