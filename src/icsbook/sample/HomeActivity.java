package icsbook.sample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = new SectionListFragment(); 
        
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().add(android.R.id.content, fragment).commit();
    }
    
    public class SectionListFragment extends ListFragment {
        
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            String[] data = getResources().getStringArray(R.array.sections);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
            setListAdapter(adapter);
        }
        
        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);
            
            Fragment fragment = new DetailListFragment(position);
            
            FragmentManager manager = getFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(android.R.id.content, fragment);
            ft.addToBackStack("Section" + position);
            ft.commit();
        }
    }
    
    public class DetailListFragment extends ListFragment {
        
        private int mPosition = 0;
        
        public DetailListFragment(int position) {
            mPosition = position;
        }
        
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            
            int resId = R.array.section1;
            switch(mPosition) {
                case 0:
                    resId = R.array.section1;
                    break;
                case 1:
                    resId = R.array.section2;
                    break;
                case 2:
                    resId = R.array.section3;
                    break;
                case 3:
                    resId = R.array.section4;
                    break;
                case 4:
                    resId = R.array.section5;
                    break;
                case 5:
                    resId = R.array.section7;
                    break;
                case 6:
                    resId = R.array.section8;
                    break;
                case 7:
                    resId = R.array.section9;
                    break;
            }
            
            String[] data = getResources().getStringArray(resId);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
            setListAdapter(adapter);
        }
        
        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);
            
            switch(mPosition) {
                case 0:
                    openSection1(position);
                    break;
                case 1:
                    openSection2(position);
                    break;
                case 2:
                    openSection3(position);
                    break;
                case 3:
                    openSection4(position);
                    break;
                case 4:
                    openSection5(position);
                    break;
                case 5:
                    openSection7(position);
                    break;
                case 6:
                    openSection8(position);
                    break;
                case 7:
                    openSection9(position);
                    break;
            }
        }

        private void openSection9(int position) {
            Intent intent = null;
            
            switch(position) {
                case 0:
                    intent = new Intent(getActivity(), icsbook.sample.section9.example3.MainActivity.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), icsbook.sample.section9.example4.MainActivity.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), icsbook.sample.section9.example5.MainActivity.class);
                    break;
            }
            
            startActivity(intent);
        }

        private void openSection8(int position) {
            Intent intent = null;
            
            switch(position) {
                case 0:
                    intent = new Intent(getActivity(), icsbook.sample.section8.example1.MainActivity.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), icsbook.sample.section8.example2.MainActivity.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), icsbook.sample.section8.example3.MainActivity.class);
                    break;
                case 3:
                    intent = new Intent(getActivity(), icsbook.sample.section8.example4.MainActivity.class);
                    break;
                case 4:
                    intent = new Intent(getActivity(), icsbook.sample.section8.example5.MainActivity.class);
                    break;
            }
            
            startActivity(intent);
        }

        private void openSection7(int position) {
            Intent intent = null;
            
            switch(position) {
                case 0:
                    intent = new Intent(getActivity(), icsbook.sample.section7.example2.MainActivity.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), icsbook.sample.section7.example3.MainActivity.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), icsbook.sample.section7.example4.MainActivity.class);
                    break;
            }
            
            startActivity(intent);
        }

        private void openSection5(int position) {
            Intent intent = null;
            
            switch(position) {
                case 0:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example3.MainActivity.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example3.MainActivity2.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example3.MainActivity3.class);
                    break;
                case 3:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example3.MainActivity4.class);
                    break;
                case 4:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example4.MainActivity.class);
                    break;
                case 5:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example5.MainActivity.class);
                    break;
                case 6:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example5.MainActivity2.class);
                    break;
                case 7:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example5.MainActivity3.class);
                    break;
                case 8:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example5.MainActivity4.class);
                    break;
                case 9:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example6.MainActivity.class);
                    break;
                case 10:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example6.MainActivity2.class);
                    break;
                case 11:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example7.MainActivity.class);
                    break;
                case 12:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example8.MainActivity.class);
                    break;
                case 13:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example9.MainActivity.class);
                    break;
                case 14:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example9.MainActivity2.class);
                    break;
                case 15:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example9.MainActivity3.class);
                    break;
                case 16:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example9.MainActivity4.class);
                    break;
                case 17:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example9.MainActivity5.class);
                    break;
                case 18:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example9.MainActivity6.class);
                    break;
                case 19:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example9.MainActivity7.class);
                    break;
                case 20:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example9.MainActivity8.class);
                    break;
                case 21:
                    intent = new Intent(getActivity(), icsbook.sample.section5.example10.MainActivity.class);
                    break;
            }
            
            startActivity(intent);
        }

        private void openSection4(int position) {
            Intent intent = null;
            
            switch(position) {
                case 0:
                    intent = new Intent(getActivity(), icsbook.sample.section4.example1.MainActivity.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), icsbook.sample.section4.example1.MainActivity2.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), icsbook.sample.section4.example2.MainActivity.class);
                    break;
                case 3:
                    intent = new Intent(getActivity(), icsbook.sample.section4.example3.MainActivity.class);
                    break;
                case 4:
                    intent = new Intent(getActivity(), icsbook.sample.section4.example3.MainActivity2.class);
                    break;
                case 5:
                    intent = new Intent(getActivity(), icsbook.sample.section4.example3.MainActivity3.class);
                    break;
                case 6:
                    intent = new Intent(getActivity(), icsbook.sample.section4.example3.MainActivity4.class);
                    break;
                case 7:
                    intent = new Intent(getActivity(), icsbook.sample.section4.example4.MainActivity.class);
                    break;
                case 8:
                    intent = new Intent(getActivity(), icsbook.sample.section4.example4.MainActivity2.class);
                    break;
                case 9:
                    intent = new Intent(getActivity(), icsbook.sample.section4.example4.MainActivity3.class);
                    break;
                case 10:
                    intent = new Intent(getActivity(), icsbook.sample.section4.example5.MainActivity.class);
                    break;
            }
            
            startActivity(intent);
        }

        private void openSection3(int position) {
            Intent intent = null;
            
            switch(position) {
                case 0:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example1.MainActivity.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example1.MainActivity2.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example1.MainActivity3.class);
                    break;
                case 3:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example2.MainActivity.class);
                    break;
                case 4:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example3.MainActivity.class);
                    break;
                case 5:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example3.MainActivity2.class);
                    break;
                case 6:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example4.MainActivity.class);
                    break;
                case 7:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example4.MainActivity2.class);
                    break;
                case 8:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example5.MainActivity.class);
                    break;
                case 9:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example5.MainActivity2.class);
                    break;
                case 10:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example6.MainActivity.class);
                    break;
                case 11:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example7.MainActivity.class);
                    break;
                case 12:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example7.MainActivity2.class);
                    break;
                case 13:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example8.MainActivity.class);
                    break;
                case 14:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example9.MainActivity.class);
                    break;
                case 15:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example9.MainActivity2.class);
                    break;
                case 16:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example9.MainActivity3.class);
                    break;
                case 17:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example9.MainActivity4.class);
                    break;
                case 18:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example9.MainActivity5.class);
                    break;
                case 19:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example9.MainActivity6.class);
                    break;
                case 20:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example9.MainActivity7.class);
                    break;
                case 21:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example9.MainActivity8.class);
                    break;
                case 22:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example9.MainActivity9.class);
                    break;
                case 23:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example9.MainActivity10.class);
                    break;
                case 24:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example9.MainActivity11.class);
                    break;
                case 25:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example9.MainActivity12.class);
                    break;
                case 26:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example10.MainActivity.class);
                    break;
                case 27:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example10.MainActivity2.class);
                    break;
                case 28:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example11.MainActivity.class);
                    break;
                case 29:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example11.MainActivity2.class);
                    break;
                case 30:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example11.MainActivity3.class);
                    break;
                case 31:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example11.MainActivity4.class);
                    break;
                case 32:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example11.MainActivity5.class);
                    break;
                case 33:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example11.MainActivity6.class);
                    break;
                case 34:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example11.MainActivity7.class);
                    break;
                case 35:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example12.MainActivity.class);
                    break;
                case 36:
                    intent = new Intent(getActivity(), icsbook.sample.section3.example12.MainActivity2.class);
                    break;
            }
            
            startActivity(intent);
        }

        private void openSection2(int position) {
            Intent intent = null;
            
            switch(position) {
                case 0:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example2.MainActivity.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example3.MainActivity.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example4.MainActivity.class);
                    break;
                case 3:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example5.MainActivity.class);
                    break;
                case 4:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example5.MainActivity2.class);
                    break;
                case 5:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example5.MainActivity3.class);
                    break;
                case 6:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example5.MainActivity4.class);
                    break;
                case 7:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example5.MainActivity5.class);
                    break;
                case 8:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example6.MainActivity.class);
                    break;
                case 9:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example7.MainActivity.class);
                    break;
                case 10:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example8.MainActivity.class);
                    break;
                case 11:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example9.MainActivity.class);
                    break;
                case 12:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example10.MainActivity.class);
                    break;
                case 13:
                    intent = new Intent(getActivity(), icsbook.sample.section2.example10.MainActivity2.class);
                    break;
            }
            
            startActivity(intent);
        }

        private void openSection1(int position) {
            Intent intent = null;
            
            switch(position) {
                case 0:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example1.MainActivity.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example2.MainActivity3.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example2.MainActivity4.class);
                    break;
                case 3:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example2.MainActivity5.class);
                    break;
                case 4:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example3.ThemeHoloActivity.class);
                    break;
                case 5:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example3.ThemeHoloNoActionBarActivity.class);
                    break;
                case 6:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example3.ThemeHoloNoActionBarFullscreenActivity.class);
                    break;
                case 7:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example3.ThemeHoloPanelActivity.class);
                    break;
                case 8:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example3.ThemeHoloWallpaperActivity.class);
                    break;
                case 9:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example3.ThemeHoloWallpaperNoActionBarActivity.class);
                    break;
                case 10:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example3.ThemeHoloDialogActivity.class);
                    break;
                case 11:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example3.ThemeHoloDialogMinWidthActivity.class);
                    break;
                case 12:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example3.ThemeHoloDialogNoActionBarActivity.class);
                    break;
                case 13:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example3.ThemeHoloDialogNoActionBarMinWidthActivity.class);
                    break;
                case 14:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example4.MainActivity.class);
                    break;
                case 15:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example4.MainActivity2.class);
                    break;
                case 16:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example4.MainActivity3.class);
                    break;
                case 17:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example4.MainActivity4.class);
                    break;
                case 18:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example4.MainActivity5.class);
                    break;
                case 19:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example4.MainActivity6.class);
                    break;
                case 20:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example4.MainActivity7.class);
                    break;
                case 21:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example4.MainActivity8.class);
                    break;
                case 22:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example7.MainActivity.class);
                    break;
                case 23:
                    intent = new Intent(getActivity(), icsbook.sample.section1.example7.MainActivity2.class);
                    break;
            }
            
            startActivity(intent);
        }
    }
}
