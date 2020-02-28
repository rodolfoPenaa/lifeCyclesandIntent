package com.lifeCyclesandIntent.myZapp.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lifeCyclesandIntent.myZapp.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import aPi.API;
import aPi.RetrofitClient;
import fragments.BlankFragment;
import pojos.Question;
import pojos.QuestionsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    private TextView questionView, categoryView, difficultyView;
    private RadioGroup groupAnswerView;
    private RadioButton answerOne, answerTwo;
    private MainViewModel mViewModel;
    private Button buttoninfirstfragment;


    public static MainFragment newInstance(
            String question,
            String category,
            String difficulty) {
        MainFragment fragmentOne = new MainFragment();
        Bundle arguments = new Bundle();
        arguments.putString("QUESTION", question);
        arguments.putString("CATEGORY", category);
        arguments.putString("DIFFICULTY", difficulty);
        fragmentOne.setArguments(arguments);
        return fragmentOne;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);

        final String question = Objects.requireNonNull(getArguments().getString("QUESTION"));
        final String category = Objects.requireNonNull(getArguments().getString("CATEGORY"));
        final String difficulty = Objects.requireNonNull(getArguments().getString("DIFFICULTY"));

        ImageView imageView = view.findViewById(R.id.logo_desafioLA);

        Picasso.get().load("https://desafiolatam.com/assets/home/logo-academia-bla-790873cdf66b0e681dfbe640ace8a602f5330bec301c409744c358330e823ae3.png").into(imageView);

        initializeViews(view);
        questionView = view.findViewById(R.id.question);
        questionView.setText(question);
        categoryView = view.findViewById(R.id.category_Value);
        categoryView.setText(category);
        difficultyView = view.findViewById(R.id.diffuclty_Value);
        difficultyView.setText(difficulty);
        answerOne = view.findViewById(R.id.radioFirstOption);
        answerTwo = view.findViewById(R.id.radioSecondOption);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buttoninfirstfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "NewQuestion", Toast.LENGTH_SHORT).show();

                API service = RetrofitClient.getRetrofitInstance().create(API.class);
                Call <QuestionsList> call = service.getQuestion();
                call.enqueue(new Callback<QuestionsList>() {
                    @Override
                    public void onResponse(Call<QuestionsList> call, Response<QuestionsList> response) {
                        QuestionsList questions = response.body();
                        if (response != null) {
                            Question question;
                            question = questions.getResults().get(0);

                            BlankFragment blankFragment = BlankFragment.newInstance(
                                    question.getQuestion(),
                                    question.getCategory(),
                                    question.getDifficulty());

                                    changeFragment(blankFragment);
                        };

                    }

                    @Override
                    public void onFailure(Call<QuestionsList> call, Throwable t) {

                    }
                });


            }


        });

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }


    private void initializeViews(View view) {

        questionView = view.findViewById(R.id.question);
        categoryView = view.findViewById(R.id.category_Value);
        difficultyView = view.findViewById(R.id.diffuclty_Value);
        groupAnswerView = view.findViewById(R.id.radioGrupoRespuestas);
        buttoninfirstfragment = view.findViewById(R.id.btnAnswerConsult);
    }

    private void changeFragment(BlankFragment blankFragment) {

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, blankFragment, "SECONDFRAGMENT")
                .addToBackStack("SECONDFRAGMENT")
                .commit();
    }
}



