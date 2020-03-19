package com.iton.tapit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;

import android.view.animation.*;

public class CS_Game extends AppCompatActivity {

    private ClassicGame classicGame;

    private View decoreView;
    private float x1, x2, y1, y2;

    private Button btn_Screen;
    private Button btn_Back;
    private Button btn_More;
    private Button btn_Start;
    private TextView lbl_Time;

    LinearLayout page_Navigator;
    private int dotsCount;
    private ImageView[] dots;
    private boolean onChangeGame = false;

    private int currentGame = 0;
    private int[] gameSeconds = {3, 5, 10, 15};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs_game);

        btn_Screen = (Button)findViewById(R.id.btn_screen);
        btn_Back = (Button)findViewById(R.id.btn_back);
        btn_More = (Button)findViewById(R.id.btn_more);
        btn_Start = (Button)findViewById(R.id.btn_start);
        page_Navigator = (LinearLayout)findViewById(R.id.page_navigator);
        lbl_Time = (TextView)findViewById(R.id.lbl_game_timer);

        hideSystemBars();
        init_pageNav();
        init_game();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus){
            decoreView.setSystemUiVisibility(hideSystemBarsValue());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch (touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();

                if (x1 < x2){
                    if (currentGame != 0)
                        animate_gameTitle(true, false);

                }
                else if (x1 > x2){
                    if (currentGame != 3)
                        animate_gameTitle(false, false);

                }
                break;
        }

        return false;
    }

    public void init_pageNav() {
        dotsCount = 4;
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i ++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8,0,8,0);

            page_Navigator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
    }

    public void init_game() {
        classicGame = new ClassicGame(this, gameSeconds[currentGame]);
        change_Navigator();
    }

    public void change_Navigator() {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
        }

        dots[currentGame].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
    }

    public void animate_gameTitle(final boolean rtl, final boolean returnAnim) {
        if (onChangeGame && !returnAnim) return;

        float fromX = 0, toX = 1000.0f;

        if (returnAnim) {
            fromX = 1000.0f;
            toX = 0;

            if (rtl) fromX *= -1;
        }
        else {
            if (!rtl) {
                toX *= -1;
                currentGame++;
            }
            else
                currentGame--;
        }

        TranslateAnimation animation;

        animation = new TranslateAnimation(fromX, toX, 0, 0);

        animation.setDuration(650);
        animation.setRepeatCount(0);
        animation.setFillAfter(true);
        lbl_Time.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                onChangeGame = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!returnAnim) {
                    animate_gameTitle(rtl, true);
                    init_game();
                }
                if (returnAnim)
                    onChangeGame = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void btn_Back(View v){
        finish();
        this.overridePendingTransition(R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_right);
    }

    public void btn_Start(View v){
        btn_Screen.setVisibility(View.VISIBLE);
        classicGame.startCount();
    }

    public void btn_Screen(View v){
        classicGame.addTap();
    }

    public void UI_ElementsHide(boolean state){
        if (state){
            btn_Screen.setVisibility(View.INVISIBLE);
            btn_Back.setVisibility(View.VISIBLE);
            btn_More.setVisibility(View.VISIBLE);
            btn_Start.setVisibility(View.VISIBLE);
            page_Navigator.setVisibility(View.VISIBLE);
        }
        else {
            btn_Screen.setVisibility(View.VISIBLE);
            btn_Back.setVisibility(View.INVISIBLE);
            btn_More.setVisibility(View.INVISIBLE);
            btn_Start.setVisibility(View.INVISIBLE);
            page_Navigator.setVisibility(View.INVISIBLE);
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
}
