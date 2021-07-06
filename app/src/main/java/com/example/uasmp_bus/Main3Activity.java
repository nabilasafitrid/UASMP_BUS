package com.example.uasmp_bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    GridLayout mainGrid;
    Button pesan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seat);

        TextView name_ed = (TextView) findViewById(R.id.nama1view);
        TextView from_ed = (TextView) findViewById(R.id.from1view);
        TextView des_ed = (TextView) findViewById(R.id.des1view);
        TextView date_ed = (TextView) findViewById(R.id.dateview);
        TextView nama_bis = (TextView) findViewById(R.id.Nama_Bis);
        TextView harga_bis = (TextView) findViewById(R.id.Harga_Bis);
        TextView waktuber = (TextView) findViewById(R.id.Waktu_BisBer);
        TextView waktuti = (TextView) findViewById(R.id.Waktu_BisTi);
        TextView fasilitas_bis = (TextView) findViewById(R.id.Fasilitas_Bis);
        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            name_ed.setText(bundle.getString("NAMA"));
            des_ed.setText(bundle.getString("DESTINATION"));
            from_ed.setText(bundle.getString("FROM"));
            date_ed.setText(bundle.getString("DATE"));
            nama_bis.setText(bundle.getString("NAMABIS"));
            harga_bis.setText(bundle.getString("HARGA"));
            waktuber.setText(bundle.getString("WAKTUBER"));
            waktuti.setText(bundle.getString("WAKTUTI"));
            fasilitas_bis.setText(bundle.getString("FASILITAS"));
        }else{
            name_ed.setText( getIntent().getStringExtra("NAMA"));
            des_ed.setText( getIntent().getStringExtra("DESTINATION"));
            from_ed.setText( getIntent().getStringExtra("FROM"));
            date_ed.setText( getIntent().getStringExtra("DATE"));
            nama_bis.setText( getIntent().getStringExtra("NAMABIS"));
            harga_bis.setText( getIntent().getStringExtra("HARGA"));
            waktuber.setText( getIntent().getStringExtra("WAKTUBER"));
            waktuti.setText( getIntent().getStringExtra("WAKTUTI"));
            fasilitas_bis.setText( getIntent().getStringExtra("FASILITAS"));

        }

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        setToggleEvent(mainGrid);

        pesan = findViewById(R.id.pesan);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Invoice.class);
                intent.putExtra("DESTINATION", des_ed.getText().toString());
                intent.putExtra("FROM", from_ed.getText().toString());
                intent.putExtra("DATE", date_ed.getText().toString());
                intent.putExtra("NAMA", name_ed.getText().toString());
                intent.putExtra("NAMABIS", nama_bis.getText().toString());
                intent.putExtra("FASILITAS", fasilitas_bis.getText().toString());
                intent.putExtra("HARGA", harga_bis.getText().toString());
                intent.putExtra("WAKTUBER", waktuber.getText().toString());
                intent.putExtra("WAKTUTI", waktuti.getText().toString());
                startActivity(intent);

            }
        });

    }



    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
//            String getName = ((TextView) mainGrid.getChildAt(i)).getText().toString();
            final int finalI = i;
//            TextView set = (TextView) findViewById(R.id.seat);
//            set.setText(getName);

//            id[i] = getName;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#f5a623"));


                    }else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#ded7ee"));
                    }
                }
            });
        }
    }

}
