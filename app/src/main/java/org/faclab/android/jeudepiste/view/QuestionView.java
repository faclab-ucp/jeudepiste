package org.faclab.android.jeudepiste.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import org.faclab.android.jeudepiste.R;
import org.faclab.android.jeudepiste.model.Question;
import org.faclab.android.jeudepiste.model.QuestionOneAnswer;
import org.faclab.android.jeudepiste.model.QuestionQCM;

/**
 * Created by Tatiana Grange on 11/03/2015.
 */
public class QuestionView extends LinearLayout {
    /* **********************
     * 		Attributes		*
     * ********************** */
    private Question question;

    /* **********************
     * 		Constructor		*
     * ********************** */
    public QuestionView(Context context, Question question, OnClickListener listener) {
        super(context);
        this.question = question;

        View.inflate(context, question instanceof QuestionOneAnswer ? R.layout.question_one_answer : R.layout.question_qcm, this);

        ((TextView)findViewById(R.id.tv_question)).setText(question.getQuestionString(context));
        ((TextView)findViewById(R.id.tv_clue)).setText(question.getClueString(context));

        findViewById(R.id.btn_ok).setOnClickListener(listener);

        if(question instanceof QuestionQCM){
            ((RadioButton)findViewById(R.id.answer1)).setText(((QuestionQCM)question).getAnswerStringForIndex(context,0));
            ((RadioButton)findViewById(R.id.answer2)).setText(((QuestionQCM)question).getAnswerStringForIndex(context,1));
            ((RadioButton)findViewById(R.id.answer3)).setText(((QuestionQCM)question).getAnswerStringForIndex(context,2));
            ((RadioButton)findViewById(R.id.answer4)).setText(((QuestionQCM)question).getAnswerStringForIndex(context,3));
            ((RadioButton)findViewById(R.id.answer5)).setText(((QuestionQCM)question).getAnswerStringForIndex(context,4));
        }

    }


}
