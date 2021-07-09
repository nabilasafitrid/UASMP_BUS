package com.example.uasmp_bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private static final String TAG = "Register";
    private static final String KEY_TITLE = "Email";
    private static final String KEY_DESCRIPTION = "Nama";
    private static final String KEY_NOHP ="nohp";

    Button register;
    public TextView get_nama ;
    public String KEY_NAME ="NAMA";

    private EditText email;
    private EditText nama;
    private EditText nohp;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        get_nama = (EditText) findViewById(R.id.nama);
        email = findViewById(R.id.email);
        nama = findViewById(R.id.nama);
        nohp = findViewById(R.id.nohp);

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("NAMA", get_nama.getText().toString());
                startActivity(intent);

                String title = email.getText().toString();
                String description = nama.getText().toString();
                String Nohp = nohp.getText().toString();

                Map<String, Object> note = new HashMap<>();
                note.put(KEY_TITLE, title);
                note.put(KEY_DESCRIPTION, description);
                note.put(KEY_NOHP, Nohp);

                db.collection("BUZEE").document("Profil").set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Register.this, "Note saved", Toast.LENGTH_SHORT).show();
                            }

                        })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Register.this, "Error!", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, e.toString());
                            }
                        });
            }
        });
    }
}




//        if(hasConnection(Register.this)) {
//            //call methods
//            //getJsonData();
//            startActivity(new Intent(getApplicationContext(),Register.class));
//        } else {
//            showNetDisabledAlertToUser(Register.this);
//        }



    /*public boolean hasConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }
        return false;
    }

    public static void showNetDisabledAlertToUser(final Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, AlertDialog.THEME_TRADITIONAL);
        alertDialogBuilder.setMessage("Would you like to enable it?")
                .setTitle("No Internet Connection")
                .setPositiveButton(" Enable Internet ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent dialogIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(dialogIntent);
                    }
                });

        alertDialogBuilder.setNegativeButton(" Cancel ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}

     */