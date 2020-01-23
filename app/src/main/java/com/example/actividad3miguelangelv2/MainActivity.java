package com.example.actividad3miguelangelv2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DefaultDatabaseErrorHandler;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.PlayerView);
    }

    @Override
    protected void onStart(){
        super.onStart();

        player = ExoPlayerFactory.newSimpleInstance(this,new DefaultTrackSelector());

        playerView.setPlayer(player);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "Actividad 3"));

        ExtractorMediaSource archivoMultimedia = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse("https://www3.gobiernodecanarias.org/medusa/mediateca/iesgeneto/wp-content/uploads/sites/9/2013/11/paseo-virtual-final.mp4"));
        player.prepare(archivoMultimedia);
        player.setPlayWhenReady(true);
    }

    @Override
    protected void onStop(){
        super.onStop();

        playerView.setPlayer(null);
        player.release();
        player = null;
    }

}
