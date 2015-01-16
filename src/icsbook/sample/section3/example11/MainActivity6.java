package icsbook.sample.section3.example11;

import icsbook.sample.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity6 extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // リスト用データ
        String[] data = { "Sample1", "Sample2", "Sample3", "Sample4", 
                "Sample5", "Sample6", "Sample7", "Sample8",
                "Sample9", "Sample10", "Sample11", };
        
        // リスト用 Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, data);
        
        // Adapter をリストにセット
        setListAdapter(adapter);


        ListView list = getListView();
        
        // リストを複数選択モードにする
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        
        // 複数選択のリスナーをセット
        list.setMultiChoiceModeListener(new MultiChoiceModeListener() {

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return true;
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.menu, menu);
                mode.setTitle("Select Items");
                setSubtitle(mode);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        Toast.makeText(MainActivity6.this, "Edit " 
                                + getListView().getCheckedItemCount() 
                                + " items", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        break;
                    default:
                        Toast.makeText(MainActivity6.this, "Clicked " 
                                + item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                setSubtitle(mode);
            }

            private void setSubtitle(ActionMode mode) {
                final int checkedCount = getListView().getCheckedItemCount();
                switch (checkedCount) {
                    case 0:
                        mode.setSubtitle(null);
                        break;
                    case 1:
                        mode.setSubtitle("One item selected");
                        break;
                    default:
                        mode.setSubtitle("" + checkedCount + " items selected");
                        break;
                }
            }
        });
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3_3_2, menu);
        return true;
    }
}
