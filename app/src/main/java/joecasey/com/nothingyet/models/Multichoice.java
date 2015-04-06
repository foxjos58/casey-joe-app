package joecasey.com.nothingyet.models;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Joe F on 3/18/2015.
 */
public class Multichoice {
    private int resId;
    private List<String> choices;
    private String answer;

    public Multichoice(int resId, String[] choices, String answer) {
        this.resId = resId;
        this.choices = Arrays.asList(choices);
        this.answer = answer;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
