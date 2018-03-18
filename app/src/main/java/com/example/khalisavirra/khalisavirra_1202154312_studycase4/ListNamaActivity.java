package com.example.khalisavirra.khalisavirra_1202154312_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListNamaActivity extends AppCompatActivity {

    //Deklarasi variable
    private Button mStartAsyncTask;
    private ProgressBar mProgressBar;
    private ListView mListView;

    //Mendefinisikan dan menyimpan string daftar nama mahasiswa ke dalam variable array nama_mahasiswa
    private String [] nama_mahasiswa= {
            "Khalisa", "Virra", "Citra", "Dewi", "Riska",
            "Tiyas", "Laysha", "Khairiah", "Euis", "Rohayati",
            "Khairul", "Akhyar", "Lutfi", "Ahabba", "Dini", "Virginia",
            "Kiky", "Debora", "Anis", "Nanda", "Astuti"
    };

    //Deklarasi variable untuk menambahkan item ke dalam listview
    private AddItemToListView mAddItemToListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama);

        //Mencari variable yang sudah dideklarasikan berdasarkan id
        mStartAsyncTask = (Button) findViewById(R.id.btn_AsyncTask);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mListView = (ListView) findViewById(R.id.listview);

        //Mendefiniskan progressbar terlihat ketika aplikasi berjalan
        mListView.setVisibility(View.GONE);

        //Adapter yang digunakan untuk menyimpan data nama mahasiswa berdasarkan array yang telah didefinisikan sebelumnya
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //Memulai async task ketika btn_AsyncTask diklik
        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
            }
        });
    }

    //Membuat class proses async task untuk menambahkan item ke dalam listview
    public class AddItemToListView  extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListNamaActivity.this);

        //Menampilkan progress bar pada user interface
        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            //Untuk menampilkan progress ketika btn_AsyncTask di klik
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //Set pesan yang ditampilkan pada progress dialog
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setProgress(0);

            //Menghandle tombol cancel pada dialog
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    //Menampilkan progress bar pada layar dialog setelah diklik cancel
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            //Menampilkan progress dialog
            mProgressDialog.show();
        }
        //Langkah ini digunakan untuk melakukan perhitungan background yang bisa memakan waktu lama.
        //Hasil perhitungan return dan akan diteruskan kembali ke langkah akhir
        @Override
        protected Void doInBackground(Void... params) {
            for (String item : nama_mahasiswa){
                publishProgress(item);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }

        //Metode ini digunakan untuk menampilkan kegiatan setelahnya di dalam user interface
        //Ini bisa digunakan untuk menghidupkan sebuah progress bar dalam field teks.
        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            Integer current_status = (int)((counter/(float)nama_mahasiswa.length)*100);
            mProgressBar.setProgress(current_status);

            //Set tampilan progress pada dialog progress
            mProgressDialog.setProgress(current_status);

            //Set message berupa persentase progress pada dialog progress
            mProgressDialog.setMessage(String.valueOf(current_status+"%"));
            counter++;
        }

        //Memanggil pada user interface setelah perhitungan latar belakang selesai
        @Override
        protected void onPostExecute(Void Void) {
            //Menyembunyikan progressbar
            mProgressBar.setVisibility(View.GONE);

            //Menghilangkan tampilan progress dialog
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }
    }
}
