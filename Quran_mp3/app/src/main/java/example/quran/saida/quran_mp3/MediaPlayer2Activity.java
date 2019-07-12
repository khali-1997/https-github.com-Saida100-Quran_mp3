package example.quran.saida.quran_mp3;

import android.app.DownloadManager;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MediaPlayer2Activity extends AppCompatActivity {

    // DownloadManager downloadManager;

    private boolean isConnectedInternet;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    private SeekBar seekbar, volumeBar;
    private TextView txtStartTime, txtEndTime, nameSurah;
    private Button btnPlay, btnPause;
    private ImageView imageView1, imageView2, imageView3;
    private ImageButton imageButton;

    public static int oneTimeOnly = 0;
    String url = "http://server8.mp3quran.net/afs/";
    //http://server8.mp3quran.net/afs/114.mp3
    String number;
    String name;
    String url2;
    String buttonText;
    DatabaseSure databaseSure;
    boolean checkInternet = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player2);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        seekbar.setClickable(false);
      //  imageButton = findViewById(R.id.downloadFile);
        txtStartTime = (TextView) findViewById(R.id.startTime);
        txtEndTime = (TextView) findViewById(R.id.endTime);
        nameSurah = (TextView) findViewById(R.id.nameSurah);
        imageView1 = findViewById(R.id.volume);
        imageView2 = findViewById(R.id.sound2);
        imageView3 = findViewById(R.id.imageQuran);
        btnPlay = (Button) findViewById(R.id.button);
        btnPause = (Button) findViewById(R.id.button2);
        databaseSure = new DatabaseSure(getApplicationContext());
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setVolume(0.5f, 0.5f);
        number = getIntent().getStringExtra("number");
        name = getIntent().getStringExtra("name");
        nameSurah.setText(name);


        buttonText = btnPlay.getText().toString();
        try {
            if (Integer.parseInt(number) / 10 < 1) {
                url2 = url.concat("00").concat(number).concat(".mp3");
            } else if (Integer.parseInt(number) / 10 >= 1 && Integer.parseInt(number) / 10 < 10) {
                url2 = url.concat("0").concat(number).concat(".mp3");

            } else {
                url2 = url.concat(number).concat(".mp3");
            }
            mediaPlayer.setDataSource(url2);
            mediaPlayer.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        }
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isConnectedInternet = isNetworkAvailable();
                if (!isConnectedInternet) {
                    Toast.makeText(getApplicationContext(), "İnternet bağlantısı yoxdur", Toast.LENGTH_SHORT).show();
                    checkInternet = false;
                } else {
                    checkInternet = true;

                }
                if (!mediaPlayer.isPlaying() && checkInternet) {
                    Log.e("log","play cliked");
                    Toast.makeText(getApplicationContext(), "Surə oxunur", Toast.LENGTH_SHORT).show();
                    oneTimeOnly = 0;
                    btnPlay.setVisibility(View.GONE);
                    btnPause.setVisibility(View.VISIBLE);
                    mediaPlayer.start();
                    finalTime = mediaPlayer.getDuration();
                    startTime = mediaPlayer.getCurrentPosition();
                    if (oneTimeOnly == 0) {
                        seekbar.setMax((int) finalTime);
                        oneTimeOnly = 1;
                    }


                    txtStartTime.setText(String.format("%d min, %d sec",
                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                            startTime)))
                    );
                    txtEndTime.setText(String.format("%d dəq, %d san",
                            TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                            TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                            finalTime)))
                    );


                    seekbar.setProgress((int) startTime);
                    myHandler.postDelayed(UpdateSongTime, 100);


                }

            }

        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pause sıxıldı", Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
                btnPause.setVisibility(View.GONE);
                btnPlay.setVisibility(View.VISIBLE);


            }
        });


        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volumeNum = progress / 100f;
                mediaPlayer.setVolume(volumeNum, volumeNum);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                downloadManager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//                Uri uri= Uri.parse(url2);
//                DownloadManager.Request request=new DownloadManager.Request(uri);
//                  request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                Long reference=downloadManager.enqueue(request);
//                if(checkInternet){
//                    Toast.makeText(getApplicationContext(), nameSurah.getText().toString()+"  surəsi yüklənir",Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(getApplicationContext(), "İnternet bağlantısı yoxdur", Toast.LENGTH_SHORT).show();
//
//                }
//
//            }
//        });


    }


    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            txtStartTime.setText(String.format("%d dəq, %d san",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
            if ((txtStartTime.getText()).equals(txtEndTime.getText())) {
                btnPause.setVisibility(View.GONE);
                btnPlay.setVisibility(View.VISIBLE);
            }
        }
    };

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();


    }


    @Override
    protected void onRestart() {
        super.onRestart();

    }
}

