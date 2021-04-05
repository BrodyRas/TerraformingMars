package TMars.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import edu.byu.cs.tweeter.R;

/**
 * Contains the minimum UI required to allow the user to login with a hard-coded user. Most or all
 * of this should be replaced when the back-end is implemented.
 */
public class SetupActivity extends AppCompatActivity {

    private static final String LOG_TAG = "SetupActivity";
    private Toast openToast;
    private int corporation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Spinner selector = (Spinner) findViewById(R.id.corporation_select);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.corporations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selector.setAdapter(adapter);

        selector.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        corporation = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        corporation = 0;
                    }
                }
        );

        Button startGameButton = findViewById(R.id.startButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String corp_name = (String) selector.getItemAtPosition(corporation);
                openToast = Toast.makeText(SetupActivity.this, "Sending "+ corp_name + " to Mars", Toast.LENGTH_LONG);
                openToast.show();
                Intent intent = new Intent(SetupActivity.this, TableauActivity.class);

                intent.putExtra(TableauActivity.CURRENT_PLAYER_KEY, (String) null);
                intent.putExtra(TableauActivity.CURRENT_CORP_KEY, corporation);

                openToast.cancel();
                startActivity(intent);
            }
        });
    }
}

