package org.faclab.android.jeudepiste.model;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import java.util.ArrayList;

/**
 * Created by Tatiana Grange on 11/03/2015.
 */
public class QuestionQCM extends Question {

    private ArrayList<Integer> answersId = new ArrayList<>();
    private int realAnswerId;

    public QuestionQCM(int questionId, int clueId, ArrayList<Integer> answersId,int realAnswerId) {
        super(questionId, clueId);

        this.answersId = answersId;
        this.realAnswerId = realAnswerId;
    }

    public ArrayList<Integer> getAnswersId() {
        return answersId;
    }

    public String getAnswerStringForIndex(Context context, int index) {
        return context.getResources().getString(answersId.get(index));
    }

    public int getRealAnswerId() {
        return realAnswerId;
    }

    public String getRealAnswerString(Context context) {
        return context.getResources().getString(realAnswerId);
    }

    public void setRealAnswerId(int realAnswerId) {
        this.realAnswerId = realAnswerId;
    }

    @Override
    public boolean isCorrect(View v) {
        RadioGroup radioGroup = (RadioGroup) v;
        int idRb = radioGroup.getCheckedRadioButtonId();
        RadioButton rbSelected = (RadioButton) radioGroup.findViewById(idRb);
        String text = rbSelected.getText().toString();
        String realAnswerString = getRealAnswerString(v.getContext());

        return text.equals(realAnswerString);
    }
}
