package com.lifeCyclesandIntent.myZapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lifeCyclesandIntent.myZapp.ui.main.MainFragment;

import aPi.API;
import aPi.RetrofitClient;
import pojos.Question;
import pojos.QuestionsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG ="onCreate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        API service = RetrofitClient.getRetrofitInstance().create(API.class); //instancia de clase API
        Call <QuestionsList> call = service.getQuestion();
        call.enqueue(new Callback<QuestionsList>() {
            @Override
            public void onResponse(Call<QuestionsList> call, Response<QuestionsList> response) {
                QuestionsList questions = response.body();
                if (response != null) {
                        Question question;
                        question = questions.getResults().get(0);

                        MainFragment mainFragment = MainFragment.newInstance(
                                question.getQuestion(),
                                question.getCategory(),
                                question.getDifficulty()
                    );
                    initializeFragment(mainFragment);
                    }
                }

            @Override
            public void onFailure(Call<QuestionsList> call, Throwable t) {
                Log.e("ERRORes", t.toString());
                Toast.makeText(getApplicationContext(), "Error: no pudimos recuperar los datos de opentdb",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void initializeFragment(MainFragment mainFragment){
                 getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container,mainFragment, "VIEWFRAGMENT")
                .commit();
   };
}

