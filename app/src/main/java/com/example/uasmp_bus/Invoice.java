package com.example.uasmp_bus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Invoice extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner metodepembayaran;
    private String METODE[] = {"BNI","BCA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        // untuk spinner
        metodepembayaran = findViewById(R.id.metodepembayaran);
        metodepembayaran.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,METODE);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        metodepembayaran.setAdapter(arrayAdapter);

        //untuk tombol button bayar

        Button tombolbayar = (Button) findViewById(R.id.tombolbayar);
        tombolbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}