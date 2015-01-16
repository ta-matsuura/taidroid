package icsbook.sample.section2.example9;

import icsbook.sample.R;
import icsbook.sample.section2.example9.MainActivity.RetainedFragment.ProgressFragment;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 一番最初の場合は UI を生成する
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content, new UiFragment()).commit();
        }
    }

    /**
     * UI 用のフラグメント 処理の進捗状況を表示する ProgressBar を持つ
     */
    public static class UiFragment extends Fragment implements ProgressFragment{

        RetainedFragment mWorkFragment;
        ProgressBar mProgressBar;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.main2_9, container, false);

            Button button = (Button) v.findViewById(R.id.restart);
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    mWorkFragment.restart();
                }
            });
            
            mProgressBar = (ProgressBar)v.findViewById(R.id.progress);

            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            FragmentManager fm = getFragmentManager();

            // Fragment がすでに生成されているかチェック
            mWorkFragment = (RetainedFragment) fm.findFragmentByTag("work");

            // Fragment が生成されていない場合は、生成する
            if (mWorkFragment == null) {
                mWorkFragment = new RetainedFragment();
                // ターゲット Fragment にこの Fragment をセット
                mWorkFragment.setTargetFragment(this, 0);
                fm.beginTransaction().add(mWorkFragment, "work").commit();
            }
        }

        @Override
        public ProgressBar getProgressBar() {
            return mProgressBar;
        }
    }

    /**
     * バックグラウンドで処理するための Fragment
     */
    public static class RetainedFragment extends Fragment {
        
        public interface ProgressFragment {
            public ProgressBar getProgressBar();
        }
        
        // 進捗状態をセットするプログレスバー
        ProgressBar mProgressBar;
        
        // 現在の進捗位置
        int mPosition;
        
        boolean mReady = false;
        boolean mQuiting = false;

        /**
         * バックグラウンド処理用のスレッド
         */
        final Thread mThread = new Thread() {
            @Override
            public void run() {
                int max = 10000;

                while (true) {

                    // 現在の進捗を UI に反映
                    synchronized (this) {
                        
                        // UI の準備が完了して場合や、処理が完了した場合はスレッドを終了
                        while (!mReady || mPosition >= max) {
                            if (mQuiting) {
                                return;
                            }
                            try {
                                wait();
                            } catch (InterruptedException e) {
                            }
                        }

                        // 進捗状況をアップデート
                        mPosition++;
                        
                        // プログレスバーに進捗状況をセット
                        if(mProgressBar != null) {
                            max = mProgressBar.getMax();
                            mProgressBar.setProgress(mPosition);
                        }
                    }

                    // 通常はここでバックグラウンド処理を行う
                    // 簡単のためここでは 50ms 待つ処理をしている
                    synchronized (this) {
                        try {
                            wait(50);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        };

        /**
         * Fragment の初期化処理
         * setRetainInstance(true) が指定されている場合、
         * Activity が再生成されたときには呼ばれない
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // コンフィグレーションが変わってもこの Fragment を再生成
            // せず、保持するように指定
            setRetainInstance(true);

            // バックグラウンド処理用のスレッドを開始
            mThread.start();
        }

        /**
         * Fragment の Activity が生成され、その View の準備ができた後に呼ばれる
         * Fragment が最初に生成されたとき、及び再度新しい Activity に割り当てられる
         * ときの両方で呼ばれる
         */
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            // ターゲット Fragment を取得
            ProgressFragment fragment = (ProgressFragment) getTargetFragment();

            // ターゲット Fragment からプログレスバーを取得
            mProgressBar = fragment.getProgressBar();

            // UI の準備ができたことをセット
            synchronized (mThread) {
                mReady = true;
                mThread.notify();
            }
        }

        /**
         * Fragment が現在の Activity のインスタンスから外されるときに
         * 呼ばれる
         */
        @Override
        public void onDetach() {
            
            // 進捗状況をセットする UI が準備されていないことをセット
            synchronized (mThread) {
                mProgressBar = null;
                mReady = false;
                mThread.notify();
            }

            super.onDetach();
        }

        /**
         * Fragment が破棄されるときに呼ばれる
         * setRetainInstance(true) が指定されている場合
         * Activity が再生成されるときには呼ばれない
         */
        @Override
        public void onDestroy() {
            
            // バックグラウンド処理用のスレッドを終了するようにセット
            synchronized (mThread) {
                mReady = false;
                mQuiting = true;
                mThread.notify();
            }

            super.onDestroy();
        }

        /**
         * UI からバックグラウンド処理をリスタートするための API
         */
        public void restart() {
            synchronized (mThread) {
                mPosition = 0;
                mThread.notify();
            }
        }
    }
}