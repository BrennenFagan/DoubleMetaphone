package brennen.doublemetaphone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 4/27/16.
 */
public class SwearList {
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
                "...."//Four wildcards, automatically censoring any four character strings of characters we don't recognize.
        ));
}
