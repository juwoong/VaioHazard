package org.dimigo.vaiohazard.Interface;

import org.dimigo.vaiohazard.Answer;
import org.dimigo.vaiohazard.Question;

/**
 * Created by YuTack on 2015-11-12.
 */
public interface IAskable {
    Question ask();
    void hear(int answer);
}
