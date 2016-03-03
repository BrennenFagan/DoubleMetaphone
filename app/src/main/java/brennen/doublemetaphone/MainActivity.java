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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DoubleMetaphone encoder=  new DoubleMetaphone();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView output = (TextView) findViewById(R.id.output);
                TextView output2 = (TextView) findViewById(R.id.output2);
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
}
