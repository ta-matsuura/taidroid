package icsbook.sample.section2.example5;

import icsbook.sample.R;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager manager = getFragmentManager();
        MainFragmentDialog1 dialog1 = new MainFragmentDialog1();
        dialog1.show(manager, "dialog1");
    }

    public class MainFragmentDialog1 extends DialogFragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.main_fragment, container, false);
        }
    }
}
