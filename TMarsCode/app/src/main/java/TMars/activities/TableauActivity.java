package TMars.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import TMars.model.Corporation;
import TMars.model.Player;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.view.util.ImageUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

/**
 * The main activity for the application. Contains tabs for feed, story, following, and followers.
 */
public class TableauActivity extends AppCompatActivity {

    public static final String CURRENT_PLAYER_KEY = "CurrentUser";
    public static final String CURRENT_CORP_KEY = "CurrentCorp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableau);

        Player player = (Player) getIntent().getSerializableExtra(CURRENT_PLAYER_KEY);
        if(player == null) {
            int corpID = (int) getIntent().getSerializableExtra(CURRENT_CORP_KEY);
            player = new Player(new Corporation(corpID));
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), player);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        TextView corpName = findViewById(R.id.corp_name);

        corpName.setText(player.getTableau().getCorp().name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}