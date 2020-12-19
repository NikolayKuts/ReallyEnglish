package com.example.reallyenglsh;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.realyenglsh.R;

import java.io.IOException;

public class OnClickAudioContentPlayer implements View.OnClickListener {
    @Override
    public void onClick(View v) {

        Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.fadein);
        v.startAnimation(animation);

        String word = ((TextView) v).getText().toString();
        Log.i("log_word", word);
        MediaPlayer player = new MediaPlayer();
        try {
            player.setDataSource(String.format("https://wooordhunt.ru/data/sound/sow/us/%s.mp3", word));

            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                    v.clearAnimation();
                }
//            v.clearAnimation();
            });
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
