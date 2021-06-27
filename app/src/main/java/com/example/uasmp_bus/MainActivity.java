package com.example.uasmp_bus;

import android.app.DatePickerDialog;
import android.content.Intent;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    Button search_buses;
    DatePickerDialog picker;
    EditText eText;
    Button btnGet;
    TextView tvw;
    AutoCompleteTextView autoCompleteTextView;
    @RequiresApi(api= Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        search_buses = findViewById(R.id.search_buses);
        search_buses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });

        autoCompleteTextView =  findViewById(R.id.from_item);
        String[] option = new String[] {"Jakarta", "Bandung"};
        ArrayAdapter adapterarray = new ArrayAdapter(this, R.layout.dropdown_menu_popup_item, option);
        autoCompleteTextView.setText(adapterarray.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(adapterarray);

        autoCompleteTextView =  findViewById(R.id.destination_item);
        String[] options = new String[] {"Jakarta", "Bandung"};
        ArrayAdapter adapterarrays = new ArrayAdapter(this, R.layout.dropdown_menu_popup_item, options);
        autoCompleteTextView.setText(adapterarray.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(adapterarrays);

        tvw=(TextView)findViewById(R.id.date_textv);
        eText=(EditText) findViewById(R.id.date_edit);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
//        btnGet=(Button)findViewById(R.id.register);
//        btnGet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvw.setText("Selected Date: "+ eText.getText());
//            }
//        });

    }
}
