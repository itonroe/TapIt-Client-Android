package com.iton.tapit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MP_Menu extends AppCompatActivity {
    private View decoreView;
    private Server_Conversation SC = new Server_Conversation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_menu);
        hideSystemBars();
        connectServer();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus){
            decoreView.setSystemUiVisibility(hideSystemBarsValue());
        }
    }

    public void btn_Host(View v){
        Intent mp_groupList = new Intent(MP_Menu.this, MP_GroupList.class);
        startActivity(mp_groupList);
        this.overridePendingTransition(R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left);
    }

    public void btn_Join(View v){
        Intent mp_join = new Intent(MP_Menu.this, MP_Join.class);
        startActivity(mp_join);
        this.overridePendingTransition(R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left);
    }

    public void btn_Back(View v){
        finish();
        this.overridePendingTransition(R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_right);
    }

    public void btn_Profile(View v){
        Intent mp_profile = new Intent(MP_Menu.this, MP_Profile.class);
        startActivity(mp_profile);
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

    private void connectServer(){
        SC.connect();
    }
}
