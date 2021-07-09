package com.example.uasmp_bus;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uasmp_bus.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ticket extends AppCompatActivity {

    Display mDisplay;
    String imagesUri;
    String path;
    Bitmap bitmap;

    int totalHeight;
    int totalWidth;

    public static final int READ_PHONE = 110;
    String file_name = "Screenshot";
    File myPath;
    Button btnPrint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        TextView descaps = (TextView) findViewById(R.id.descaps);
        TextView fromcaps = (TextView) findViewById(R.id.fromcaps);
        TextView name_dt1 = (TextView) findViewById(R.id.nameView);
        TextView from_dt1 = (TextView) findViewById(R.id.from2view);
        TextView des_dt1 = (TextView) findViewById(R.id.des2view);
        TextView date_dt1 = (TextView) findViewById(R.id.date2view);
        TextView date_dt2 = (TextView) findViewById(R.id.date3view);
        TextView waktuber = (TextView) findViewById(R.id.waktu_bisber4);
        TextView waktuti = (TextView) findViewById(R.id.waktu_bisti4);

        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            name_dt1.setText(bundle.getString("NAMA"));
            des_dt1.setText(bundle.getString("DESTINATION"));
            from_dt1.setText(bundle.getString("FROM"));
            date_dt1.setText(bundle.getString("DATE"));
            date_dt2.setText(bundle.getString("DATE"));
            waktuber.setText(bundle.getString("WAKTUBER"));
            waktuti.setText(bundle.getString("WAKTUTI"));
            fromcaps.setText(bundle.getString("FROM"));
            descaps.setText(bundle.getString("DESTINATION"));
        }else{
            name_dt1.setText( getIntent().getStringExtra("NAMA"));
            des_dt1.setText( getIntent().getStringExtra("DESTINATION"));
            from_dt1.setText( getIntent().getStringExtra("FROM"));
            date_dt1.setText( getIntent().getStringExtra("DATE"));
            date_dt2.setText( getIntent().getStringExtra("DATE"));
            waktuber.setText( getIntent().getStringExtra("WAKTUBER"));
            waktuti.setText( getIntent().getStringExtra("WAKTUTI"));
            fromcaps.setText(getIntent().getStringExtra("FROM"));
            descaps.setText(getIntent().getStringExtra("DESTINATION"));
        }


        btnPrint = findViewById(R.id.btn_print);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mDisplay = wm.getDefaultDisplay();

        if(android.os.Build.VERSION.SDK_INT >= 28){
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
            && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED){
            }else{
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, READ_PHONE);
            }
        }

        btnPrint.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                btnPrint.setVisibility(View.GONE);

                takeScreenShot();

                btnPrint.setVisibility(View.VISIBLE);
            }
        });
    }

    public Bitmap getBitmapFromView(View view, int totalHeight, int totalWidth){

        Bitmap returnedBitmap = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();

        if(bgDrawable != null){
            bgDrawable.draw(canvas);
        }else{
            canvas.drawColor(Color.WHITE);
        }

        view.draw(canvas);
        return returnedBitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void takeScreenShot(){

        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ScreenShot/");

        if(!folder.exists()){
            boolean success = folder.mkdir();
        }

        path = folder.getAbsolutePath();
        path = path + "/" + file_name + System.currentTimeMillis() + ".pdf";

        View u = findViewById(R.id.tickets);

        NestedScrollView z = findViewById(R.id.tickets);
        totalHeight = z.getChildAt(0).getHeight();
        totalWidth = z.getChildAt(0).getWidth();

        String extr = Environment.getExternalStorageDirectory() + "/Flight Ticket/";
        File file = new File(extr);
        if(!file.exists())
            file.mkdir();
        String fileName = file_name + ".jpg";
        myPath = new File(extr, fileName);
        imagesUri = myPath.getPath();
        bitmap = getBitmapFromView(u, totalHeight, totalWidth);

        try{
            FileOutputStream fos = new FileOutputStream(myPath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        createPdf();


    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf() {

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();


        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        canvas.drawPaint(paint);

        Bitmap bitmap = Bitmap.createScaledBitmap(this.bitmap, this.bitmap.getWidth(), this.bitmap.getHeight(), true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);
        File filePath = new File(path);
        try{
            document.writeTo(new FileOutputStream(filePath));
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Something Wrong: "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        document.close();

        if (myPath.exists())
            myPath.delete();

        openPdf(path);

    }

    private void openPdf(String path) {
        File file = new File(path);
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.parse(path), "application/pdf");

        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Intent intent = Intent.createChooser(target, "Open FIle");
        try{
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Toast.makeText(this, "No Apps to read PDF FIle", Toast.LENGTH_SHORT).show();
        }

    }

}
