package org.faclab.android.jeudepiste.model;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Tatiana Grange on 11/03/2015.
 */
public class QuestionOneAnswer extends Question {

    private int answerId;

    public QuestionOneAnswer(int questionId, int clueId, int answerId) {
        super(questionId, clueId);

        this.answerId = answerId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public String getAnswerString(Context context) {
        return context.getResources().getString(answerId);
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean isCorrect(View v) {
        EditText input = (EditText) v;
        String text = input.getText().toString();
        String answerString = getAnswerString(v.getContext());

        return text.equals(answerString);
    }
}
