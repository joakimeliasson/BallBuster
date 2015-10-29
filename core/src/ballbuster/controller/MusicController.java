package ballbuster.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Johan Segerlund.
 */
public class MusicController implements IController {
    private int numberOfSongs = 11;
    private int currentSongIndex;
    private Music currentSong;
    private ArrayList<Music> musicList;

    private float volume = 1f;
    private boolean pause = false;

    private Random random;

    public MusicController() {
        musicList = new ArrayList<>();
        for(int i = 0; i < numberOfSongs; i++){
            String song = "music/Music" + (i + 1) + ".mp3";
                                                                //Adds all the songs from the map music with name from
                                                                // Music1 ... MusicN where N is numberOfSongs
            musicList.add(Gdx.audio.newMusic(Gdx.files.internal(song)));
        }
        random = new Random();

        currentSongIndex  = random.nextInt(numberOfSongs - 1);
        currentSong = musicList.get(currentSongIndex);
        currentSong.setVolume(volume);
    }

    @Override
    public void onCreate() {
        currentSong.play();
    }



    @Override
    public void onRender() {
        if(!currentSong.isPlaying() && !pause) {
            //Runs if the song have stopped playing(finished) and user haven't paused it.
            nextSong();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            playStop();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F9)) {
            previousSong();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F10)) {
            nextSong();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F11)) {
            decreaseVolume();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F12)) {
            increaseVolume();
        }


    }

    private void playStop(){
        if (currentSong.isPlaying()) {
            currentSong.pause();
            pause = true;
        } else {
            currentSong.play();
            pause = false;
        }
    }

    private void decreaseVolume() {
        volume = volume - 0.1f;
        if(volume < 0){
            volume = 0f;
        }
        currentSong.setVolume(volume);
    }

    private void increaseVolume() {
        volume = volume + 0.1f;
        if(volume > 1f){
            volume = 1f;
        }
        currentSong.setVolume(volume);
    }

    private void nextSong() {
        changeSong(1);
    }

    private void previousSong() {
        changeSong(numberOfSongs - 1);
    }

    private void changeSong(int i){
        if(!pause){
            currentSong.stop();
        }
        currentSongIndex = (currentSongIndex + i) % numberOfSongs;
        currentSong = musicList.get(currentSongIndex);
        currentSong.setVolume(volume);
        currentSong.play();
        pause = false;
    }
}

    /* Commands in Music
    currentSong.setVolume(0.5f);                 // sets the volume to half the maximum volume
    currentSong.setLooping(true);                // will repeat playback until currentSong.stop() is called
    currentSong.stop();                          // stops the playback
    currentSong.pause();                         // pauses the playback
    currentSong.play();                          // resumes the playback
    boolean isPlaying = currentSong.isPlaying(); // obvious :)
    boolean isLooping = currentSong.isLooping(); // obvious as well :)
    float position = currentSong.getPosition();  // returns the playback position in seconds
    */
