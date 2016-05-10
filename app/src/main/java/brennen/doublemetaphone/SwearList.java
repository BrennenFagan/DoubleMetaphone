package brennen.doublemetaphone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 4/27/16.
 */
public class SwearList {
        private static final char emojiChar1 = '\uD83D';
        private static final char letterChar1 = '\uD83C';
        private static final char eggplant = '\uDF46';
        public static List<String> swears = new ArrayList<>(Arrays.asList(
                "arse",
                "asshole",
                "bastard",
                "bitch",
                "cock",
                "cunt",
                "shit",
                "boob",
                "cum",
                "cunnilingus",
                "dick",
                "ejaculate",
                "fag",
                "faggot",
                "fuck",
                "fcuk",
                "fvck",
                "fook",
                "motherfucker",
                "gaylord",
                "jizz",
                "masturbate",
                "nigger",
                "nigga",
                "orgasm",
                "pussy",
                "tit",
                "titty",
                "titties",
                Character.toString(letterChar1)+Character.toString(eggplant), //Eggplant is apparently some dick reference?
                Character.toString(emojiChar1) + Character.toString('\uDC49') + //Pointer Finger
                Character.toString(emojiChar1) + Character.toString('\uDC4C')  //and Okay
                //"...."//Four wildcards, automatically censoring any four character strings of characters we don't recognize.
        ));
}
