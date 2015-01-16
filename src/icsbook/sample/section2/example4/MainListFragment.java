package icsbook.sample.section2.example4;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainListFragment extends ListFragment {
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] data = { "test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9", "test10" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                data);
        setListAdapter(adapter);
    }
}
