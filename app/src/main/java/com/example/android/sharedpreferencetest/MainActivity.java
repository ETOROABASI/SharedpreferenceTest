package com.example.android.sharedpreferencetest;

import android.content.SharedPreferences;
import android.os.Build;
import android.service.autofill.FillEventHistory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

   private static final String PREFERENCE_FILE_NAME = "colorPreferences";
//
 //   private final int DEFAULT_COLOR = getResources().getColor(R.color.colorPrimary);  //this crashes my app why (it says null object reference)

    Toolbar mToolbar;
    Button btn_red, btn_green, btn_blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        mToolbar = findViewById(R.id.tb_toolbar);
       btn_blue = findViewById(R.id.button_blue);
        btn_green = findViewById(R.id.button_green);
        btn_red = findViewById(R.id.button_red);
        mToolbar.setTitle(getResources().getString(R.string.app_name));

//        if(getColor() != getResources().getColor(R.color.colorPrimary) ){       //THIS MEANS IF SOMETHNG HAS BEEN INPUTTED AND THE COLOR IS THEREFORE
                                                // NOT THE DEFAULT ONE

           //this sets the color to what has been saved in the shared preference whenever you re-open your app
            mToolbar.setBackgroundColor(getColor());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getColor());
            }

        //}

        btn_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //this just sets the color to blue for the meantime so that the user can have a visual aid. it doesn't store it

                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorBluePrimary));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //setting of status bar starts from api21, LOLLIPOP
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorBluePrimary));
                    //this sets the color of the status  bar also
                }

                //this now stores the color
                storeColor(getResources().getColor(R.color.colorBluePrimary));  //stores the color in the sharedPreference


            }
        });

        btn_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //this just sets the color to green for the meantime so that the user can have a visual aid. it doesn't store it
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorGreenPrimary));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //setting of status bar starts from api21, LOLLIPOP
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorGreenPrimary));
                    //this sets the color of the status  bar also
                }


                //this now stores the color  in the shared preference
                storeColor(getResources().getColor(R.color.colorGreenPrimary));




            }
        });


        btn_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this just sets the color to red for the meantime so that the user can have a visual aid. it doesn't store it
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorRedPrimary));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //setting of status bar starts from api21, LOLLIPOP
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorRedPrimary));
                    //this sets the color of the status  bar also
                }

                //this stores the color in the sharedpreference
                storeColor(getResources().getColor(R.color.colorRedPrimary));



            }
        });

    }

    private void storeColor(int color){

        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE);
        //the PREFERENCE_FILE_NAME is the name of the file that would be created on the device to store the necessary data
        //the mode private shows that the file created would not be seen by (hence canoot be used by) other apps

        SharedPreferences.Editor editor = sharedPreferences.edit();     //opens it up for editing

        editor.putInt("color", color);      // this is the key-alue pair being used here

        editor.apply();
    }


    //WHEN CALLED, THIS METHOD GETS THE COLOR SAVED IN THE SHAREDPREFERENCE. IT IS CALLED IN THE ONCREATE METHOD, SO THAT
    //IT UPDATES THE APP WITH THE RECENT COLOR EVERYTIME THE APP IS OPENED
    private int getColor(){
        SharedPreferences mSharedPreferences = MainActivity.this.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE);

        int selectedColor = mSharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        //we are trying to get the value that must have been inputted. The second parameter is the default value,
        //this signifies the value the device would use if there's an error loading the ohters or nothing is selected

        return selectedColor;
    }
}
