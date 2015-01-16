package icsbook.sample.section1.example4;

import icsbook.sample.R;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MainActivity5 extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}
