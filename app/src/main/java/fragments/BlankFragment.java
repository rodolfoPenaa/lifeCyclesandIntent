package fragments;

import android.os.Bundle;

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
import com.lifeCyclesandIntent.myZapp.ui.main.MainFragment;
import com.lifeCyclesandIntent.myZapp.ui.main.MainViewModel;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import aPi.API;
import aPi.RetrofitClient;
import pojos.Question;
import pojos.QuestionsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlankFragment extends Fragment {

    private TextView questionView, categoryView, difficultyView;
    private RadioGroup groupAnswerView;
    private RadioButton answerOne, answerTwo;
    private MainViewModel mViewModel;
    private Button buttonSecondfragment;

    public BlankFragment() {

    }

    public static BlankFragment newInstance(String question,
                                            String category,
                                            String difficulty) {
        BlankFragment fragmenttwo = new BlankFragment();
        Bundle arguments = new Bundle();
        arguments.putString("QUESTION", question);
        arguments.putString("CATEGORY", category);
        arguments.putString("DIFFICULTY", difficulty);
        fragmenttwo.setArguments(arguments);
        return fragmenttwo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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



    private void initializeViews(View view) {

        questionView = view.findViewById(R.id.question);
        categoryView = view.findViewById(R.id.category_Value);
        difficultyView = view.findViewById(R.id.diffuclty_Value);
        groupAnswerView = view.findViewById(R.id.radioGrupoRespuestas);
        buttonSecondfragment = view.findViewById(R.id.btnsecondFragment);
    }

}
