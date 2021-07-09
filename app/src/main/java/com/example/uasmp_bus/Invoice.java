package com.example.uasmp_bus;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.firestore.FirebaseFirestore;

        import java.util.HashMap;
        import java.util.Map;

public class Invoice extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private static final String TAG = "Invoice";

    private static final String KEY_TGL = "TGL";
    private static final String KEY_DESTINATION = "Destination";
    private static final String KEY_ASAL = "From";
    private static final String KEY_PUKUL = "Waktu berangkat";
    private static final String KEY_WAKTUTIBA = "Waktu tiba";
    private static final String KEY_NAMABIS = "Nama";
    private static final String KEY_HARGA = "Harga";
    private static final String KEY_NOKURSI = "No Kursi";

    private Spinner metodepembayaran;
    private String METODE[] = {"BNI","BCA"};

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);


        TextView name_ed = (TextView) findViewById(R.id.nama3view);
        TextView from_ed = (TextView) findViewById(R.id.from3view);
        TextView des_ed = (TextView) findViewById(R.id.des3view);
        TextView date_ed = (TextView) findViewById(R.id.date4view);
        TextView harga_bis = (TextView) findViewById(R.id.harga_bis5);
        TextView waktuber = (TextView) findViewById(R.id.waktu_bisber5);
        TextView waktuti = (TextView) findViewById(R.id.waktu_bisti5);
        TextView total = (TextView) findViewById(R.id.total);

        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            name_ed.setText(bundle.getString("NAMA"));
            des_ed.setText(bundle.getString("DESTINATION"));
            from_ed.setText(bundle.getString("FROM"));
            date_ed.setText(bundle.getString("DATE"));
            harga_bis.setText(bundle.getString("HARGA"));
            total.setText(bundle.getString("HARGA"));
            waktuber.setText(bundle.getString("WAKTUBER"));
            waktuti.setText(bundle.getString("WAKTUTI"));
        }else {
            name_ed.setText(getIntent().getStringExtra("NAMA"));
            des_ed.setText(getIntent().getStringExtra("DESTINATION"));
            from_ed.setText(getIntent().getStringExtra("FROM"));
            date_ed.setText(getIntent().getStringExtra("DATE"));

            total.setText(getIntent().getStringExtra("HARGA"));
            harga_bis.setText(getIntent().getStringExtra("HARGA"));
            waktuber.setText(getIntent().getStringExtra("WAKTUBER"));
            waktuti.setText(getIntent().getStringExtra("WAKTUTI"));

        }

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
                Intent intent = new Intent(getApplicationContext(),ticket.class);
                intent.putExtra("DESTINATION", des_ed.getText().toString());
                intent.putExtra("FROM", from_ed.getText().toString());
                intent.putExtra("DATE", date_ed.getText().toString());
                intent.putExtra("NAMA", name_ed.getText().toString());

                intent.putExtra("HARGA", harga_bis.getText().toString());
                intent.putExtra("WAKTUBER", waktuber.getText().toString());
                intent.putExtra("WAKTUTI", waktuti.getText().toString());
                startActivity(intent);

                String tgl         = date_ed.getText().toString();
                String destination = des_ed.getText().toString();
                String asal        = from_ed.getText().toString();
                String pukul       = waktuber.getText().toString();
                String nama        = name_ed.getText().toString();
                String harga       = harga_bis.getText().toString();
                String waktutiba   = waktuti.getText().toString();

                Map<String, Object> note = new HashMap<>();

                note.put(KEY_TGL, tgl);
                note.put(KEY_DESTINATION, destination);
                note.put(KEY_ASAL, asal);
                note.put(KEY_PUKUL, pukul);
                note.put(KEY_NAMABIS, nama);
                note.put(KEY_HARGA, harga);
                note.put(KEY_WAKTUTIBA, waktutiba);

                db.collection("BUZEE").document("Invoice").set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Invoice.this, "SAVED", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Invoice.this, "Error!", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, e.toString());
                            }
                        });

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