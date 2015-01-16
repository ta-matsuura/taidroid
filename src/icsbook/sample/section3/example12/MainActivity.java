package icsbook.sample.section3.example12;

import icsbook.sample.R;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {

    private ShareActionProvider mShareActionProvider;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3_12, menu);
        
        // MenuItem を取得
        MenuItem menuItem = menu.findItem(R.id.menu_share);
        
        // ShareActionProvider を取得
        mShareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        
        // ShareActionProvider に共有 Intent をセット
        mShareActionProvider.setShareIntent(getDefaultShareIntent());
        
        return true;
    }

    private Intent getDefaultShareIntent() {
        // 共有 Intent を返す
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "share text");
        return intent;
    }
}
