package org.faclab.android.jeudepiste.model;

import android.content.Context;
import android.view.View;

/**
 * Created by Tatiana Grange on 11/03/2015.
 */
public abstract class Question {

    /* **********************
     * 		Attributes		*
     * ********************** */
    private int questionId;
    private int clueId;


    /* **********************
     * 		Constructor		*
     * ********************** */
    public Question(int questionId, int clueId) {
        this.questionId = questionId;
        this.clueId = clueId;
    }


    /* **********************
     * 		Accessors		*
     * ********************** */
    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionString(Context context) {
        return context.getResources().getString(questionId);
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getClueId() {
        return clueId;
    }

    public String getClueString(Context context) {
        return context.getResources().getString(clueId);
    }

    public void setClueId(int clueId) {
        this.clueId = clueId;
    }


    /* ******************************
     * 		Abstract Methods		*
     * **************************** */

    public abstract boolean isCorrect(View v);

 }
