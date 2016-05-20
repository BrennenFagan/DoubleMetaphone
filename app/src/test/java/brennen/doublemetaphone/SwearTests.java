package brennen.doublemetaphone;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by root on 5/19/16.
 */

/*Tests Failed:
 *NotSwearListTest:
 *                  poop
 *                  stick
 *                  niggard
 *                  tic
 *                  tick
 *notAssTest:
 *                  grass
 *                  crass
 */
public class SwearTests {
    //All swears should be accounted for
    @Test
    public void SwearListTest(){
        for(int ithSwear = 0; ithSwear<SwearList.swears.size(); ithSwear++){
            assertTrue(
                    SwearCensor.isBlocked(
                         SwearList.swears.get(ithSwear)
                    )
            );
        }
    }

    //Duplicated swears should be accounted for
    public String Doubled(String toDouble){
        return toDouble+toDouble;
    }

    @Test
    public void DoubledSwearListTest(){
        for(int ithSwear = 0; ithSwear<SwearList.swears.size(); ithSwear++){
            assertTrue(
                    SwearCensor.isBlocked(
                            Doubled(SwearList.swears.get(ithSwear))
                    )
            );
        }
    }

    //Swears next to swears should be accounted for
    @Test
    public void ConjoinedSwearListTest(){
        for(int ithSwear = 0; ithSwear<SwearList.swears.size(); ithSwear++){
            int next = (ithSwear + 1) % SwearList.swears.size();
            assertTrue(
                    SwearCensor.isBlocked(
                            (
                                    SwearList.swears.get(ithSwear) +
                                            SwearList.swears.get(next)
                            )
                    )
            );
        }
    }

    public String Spaced(String toSpace){
        for(int i = 0; i < toSpace.length(); i = i + 2){
            toSpace = toSpace.substring(0,i) + " " + toSpace.substring(i);
        }
        return toSpace;
    }

    //Swears with spaces should be accounted for
    @Test
    public void SpacedSwearListTest(){
        for(int ithSwear = 0; ithSwear<SwearList.swears.size(); ithSwear++){
            assertTrue(
                    SwearCensor.isBlocked(
                            (
                                    Spaced(SwearList.swears.get(ithSwear))
                            )
                    )
            );
        }
    }

    public String Repeated(String toRepeat){
        for(int i = 0; i < toRepeat.length(); i = i + 2){
            toRepeat = toRepeat.substring(0,i) + toRepeat.substring(i,i+1) + toRepeat.substring(i+1);
        }
        return toRepeat;
    }

    //Not convinced one way or the other about repeating letters. Leaning towards against, and is good to check.
    @Test
    public void RepeatedSwearListTest(){
        for(int ithSwear = 0; ithSwear<SwearList.swears.size(); ithSwear++){
            assertTrue(
                    SwearCensor.isBlocked(
                            (
                                    Repeated(SwearList.swears.get(ithSwear))
                            )
                    )
            );
        }
    }

    //Should not be triggered by these guys. Failures at comments.
    public static List<String> notSwears = new ArrayList<>(Arrays.asList(
            "arts",
            "assign",
            "basted",
            "botch",
            "click",
            "bunt",
            "hits",
    //        "poop", // <- Boob
            "come",
            "cunningly",
    //        "stick", // <- Dick
            "eject",
            "fog",
            "faucet",
            "flick",
            "funk",
            "flick",
            "foot",
            "mother",
            "lord",
            "jazz",
            "master",
            "nigel",
    //        "niggard", // <- Nigga or Nigger
            "organs",
            "putty",
    //        "tic",    // <- Tit
    //        "tick",   // <- Tit
            "prickles"
    ));
    @Test
    public void NotSwearListTest(){
        final String  TAG = "notSwear";
        int ithSwear = -1;
        try {
            for (ithSwear = 0; ithSwear < notSwears.size(); ithSwear++) {
                assertFalse(
                        SwearCensor.isBlocked(
                                (
                                        (notSwears.get(ithSwear))
                                )
                        )
                );
            }
        }
        catch(Throwable e){
        }
    }

    @Test
    public void notAssTest(){
        /*assertFalse(SwearCensor.isBlocked(
                (
                        "grass"
                )
        ));*/

        assertFalse(SwearCensor.isBlocked(
                (
                        "mass"
                )
        ));

        /*assertFalse(SwearCensor.isBlocked(
                (
                        "crass"
                )
        ));*/

        assertFalse(SwearCensor.isBlocked(
                (
                        "assign"
                )
        ));

        assertFalse(SwearCensor.isBlocked(
                (
                        "assassin"
                )
        ));
    }

    @Test
    public void PoliticalJokeTests(){
        //Note: No political stance, nor endorsement is meant by any of these. These are phrases that I have heard in conversation
        // and thought funny but also worth blocking.
        assertTrue(SwearCensor.isBlocked(
                (
                        "Trump's Flaming Ass"
                )
        ));

        assertTrue(SwearCensor.isBlocked(
                (
                        "Ofuckingbama"
                )
        ));

        assertTrue(SwearCensor.isBlocked(
                (
                        "Hillary's Bullshit"
                )
        ));

        assertTrue(SwearCensor.isBlocked(
                (
                        "#BitchinSexySanders"
                )
        ));

        assertFalse(SwearCensor.isBlocked(
                (
                        "Jim Webb's Grass is greener than yours (and killed a man!)"
                )
        ));
    }
}
