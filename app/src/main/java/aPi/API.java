package aPi;
import pojos.QuestionsList;
import retrofit2.Call;
import retrofit2.http.GET;
public interface API {
    @GET("api.php?amount=1&category=18&difficulty=medium&type=boolean")
    Call<QuestionsList>getQuestion();
    Call<QuestionsList>getQuestiontwo();
}
