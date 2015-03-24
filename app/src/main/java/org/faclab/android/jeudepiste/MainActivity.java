package org.faclab.android.jeudepiste;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.Time;
import android.view.View;
import android.widget.TextView;

import com.temboo.Library.Twitter.Tweets.StatusesUpdate;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

import org.faclab.android.jeudepiste.model.Question;
import org.faclab.android.jeudepiste.model.QuestionOneAnswer;
import org.faclab.android.jeudepiste.model.QuestionQCM;
import org.faclab.android.jeudepiste.util.NFCActivity;
import org.faclab.android.jeudepiste.util.Tools;
import org.faclab.android.jeudepiste.view.QuestionView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends NFCActivity implements View.OnClickListener {

    QuestionOneAnswer question1;
    QuestionOneAnswer question2;
    QuestionQCM question3;
    QuestionOneAnswer question4;
    QuestionQCM question5;
    QuestionQCM question6;
    QuestionOneAnswer question7;
    QuestionOneAnswer question8;
    QuestionOneAnswer question9;

    ArrayList<Question> questions;
    int indexQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        questions = new ArrayList<>();

        question1 = new QuestionOneAnswer(R.string.question1,R.string.clue1,R.string.answer1);
        question2 = new QuestionOneAnswer(R.string.question2,R.string.clue2,R.string.answer2);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.string.answer3_1);
        list.add(R.string.answer3_2);
        list.add(R.string.answer3_3);
        list.add(R.string.answer3_4);
        list.add(R.string.answer3_5);
        question3 = new QuestionQCM(R.string.question3, R.string.clue3, list, R.string.answer3_5);

        question4 = new QuestionOneAnswer(R.string.question4,R.string.clue4,R.string.answer4);

        ArrayList<Integer> list5 = new ArrayList<>();
        list5.add(R.string.answer5_1);
        list5.add(R.string.answer5_2);
        list5.add(R.string.answer5_3);
        list5.add(R.string.answer5_4);
        list5.add(R.string.answer5_5);
        question5 = new QuestionQCM(R.string.question5, R.string.clue5, list5, R.string.answer5_1);

        ArrayList<Integer> list6 = new ArrayList<>();
        list6.add(R.string.answer6_1);
        list6.add(R.string.answer6_2);
        list6.add(R.string.answer6_3);
        list6.add(R.string.answer6_4);
        list6.add(R.string.answer6_5);
        question6 = new QuestionQCM(R.string.question6, R.string.clue6, list6, R.string.answer6_1);

        question7 = new QuestionOneAnswer(R.string.question7,R.string.clue7,R.string.answer7);
        question8 = new QuestionOneAnswer(R.string.question8,R.string.clue8,R.string.answer8);
        question9 = new QuestionOneAnswer(R.string.question9,R.string.clue9,R.string.answer9);

        QuestionView view = new QuestionView(getBaseContext(),question1,this);
        indexQuestion = 0;
        setContentView(view);


        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onClick(View v) {
        Question question = questions.get(indexQuestion);
        boolean result = question.isCorrect(findViewById(R.id.answer));

        if(result){
            if(indexQuestion == questions.size() - 1){
                setContentView(R.layout.win);
                findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        TembooSession session = null;
                        try {
                            session = new TembooSession("maktub", "myFirstApp", "68357e3c2b544674b4265cc1b5ae41fa");
                            StatusesUpdate statusesUpdateChoreo = new StatusesUpdate(session);
                            StatusesUpdate.StatusesUpdateInputSet statusesUpdateInputs = statusesUpdateChoreo.newInputSet();
                            statusesUpdateInputs.setCredential("Documathon");


                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy à HH:mm:ss");
                            String formattedDate = df.format(c.getTime());


                            statusesUpdateInputs.set_StatusUpdate("Quelqu'un a réussis le défit débutant du Faclab le " + formattedDate);
                            StatusesUpdate.StatusesUpdateResultSet statusesUpdateResults = statusesUpdateChoreo.execute(statusesUpdateInputs);

                            findViewById(R.id.tv_ok).setAlpha(1f);
                            findViewById(R.id.button).setEnabled(false);
                            findViewById(R.id.button).setBackgroundColor(Color.LTGRAY);
                        } catch (TembooException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }else {
                TextView clue = (TextView) findViewById(R.id.tv_clue);
                clue.setAlpha(1f);
            }
        }else{
            Tools.toast(getBaseContext(),"C'est pas ça");
        }
    }

    @Override
    public void onEndRead(String result) {
        QuestionView view = null;

        int resultInInt = Integer.parseInt(result);

        if(resultInInt > 0 && resultInInt <= questions.size()) {
                Question question = questions.get(resultInInt - 1);
                view = new QuestionView(getBaseContext(), question, this);
                indexQuestion = resultInInt - 1;

        }

        if(view != null)
            setContentView(view);
    }

    @Override
    public void onError(Exception error) {

    }

    @Override
    public void onEndWrite() {}
}
