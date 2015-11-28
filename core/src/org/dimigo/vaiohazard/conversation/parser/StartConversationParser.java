package org.dimigo.vaiohazard.conversation.parser;

import org.dimigo.library.DialogGenerator;
import org.dimigo.vaiohazard.Object.PixelizedDialog;

import java.util.List;
import java.util.Random;

/**
 * Created by juwoong on 15. 11. 26..
 */
public class StartConversationParser extends ConversationParser {

    private DialogGenerator generator;
    private static final String uri = "Start";

    public StartConversationParser(DialogGenerator generator) {
        this.generator = generator;
    }

    public PixelizedDialog getGeneratedDialog(String name) {
        List<ConversationFormat> list = get(uri);
        PixelizedDialog result;
        System.out.println(list.size());
        ConversationFormat selected = list.get((new Random()).nextInt(list.size()));

        if(selected.useName == true) result=generator.getDialog(name, String.format(selected.question, name));
        else result=generator.getDialog(name, selected.question);

        return result;
    }
}
