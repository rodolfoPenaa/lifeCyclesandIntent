package pojos;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuestionsList {
@SerializedName("response_code")
    private int responseCode;
    private ArrayList<Question>results;
    public int getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    public ArrayList<Question> getResults() {
        return results;
    }
    public void setResults(ArrayList<Question> results) {
        this.results = results;
    }
}
