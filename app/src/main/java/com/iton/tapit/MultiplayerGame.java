package com.iton.tapit;

import android.os.CountDownTimer;
import android.widget.TextView;
import android.view.View;

import org.w3c.dom.Text;

import java.util.Locale;

public class MultiplayerGame {

    private static final long SECOND_IN_MILLIS = 1000;

    private TextView mTextViewCountDown;
    private TextView mTextViewTaps;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis;
    private long mTimeLeftInMillisToStart;

    private MP_Game gameView;

    private int gameSeconds;
    private int gameTaps;
    private boolean gameisOn;

    public MultiplayerGame(MP_Game gameView, int gameSeconds){
        this.gameView = gameView;
        this.gameSeconds = gameSeconds;
        this.gameisOn = false;

        mTextViewCountDown = (TextView)gameView.findViewById(R.id.lbl_game_timer);
        mTextViewTaps = (TextView)gameView.findViewById(R.id.lbl_taps);

        resetGame();
        updateCountDownText();
    }

    public void startGame() {
        gameisOn = true;
        startTimer();
    }

    public void startCount(){
        gameView.UI_ElementsHide(false);
        resetGame();

        mTimeLeftInMillisToStart = 3 * SECOND_IN_MILLIS;
        CountDownTimer countToStart = new CountDownTimer(mTimeLeftInMillisToStart, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillisToStart = millisUntilFinished;
                int seconds = (int) ((mTimeLeftInMillisToStart / 10) / 100);
                mTextViewCountDown.setText(String.valueOf(seconds + 1));
            }

            @Override
            public void onFinish() {
                startGame();
            }
        }.start();
    }


    public void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                gameisOn = false;
                mTextViewCountDown.setText("00:00");
                gameView.UI_ElementsHide(true);
            }
        }.start();
    }

    public void updateCountDownText(){
        int millseconds = (int) ((mTimeLeftInMillis / 10) % 100);
        int seconds = (int) ((mTimeLeftInMillis / 10) / 100);

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", seconds, millseconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    public void pauseTimer(){
        mCountDownTimer.cancel();
    }

    public void resetGame(){
        mTimeLeftInMillis = gameSeconds * SECOND_IN_MILLIS;
        this.gameTaps = 0;
        updateTapText();
    }

    public void addTap(){
        if (gameisOn) {
            gameTaps++;
            updateTapText();
        }
    }

    public void updateTapText(){
        String taps = String.format(Locale.getDefault(), "%d", gameTaps);
        mTextViewTaps.setText(taps);
    }

    public int getTaps(){
        return this.gameTaps;
    }
}
