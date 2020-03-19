package com.iton.tapit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MP_Game extends AppCompatActivity {

    private MultiplayerGame multiplayerGame;

    private View decoreView;

    private Button btn_Screen;
    private Button btn_Back;
    private Button btn_More;
    private Button btn_Start;
    private TextView lbl_Time;
    private CircleProgressBar circleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_game);

        btn_Screen = (Button)findViewById(R.id.btn_screen);
        btn_Back = (Button)findViewById(R.id.btn_back);
        btn_More = (Button)findViewById(R.id.btn_more);
        btn_Start = (Button)findViewById(R.id.btn_start);
        lbl_Time = (TextView)findViewById(R.id.lbl_game_timer);

        hideSystemBars();
        initProgressBar();
        init_game();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus){
            decoreView.setSystemUiVisibility(hideSystemBarsValue());
        }
    }

    public void init_game() {
        multiplayerGame = new MultiplayerGame(this, 5);
    }

    public void btn_Back(View v){
        finish();
        this.overridePendingTransition(R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_right);
    }

    public void btn_Start(View v){
        btn_Screen.setVisibility(View.VISIBLE);
        multiplayerGame.startCount();
    }

    public void btn_Screen(View v){
        multiplayerGame.addTap();
        updateProgressBar();
    }

    public void UI_ElementsHide(boolean state){
        if (state){
            btn_Screen.setVisibility(View.INVISIBLE);
            btn_Back.setVisibility(View.VISIBLE);
            btn_More.setVisibility(View.VISIBLE);
            btn_Start.setVisibility(View.VISIBLE);
        }
        else {
            btn_Screen.setVisibility(View.VISIBLE);
            btn_Back.setVisibility(View.INVISIBLE);
            btn_More.setVisibility(View.INVISIBLE);
            btn_Start.setVisibility(View.INVISIBLE);
        }
    }

    private int hideSystemBarsValue(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    private void hideSystemBars(){
        decoreView = getWindow().getDecorView();
        decoreView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visiblity) {
                if (visiblity == 0)
                    decoreView.setSystemUiVisibility(hideSystemBarsValue());
            }
        });
    }

    private void initProgressBar(){
        circleProgressBar = (CircleProgressBar) decoreView.findViewById(R.id.custom_progressBar);
        circleProgressBar.setProgressWithAnimation(0);
    }

    private void updateProgressBar(){
        int taps = multiplayerGame.getTaps();
        circleProgressBar.setProgress(taps);
    }
}
