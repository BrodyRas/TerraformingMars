package TMars.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.byu.cs.tweeter.R;

/**
 * Contains the minimum UI required to allow the user to login with a hard-coded user. Most or all
 * of this should be replaced when the back-end is implemented.
 */
public class OpenActivity extends AppCompatActivity {

    private static final String LOG_TAG = "OpenActivity";
    private Toast openToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        Button startGameButton = findViewById(R.id.NewGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openToast = Toast.makeText(OpenActivity.this, "Launching", Toast.LENGTH_LONG);
                openToast.show();

                Intent intent = new Intent(OpenActivity.this, SetupActivity.class);

                openToast.cancel();
                startActivity(intent);
            }
        });
    }
}

