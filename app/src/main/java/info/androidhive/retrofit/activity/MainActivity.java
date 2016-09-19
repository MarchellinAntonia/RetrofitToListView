package info.androidhive.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import info.androidhive.retrofit.R;
import info.androidhive.retrofit.adapter.BanksAdapter;
import info.androidhive.retrofit.model.Bank;
import info.androidhive.retrofit.model.BanksResponse;
import info.androidhive.retrofit.model.DataBank;
import info.androidhive.retrofit.rest.ApiClient;
import info.androidhive.retrofit.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView data_bank_code, data_bank_name, kodeAPI, statusAPI ;
    List<DataBank> dataBanks;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data_bank_code = (TextView) findViewById(R.id.data_bank_code);
        data_bank_name = (TextView) findViewById(R.id.data_bank_name);
//        kodeAPI = (TextView) findViewById(R.id.kodeAPI);
//        statusAPI = (TextView) findViewById(R.id.statusAPI);


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Bank> call = apiService.getBankDetails();
        call.enqueue(new Callback<Bank>() {
            @Override
            public void onResponse(Call<Bank> call, Response<Bank> response) {
                recyclerView.setAdapter(new BanksAdapter(response.body().getDataBank(), R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<Bank> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
