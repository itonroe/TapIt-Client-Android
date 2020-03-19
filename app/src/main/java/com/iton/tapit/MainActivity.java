package com.iton.tapit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;

public class MainActivity extends AppCompatActivity {


    public boolean isSignIn = false;

    private View decoreView;
    private float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideSystemBars();
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
                    Intent i = new Intent(MainActivity.this, MP_Join.class);
                    startActivity(i);
                }
                break;
        }

        return false;
    }

    public void btn_Classic(View v){
        Intent cs_game = new Intent(MainActivity.this, CS_Game.class);
        startActivity(cs_game);

        this.overridePendingTransition(R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left);
    }

    public void btn_Multiplayer(View v){
        if (isSignIn){
            Intent mp_menu = new Intent(MainActivity.this, MP_Menu.class);
            startActivity(mp_menu);
        }
        else {
            Intent mp_menu = new Intent(MainActivity.this, MP_Login.class);
            startActivity(mp_menu);
        }

        this.overridePendingTransition(R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left);
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
