package com.iton.tapit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MP_Signup extends AppCompatActivity {

    private View decoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_signup);
        hideSystemBars();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus){
            decoreView.setSystemUiVisibility(hideSystemBarsValue());
        }
    }

    public void btn_Back(View v){
        finish();
        this.overridePendingTransition(R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_right);
    }

    public void btn_Login(View v){
        btn_Back(v);
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
