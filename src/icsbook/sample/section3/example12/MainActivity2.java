package icsbook.sample.section3.example12;

import icsbook.sample.R;
import icsbook.sample.section3.example12.ColorActionProvider.ColorItem;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu3_12_2, menu);

        MenuItem item = menu.findItem(R.id.menu_custom_action_provider);

        ColorActionProvider actionProvider = (ColorActionProvider) item.getActionProvider();

        List<ColorItem> colorList = new ArrayList<ColorItem>();
        colorList.add(new ColorItem("BLACK", Color.BLACK));
        colorList.add(new ColorItem("BLUE", Color.BLUE));
        colorList.add(new ColorItem("GREEN", Color.GREEN));
        colorList.add(new ColorItem("RED", Color.RED));
        colorList.add(new ColorItem("YELLOW", Color.YELLOW));
        colorList.add(new ColorItem("CYAN", Color.CYAN));
        
        actionProvider.setColorList(colorList);
        actionProvider.setOnColorListSelectedListener(new ColorActionProvider.OnColorListSelectedListener() {

            @Override
            public void onColorListSelected(int color) {
                View v = findViewById(android.R.id.content);
                v.setBackgroundColor(color);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "onOptionsItemSelected", Toast.LENGTH_SHORT).show();
        return false;
    }
}