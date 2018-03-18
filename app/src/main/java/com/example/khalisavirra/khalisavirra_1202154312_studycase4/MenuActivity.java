package com.example.khalisavirra.khalisavirra_1202154312_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    //deklarasi variable
    private Button listnama;
    private Button carigambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Mencari variable yang sudah dideklarasikan berdasarkan id
        listnama = (Button)findViewById(R.id.listNama);
        carigambar = (Button)findViewById(R.id.cariGambar);

        //Apabila button List Nama Mahasiswa di klik
        listnama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pesan yang muncul bila button List Nama Mahasiswa diklik
                Toast.makeText(MenuActivity.this,"Menu List Nama Mahasiswa Dipilih",Toast.LENGTH_SHORT).show();
                //Setelah menampilkan Toast akan intent ke aktivitas selanjutnya
                Intent btn1 = new Intent(MenuActivity.this,ListNamaActivity.class);
                //Start aktivitas berdasarkan intent btn1 yang sudah dideklarasikan
                startActivity(btn1);
            }
        });

        //Apabila button Cari Gambar di klik
        carigambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pesan yang muncul bila button Cari Gambar diklik
                Toast.makeText(MenuActivity.this,"Menu Cari Gambar Dipilih",Toast.LENGTH_SHORT).show();
                //Setelah menampilkan Toast akan intent ke aktivitas selanjutnya
                Intent btn2 = new Intent(MenuActivity.this,CariGambarActivity.class);
                //Start aktivitas berdasarkan intent btn2 yang sudah dideklarasikan
                startActivity(btn2);
            }
        });
    }
}
