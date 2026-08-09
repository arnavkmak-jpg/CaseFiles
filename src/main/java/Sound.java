import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Sound {
    static HashMap<String,Clip> sfx = new HashMap<>();
    static HashMap<String,Clip> bgm = new HashMap<>();
    private static Clip getAudio(String filepath){
        URL url = Sound.class.getResource(filepath);
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            return clip;
        }catch (LineUnavailableException e){
            System.out.println("Line not available");
        }
        catch (UnsupportedAudioFileException e){
            System.out.println("Audio not supported");
        }catch (IOException e){
            System.out.println("Something went wrong");
        }
        return null;


    }
    public static void loadALL(){
        //Sound effects
        sfx.put("Crowd", getAudio("/sfx/crowd talking.wav"));
        sfx.put("Slam", getAudio("/sfx/Desk Slam.wav"));
        sfx.put("dBlip", getAudio("/sfx/dialogue blip.wav"));
        sfx.put("Doors", getAudio("/sfx/door_open.wav"));
        sfx.put("HoldIt", getAudio("/sfx/holdit.wav"));
        sfx.put("Gavel", getAudio("/sfx/Judge gavel.wav"));
        sfx.put("lBlip", getAudio("/sfx/Location Typing.wav"));
        sfx.put("NotGuilty", getAudio("/sfx/Not Guilty.wav"));
        sfx.put("dObjection", getAudio("/sfx/objection.wav"));
        sfx.put("pObjection", getAudio("/sfx/prosec objection.wav"));
        sfx.put("Shocked", getAudio("/sfx/Shocked.wav"));
        sfx.put("TheEnd", getAudio("/sfx/The end.wav"));
        sfx.put("Victory", getAudio("/sfx/VIctory.wav"));
        sfx.put("Testimony",getAudio("/sfx/testimony.wav"));

        //Background music
        bgm.put("Courtroom", getAudio("/bgm/03. Apollo Justice - Ace Attorney - Court is Now in Session.wav"));
        bgm.put("Moderato", getAudio("/bgm/04. Cross-Examination - Moderato 2007.wav"));
        bgm.put("Objection", getAudio("/bgm/06. Apollo Justice - A New Era Begins!.wav"));
        bgm.put("Allegro", getAudio("/bgm/07. Cross-Examination - Allegro 2007.wav"));
        bgm.put("Truth", getAudio("/bgm/08. The Truth Revealed 2007.wav"));
        bgm.put("Pursuit", getAudio("/bgm/11. Pursuit - Gotta Corner the Culprit.wav"));
        bgm.put("Lobby", getAudio("/bgm/33. Victory! - A Win for Us.wav"));
    }

    public static void startSfx(String key){
        Clip c = sfx.get(key);
        c.setFramePosition(0);
        c.start();
    }

    public static void loopSfx(String key){
        Clip c = sfx.get(key);
        if (c!=null){
            c.setFramePosition(0);
            c.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public static void stopSfx(String key){
        Clip c = sfx.get(key);
        if (c!=null){
            c.stop();
        }
    }

    public static void startBgm(String key){
        Clip c = bgm.get(key);
        if (c != null){
            c.stop();
        }
        c.setFramePosition(0);
        c.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public static void stopBgm(String key){
        Clip c = bgm.get(key);
        if (c != null){
            c.stop();

        }

    }





}
