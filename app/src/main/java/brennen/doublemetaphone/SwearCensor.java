package brennen.doublemetaphone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 4/2/16.
 */
public class SwearCensor {
    final static private DoubleMetaphone encoder = new DoubleMetaphone();
    static private ArrayList<String> swearList = new ArrayList<>(
            phoneticizeList(SwearList.swears)
    );

    static private int assEncodingNumber = swearList.size();
    static private String assEncoding = encoder.doubleMetaphone("ass");

    //All methods static, never need to create an instance of the censor.
    private SwearCensor(){}

    static public String encode(String toEncode, boolean alternate){
        return encoder.doubleMetaphone(toEncode, alternate);
    }

    static public String encode(String toEncode){
        return encoder.doubleMetaphone(toEncode, false);
    }

    static public String censorString(String toCensor){
        if(!isBlocked(toCensor))
            return toCensor;
        else {
            //Find the substrings that need to be removed.
            //Censor them.
            //return the minPosition and maxPosition of the part that maps to the swear.
            //Create the phonetic
            //Identify the bad phonetic in the phonetic
            String lastEncoding = encode(toCensor);
            String encoding = lastEncoding;
            int index = indexOfInFixSwearInSwearList(encoding);
            int indexOfSwearLeft = 0, indexOfSwearRight = toCensor.length()-1;
            //We can determine if it is a prefix or suffix and thus reduce difficulty of finding.
            //Otherwise, if it is infix, we need to do it the long and arduous way.
            while(encoding.contains(swearList.get(index))){
                indexOfSwearLeft++;
                lastEncoding = encoding;
                encoding = encode(toCensor.substring(indexOfSwearLeft));
            }
            indexOfSwearLeft--;
            encoding = lastEncoding;
            while(encoding.contains(swearList.get(index))){
                indexOfSwearRight--;
                encoding = encode(toCensor.substring(indexOfSwearLeft,indexOfSwearRight));
            }
            indexOfSwearRight++;

            String returnString = replaceSubString(toCensor, indexOfSwearLeft, indexOfSwearRight);
            return censorString(returnString);
        }
    }

    static public boolean isBlocked(String toEncode){
        return isSwear(encode(toEncode, false), encode(toEncode, true));
    }

    static public boolean encodingIsBlocked(String encoding1){
        return isSwear(encoding1);
    }

    static public boolean encodingIsBlocked(String encoding1, String encoding2){
        return isSwear(encoding1, encoding2);
    }

    static private boolean isSwear(String encoding1, String encoding2){
        return isSwear(encoding1)||isSwear(encoding2);
    }

    static private boolean isSwear(String encoding1){
        return isRootSwear(encoding1)||isInFixSwear(encoding1);
    }

    static private boolean isRootSwear(String encoding){
        return swearList.contains(encoding);
    }

    static private boolean isInFixSwear(String encoding){
        for(int i=0; i < swearList.size(); i++){
            if(encoding.contains(swearList.get(i)))
                return true;
        }
        return isAssSwear(encoding);
    }

    static private int indexOfInFixSwearInSwearList(String encoding) {
        return indexOfInFixSwearInSwearList(encoding, 0);
    }

    static private int indexOfInFixSwearInSwearList(String encoding, int beginWith){
        for(int i=beginWith; i < swearList.size(); i++){
            if(encoding.contains(swearList.get(i)))
                return i;
        }
        return indexOfAssSwear(encoding);
    }

    static private boolean isAssSwear(String encoding){
        if(encoding.contains(assEncoding+"A")
                ||encoding.contains(assEncoding+"E")
                ||encoding.contains(assEncoding+"I")
                ||encoding.contains(assEncoding+"O")
                ||encoding.contains(assEncoding+"U"))
            return false;
        else if(encoding.matches("."+assEncoding))
            return false;
        else
            return encoding.contains(assEncoding);

    }

    static private int indexOfAssSwear(String encoding){
        if(!isAssSwear(encoding)) return -1;
        else{
            return assEncodingNumber;
        }
    }

    static private List<String> phoneticizeList(List<String> listToPhoneticize){
        ArrayList<String> returnList = new ArrayList<>();
        for(int i = 0; i < listToPhoneticize.size(); i++) {
            returnList.add(encoder.doubleMetaphone(listToPhoneticize.get(i)));
        }
        return returnList;
    }

    //Note: endIndex is the start of the last part of the word that isn't censored.
    static private String replaceSubString(String toReplace, int startIndex, int endIndex){
        if(endIndex+1>=toReplace.length())
            return (toReplace.substring(0, startIndex) + "**");
        return (toReplace.substring(0, startIndex) +
                "**"
                + toReplace.substring(endIndex));
    }


/*Handling Swearing:
 *We choose * to have no phonetic sound, enabling deletion and blocking.
 *We have three categories of swears:
 *  Category            e.g.            not e.g.
 *  Root Swears         Fuck            Fock
 *  In-Fix Swears       OFuckingBama    Grasses
 *  Composite Swears    Bullshit        --------
 *
 * First, we handle root swears as the simple case.
 *      Answer: Direct Dictionary Block
 * Then we handle infix swears. This lets us condense BullFuckingshit to Bull**shit
 * Then we handle composite swears, which would take the Bull**shit to ****.
 */

/*Handling Infix Swearing:
 * Clearly
 *  OFuckingBama -> O**bama
 * But
 *  Grasses -/> Gr**es
 * The difference is that "ass" is used innocently, fuck is not.
 * We have found no examples of any other word that is used innocently.
 * Hence, we can look specifically at ass as a problem that is the exception,
 * not the rule.
 * Note: associate, assign, assembly, assassin, assail, assault, assay are words that should not be blocked.
 * So ass with something in front of it or ass with a vowel following it are okay.
 * Asshole is not.
 *
 * We specify ass (and all other swears) as our blacklist.
 * #ass is whitelisted, where # is a wildcard.
 * ass(vowel) is whitelisted, where (vowel) is a vowel wildcard.
 * asshole is blacklisted.
 */
}

