package com.example.uasmp_bus;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "DocSnippets";
    CardView cardView1;CardView cardView2;CardView cardView3;

    private RecyclerView recyclerView;
    private Myadapter adapter;
    private ArrayList<dataBis> bisArrayList;
    public String KEY_NAMEBUS ="NAMABIS";
    public String KEY_FAS ="FASILITAS";
    public String KEY_HARGA ="HARGA";
    public String KEY_WAKTUBER ="WAKTUBER";
    public String KEY_WAKTUTI ="WAKTUTI";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);

        TextView name_ed = (TextView) findViewById(R.id.nameview);
        TextView from_ed = (TextView) findViewById(R.id.fromview);
        TextView des_ed = (TextView) findViewById(R.id.desview);
        TextView date_ed = (TextView) findViewById(R.id.dateview);
        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            name_ed.setText(bundle.getString("NAMA"));
            des_ed.setText(bundle.getString("DESTINATION"));
            from_ed.setText(bundle.getString("FROM"));
            date_ed.setText(bundle.getString("DATE"));
        }else{
            name_ed.setText( getIntent().getStringExtra("NAMA"));
            des_ed.setText( getIntent().getStringExtra("DESTINATION"));
            from_ed.setText( getIntent().getStringExtra("FROM"));
            date_ed.setText( getIntent().getStringExtra("DATE"));

        }

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.recyleview);

        adapter = new Myadapter(bisArrayList, getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Main2Activity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        TextView nama_bis1 = (TextView) findViewById(R.id.nama_bis1);
        TextView harga_bis1 = (TextView) findViewById(R.id.harga_bis1);
        TextView waktuber1 = (TextView) findViewById(R.id.waktu_bisber);
        TextView waktuti1 = (TextView) findViewById(R.id.waktu_bisti);
        TextView fasilitas_bis1 = (TextView) findViewById(R.id.fasilitas_bis1);
        TextView nama_bis2 = (TextView) findViewById(R.id.nama_bis2);
        TextView harga_bis2 = (TextView) findViewById(R.id.harga_bis2);
        TextView waktuber2 = (TextView) findViewById(R.id.waktu_bisber2);
        TextView waktuti2 = (TextView) findViewById(R.id.waktu_bisti2);
        TextView fasilitas_bis2 = (TextView) findViewById(R.id.fasilitas_bis2);
        TextView nama_bis3 = (TextView) findViewById(R.id.nama_bis3);
        TextView harga_bis3 = (TextView) findViewById(R.id.harga_bis3);
        TextView waktuber3 = (TextView) findViewById(R.id.waktu_bisber3);
        TextView waktuti3 = (TextView) findViewById(R.id.waktu_bisti3);
        TextView fasilitas_bis3 = (TextView) findViewById(R.id.fasilitas_bis3);

        cardView1 = findViewById(R.id.cardView1);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                intent.putExtra("DESTINATION", des_ed.getText().toString());
                intent.putExtra("FROM", from_ed.getText().toString());
                intent.putExtra("DATE", date_ed.getText().toString());
                intent.putExtra("NAMA", name_ed.getText().toString());
                intent.putExtra("NAMABIS", nama_bis1.getText().toString());
                intent.putExtra("FASILITAS", fasilitas_bis1.getText().toString());
                intent.putExtra("HARGA", harga_bis1.getText().toString());
                intent.putExtra("WAKTUBER", waktuber1.getText().toString());
                intent.putExtra("WAKTUTI", waktuti1.getText().toString());
                startActivity(intent);

            }
        });
        cardView2 = findViewById(R.id.cardView2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                intent.putExtra("DESTINATION", des_ed.getText().toString());
                intent.putExtra("FROM", from_ed.getText().toString());
                intent.putExtra("DATE", date_ed.getText().toString());
                intent.putExtra("NAMA", name_ed.getText().toString());
                intent.putExtra("NAMABIS", nama_bis2.getText().toString());
                intent.putExtra("FASILITAS", fasilitas_bis2.getText().toString());
                intent.putExtra("HARGA", harga_bis2.getText().toString());
                intent.putExtra("WAKTUBER", waktuber2.getText().toString());
                intent.putExtra("WAKTUTI", waktuti2.getText().toString());
                startActivity(intent);

            }
        });
        cardView3 = findViewById(R.id.cardView3);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                intent.putExtra("DESTINATION", des_ed.getText().toString());
                intent.putExtra("FROM", from_ed.getText().toString());
                intent.putExtra("DATE", date_ed.getText().toString());
                intent.putExtra("NAMA", name_ed.getText().toString());
                intent.putExtra("NAMABIS", nama_bis3.getText().toString());
                intent.putExtra("FASILITAS", fasilitas_bis3.getText().toString());
                intent.putExtra("HARGA", harga_bis3.getText().toString());
                intent.putExtra("WAKTUBER", waktuber3.getText().toString());
                intent.putExtra("WAKTUTI", waktuti3.getText().toString());
                startActivity(intent);

            }
        });

//        @Override
//        public void onRowMakananAdapterClicked(int position) {
//            String nama = getMakananSundaList().get(position).getNama();
//            Toast.makeText(this, "Kamu memilih makanan " + nama, Toast.LENGTH_SHORT).show();
//        }
    }

    void addData(){
        bisArrayList = new ArrayList<>();
//        bisArrayList.add(new dataBis("Expo Bus Service", "Rp.80.000", "A/C Sleeper (2+2)", "09.00", "12.00"));
//        bisArrayList.add(new dataBis("Red Bus Service", "Rp.86.000", "A/C Sleeper (2+2)", "12.00", "14.45"));
//        bisArrayList.add(new dataBis("Travelo Service", "Rp.90.000", "A/C Sleeper (2+2)", "19.00", "20.30"));
//        bisArrayList.add(new dataBis("Aham Siswana", "1214378098", "098758124"));
    }
}
