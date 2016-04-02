package brennen.doublemetaphone;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    final private DoubleMetaphone encoder = new DoubleMetaphone();
    static private ArrayList<String> swearList;
    static private String assEncoding;

    private void populateSwearList(){
        //Populating based off of American sensibilities + https://gist.github.com/jamiew/1112488
        //Using this method, we can populate based off of the encodings strictly.
        swearList = new ArrayList<>(
                Arrays.asList(
                        encoder.doubleMetaphone("arse"),
                        encoder.doubleMetaphone("asshole"),
                        encoder.doubleMetaphone("bastard"),
                        encoder.doubleMetaphone("bitch"),
                        encoder.doubleMetaphone("cock"),
                        encoder.doubleMetaphone("cunt"),
                        encoder.doubleMetaphone("fuck"),
                        encoder.doubleMetaphone("motherfucker"),
                        encoder.doubleMetaphone("shit"),
                        encoder.doubleMetaphone("boob"),
                        encoder.doubleMetaphone("cum"),
                        encoder.doubleMetaphone("cunnilingus"),
                        encoder.doubleMetaphone("dick"),
                        encoder.doubleMetaphone("ejaculate"),
                        encoder.doubleMetaphone("fag"),
                        encoder.doubleMetaphone("faggot"),
                        encoder.doubleMetaphone("fcuk"),
                        encoder.doubleMetaphone("fook"),
                        encoder.doubleMetaphone("gaylord"),
                        encoder.doubleMetaphone("jizz"),
                        encoder.doubleMetaphone("masturbate"),
                        encoder.doubleMetaphone("nigger"),
                        encoder.doubleMetaphone("nigga"),
                        encoder.doubleMetaphone("orgasm"),
                        encoder.doubleMetaphone("pussy"),
                        encoder.doubleMetaphone("tit"),
                        encoder.doubleMetaphone("titty"),
                        encoder.doubleMetaphone("titties")
                )
        );
        assEncoding = encoder.doubleMetaphone("ass");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        populateSwearList();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView output = (TextView) findViewById(R.id.output);
                TextView output2 = (TextView) findViewById(R.id.output2);
                TextView wouldBeBlocked = (TextView) findViewById(R.id.wouldBeBlocked);
                EditText input = (EditText) findViewById(R.id.input);

                String[] words = input.getText().toString().split(" ");
                String intermediate="";
                String[] words2 = input.getText().toString().split(" ");
                String intermediate2="";
                for(int i=0; i<words.length; i++) {
                    words[i] = encoder.doubleMetaphone(words[i]);
                    intermediate += words[i] + " ";
                    words2[i] = encoder.doubleMetaphone(words2[i],true);
                    intermediate2 += words2[i] + " ";
                }

                output.setText(intermediate.trim());
                output2.setText(intermediate2.trim());

                if(blocked(intermediate, intermediate2))
                    wouldBeBlocked.setText("Very Bad!");
                else
                    wouldBeBlocked.setText("All Good!");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean blocked(String encoding1, String encoding2){
        return isRootSwear(encoding1)||isRootSwear(encoding2)
                ||isInFixSwear(encoding1)||isInFixSwear(encoding2);
    }

    private boolean isRootSwear(String encoding){
        return swearList.contains(encoding);
    }

    private boolean isInFixSwear(String encoding){
        for(int i=0; i < swearList.size(); i++){
            if(encoding.contains(swearList.get(i)))
                return true;
        }
        return isAssSwear(encoding);
    }

    private boolean isAssSwear(String encoding){
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