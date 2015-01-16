package icsbook.sample.section2.example5;

import icsbook.sample.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity5 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager manager = getFragmentManager();
        MainFragmentDialog dialog = new MainFragmentDialog();
        dialog.show(manager, "dialog");
    }

    public class MainFragmentDialog extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.main_fragment, null, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(" タイトル");
            builder.setPositiveButton("OK", null);
            builder.setNegativeButton("Cancel", null);
            builder.setView(view);
            return builder.create();
        }
    }
}
