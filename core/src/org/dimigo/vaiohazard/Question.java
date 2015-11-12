package org.dimigo.vaiohazard;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by YuTack on 2015-11-11.
 */
public class Question {
    public String question;
    public List<String> possibleAnswers;

    public Question() {
        possibleAnswers = new ArrayList<String>();
    }

    public Question(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}
