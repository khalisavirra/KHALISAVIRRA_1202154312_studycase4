package com.example.khalisavirra.khalisavirra_1202154312_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class CariGambarActivity extends AppCompatActivity {

    //Deklarasi variable
    private EditText mlinkUrl;
    private Button mImageButton;
    private ImageView mImage;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);

        //Mencari variable yang sudah dideklarasikan berdasarkan id
        mlinkUrl = (EditText)findViewById(R.id.textURL);
        mImageButton = (Button) findViewById(R.id.btn_cari);
        mImage = (ImageView) findViewById(R.id.ImageView);

        //Apabila button klik untuk mencari gambar di klik
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String downloadUrl = mlinkUrl.getText().toString();
                if(downloadUrl.isEmpty()){
                    //Pesan yang muncul bila editText kosong/tidak diisi
                    Toast.makeText(CariGambarActivity.this,"Masukkan URL Image terlebih dahulu",Toast.LENGTH_SHORT).show();
                }else {
                    //Mengeksekusi DownloadImage AsyncTask
                    new ImageDownloader().execute(downloadUrl);
                }
            }
        });
    }

    //Membuat class proses async task untuk menampilkan image
    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        //Menampilkan progress bar pada user interface
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Membuat progress dialog
            mProgress = new ProgressDialog(CariGambarActivity.this);
            //Set judul yang ditampilkan pada progress dialog
            mProgress.setTitle("Search Image");
            //Set pesan yang ditampilkan pada progress dialog
            mProgress.setMessage("Loading...");
            mProgress.setIndeterminate(false);
            //Menampilakan pesan progress
            mProgress.show();
        }

        //Langkah ini digunakan untuk melakukan perhitungan background yang bisa memakan waktu lama.
        //Hasil perhitungan return dan akan diteruskan kembali ke langkah akhir
        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageURL = URL[0];
            Bitmap bitmap = null;
            try {
                //Melakukan download image dari URL yang dimasukkan
                InputStream input = new java.net.URL(imageURL).openStream();
                //Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        //Memanggil pada user interface setelah perhitungan latar belakang selesai
        @Override
        protected void onPostExecute(Bitmap result) {
            // Set bitmap ke dalam imageview
            mImage.setImageBitmap(result);
            //Menghilangkan tampilan progress dialog
            mProgress.dismiss();
        }
    }
}
