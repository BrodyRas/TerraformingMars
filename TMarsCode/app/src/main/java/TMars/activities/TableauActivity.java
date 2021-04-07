package TMars.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import TMars.model.Corporation;
import TMars.model.GlobalAssets;
import TMars.model.Player;
import edu.byu.cs.tweeter.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

/**
 * The main activity for the application. Contains tabs for feed, story, following, and followers.
 */
public class TableauActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String CURRENT_PLAYER_KEY = "CurrentUser";
    public static final String CURRENT_CORP_KEY = "CurrentCorp";
    private GlobalAssets ga = GlobalAssets.getInstance(this);
    private Player player;
    private SectionsPagerAdapter spa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableau);
        player = (Player) getIntent().getSerializableExtra(CURRENT_PLAYER_KEY);
        if(player == null) {
            int corpID = (int) getIntent().getSerializableExtra(CURRENT_CORP_KEY);
            player = Player.getInstance(ga.getCorporation(corpID));
        }

        spa = new SectionsPagerAdapter(this, getSupportFragmentManager(), player);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(spa);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        TextView corpName = findViewById(R.id.corp_name);

        corpName.setText(player.getTableau().getCorp().getName());

        Button endGen = findViewById(R.id.generation_button);
        endGen.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        Toast openToast = Toast.makeText(TableauActivity.this, "Producing", Toast.LENGTH_LONG);
        openToast.show();
        player.getTableau().produceResources();
        spa.refresh();
        //somehow get the tableau to refresh.
    }
}