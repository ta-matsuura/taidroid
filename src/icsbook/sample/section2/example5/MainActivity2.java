package icsbook.sample.section2.example5;

import icsbook.sample.R;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDialog();
    }

    private int mStackLevel;

    private void showDialog() {
        mStackLevel++;
        FragmentManager manager = getFragmentManager();
        MainFragmentDialog1 dialog1 = MainFragmentDialog1.newInstance(mStackLevel);
        dialog1.show(manager, "dialog1");
    }

    public static class MainFragmentDialog1 extends DialogFragment {
        static MainFragmentDialog1 newInstance(int num) {
            MainFragmentDialog1 f = new MainFragmentDialog1();
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            int num = getArguments().getInt("num");
            int style = DialogFragment.STYLE_NORMAL, theme = 0;
            switch ((num - 1) % 6) {
                case 1:
                    style = DialogFragment.STYLE_NO_TITLE;
                    break;
                case 2:
                    style = DialogFragment.STYLE_NO_FRAME;
                    break;
                case 3:
                    style = DialogFragment.STYLE_NO_INPUT;
                    break;
                case 4:
                    style = DialogFragment.STYLE_NORMAL;
                    break;
                case 5:
                    style = DialogFragment.STYLE_NORMAL;
                    break;
                case 6:
                    style = DialogFragment.STYLE_NO_TITLE;
                    break;
                case 7:
                    style = DialogFragment.STYLE_NO_FRAME;
                    break;
                case 8:
                    style = DialogFragment.STYLE_NORMAL;
                    break;
            }
            switch ((num - 1) % 6) {
                case 4:
                    theme = android.R.style.Theme_Holo;
                    break;
                case 5:
                    theme = android.R.style.Theme_Holo_Light_Dialog;
                    break;
                case 6:
                    theme = android.R.style.Theme_Holo_Light;
                    break;
                case 7:
                    theme = android.R.style.Theme_Holo_Light_Panel;
                    break;
                case 8:
                    theme = android.R.style.Theme_Holo_Light;
                    break;
            }
            setStyle(style, theme);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.main_dialog, container, false);
            View button = v.findViewById(R.id.show_dialog);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ((MainActivity2) getActivity()).showDialog();
                }
            });
            return v;
        }
    }
}
