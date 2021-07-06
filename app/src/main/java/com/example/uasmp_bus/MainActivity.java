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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private String nama;
    public static final String EXTRA_MESSAGES =
            "com.example.android.uts.extra.MESSAGES";
    Button search_buses;
    DatePickerDialog picker;
    EditText eText;
    Button btnGet;
    TextView tvw;
    TextView tgl;
    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteTextView autoCompleteTextView1;
    public String KEY_DESTI ="DESTINATION";
    public String KEY_FROM ="FROM";
    public String KEY_DATE ="DATE";
    @RequiresApi(api= Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        TextView name_ed = (TextView) findViewById(R.id.nameView);
        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            name_ed.setText(bundle.getString("NAMA"));
        }else{
            name_ed.setText( getIntent().getStringExtra("NAMA"));

        }


        autoCompleteTextView =  findViewById(R.id.from_item);
        String[] option = new String[] {"Jakarta", "Bandung"};
        ArrayAdapter adapterarray = new ArrayAdapter(this, R.layout.dropdown_menu_popup_item, option);
        autoCompleteTextView.setText(adapterarray.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(adapterarray);

        autoCompleteTextView1 =  findViewById(R.id.destination_item);
        String[] options = new String[] {"Bandung", "Jakarta"};
        ArrayAdapter adapterarrays = new ArrayAdapter(this, R.layout.dropdown_menu_popup_item, options);
        autoCompleteTextView1.setText(adapterarrays.getItem(0).toString(), false);
        autoCompleteTextView1.setAdapter(adapterarrays);

        tgl=(TextView)findViewById(R.id.date);
        tvw=(TextView)findViewById(R.id.date_textv);
        eText=(EditText) findViewById(R.id.date_edit);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int dayw = cldr.get(Calendar.DAY_OF_WEEK);
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog

                picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view,int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                                Date date = new Date(year, monthOfYear, dayOfMonth-1);
                                String dayOfWeek = simpledateformat.format(date);
                                SimpleDateFormat month_date = new SimpleDateFormat("MMM");
                                String month_name = month_date.format(cldr.getTime());
                                tgl.setText(dayOfMonth + " - " + month_name + " - " + year + "   | " + dayOfWeek );
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        search_buses = findViewById(R.id.search_buses);
        search_buses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                Intent intent1 = new Intent(getApplicationContext(), Main3Activity.class);
                intent.putExtra("DESTINATION", autoCompleteTextView1.getText().toString());
                intent.putExtra("FROM", autoCompleteTextView.getText().toString());
                intent.putExtra("DATE", tgl.getText().toString());
                intent.putExtra("NAMA", name_ed.getText().toString());
//                intent1.putExtra("DESTINATION", autoCompleteTextView1.getText().toString());
//                intent1.putExtra("FROM", autoCompleteTextView.getText().toString());
//                intent1.putExtra("DATE", tgl.getText().toString());
//                intent1.putExtra("NAMA", name_ed.getText().toString());
                startActivity(intent);
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
