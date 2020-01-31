package com.htboiz.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private static String WEATHER_MAP_API_KEY = "my_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText loc = findViewById(R.id.location);

        Button button = findViewById(R.id.search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String location = loc.getText().toString();

                String url = getUrl(location);

                fetchAsync(url);

                // NetworkOnMainException
//                try {
//                    String json = fetch(url);
//                } catch (IOException ex) {
//
//                }


            }
        });
    }

    void displayWeather(String json) {
        Log.v(TAG, "displayWeather " + json);
        if (json != null) {
            WeatherResponse weather = getGson().fromJson(json, new TypeToken<WeatherResponse>() {
            }.getType());
            Log.i(TAG, weather.toString());
        } else {
            Log.w(TAG, "Json was null");
        }
    }

    String getUrl(String location) {
        String url = "http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=" + WEATHER_MAP_API_KEY;
        return url;
    }

    void fetchAsync(String url) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "fetch failed " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    String json = response.body().string();
                    displayWeather(json);
                } else {
                    Log.e(TAG, "fetch failed " + response.code());
                }
            }
        });

    }


//    String fetch(String url) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        try (Response response = client.newCall(request).execute()) {
//            return response.body().string();
//        }
//    }

    Gson getGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

}
