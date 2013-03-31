/**
 * Created with IntelliJ IDEA.
 * Date  : 28/03/13
 * Time  : 9:28 AM
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class Jukebox extends Applet{
//***************************************//
    Image img;
    MediaTracker MediaTrack;
    AudioInputStream stream;
    Clip music;
    List list;
    URL url;
    URL file;
//***************************************//

    public void init() {
        // Sizes the applet on init to the preferred/intended dimensions
        resize(600, 260);
        setLayout(new BorderLayout(0,3));

        // Creates the list component that will house our songs.
        list = new List(5);

        /* *
        * Complicated block of code does a bunch of stuff.
        *
        * Firstly it gets a URL to the /data folder that's correct because it's worked out relative to the java file.
        * This means it'll work when uploaded to a web server/anywhere.
        *
        * It then adds each .wav file to the list component in the window, but this is surrounded in a try/catch expression.
        *
        * Simply meaning that if it fails (kind of likely, what if no songs are available, etc.) it'll know what to do still
        * */

        url = this.getClass().getResource("/data");

        File dir = null;
        try {
            dir = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        for (File child : dir.listFiles()) {
            if(child.getName().endsWith(".wav")) {
                list.add(child.getName());
            } else {
                // Do nothing?
            }
        }

        // Add the list to the window.
        add(list, BorderLayout.SOUTH);

        /*
         * Listen up!
         *  Provides a listener that responds to when items are *double-clicked* on
         *  in the list component.
         */
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                file = this.getClass().getResource("/data/" + list.getSelectedItem());  // Gets the File URL to the selected song.

                // What if something is already playing? :S
                if(stream != null) {  // If the AudioStream *isn't* empty...
                    try {
                        stream.close();  // try to close the stream
                    } catch (IOException e) {
                        e.printStackTrace();   // but if it can't, print a stack trace.
                    }
                }

                if(music != null) {  // If the clip *isn't empty...
                    music.stop();    // Stop the music.
                    music.flush();   // Flush the cache.
                }

                stream = null; // Set the stream to null.
                try {
                    stream = AudioSystem.getAudioInputStream(file);  // Try to put the file into the AudioStream
                } catch (UnsupportedAudioFileException e) {          // If the file is unsuported..
                    e.printStackTrace();                                // Print a Stack Trace
                } catch (IOException e) {                            // If there's an I/O exception..
                    e.printStackTrace();                                // Print a Stack Trace
                }
                music = null; // Set the clip to null.
                try {
                    music = AudioSystem.getClip();      // Creates a clip that can be used for playing back an audio file or an audio stream.
                } catch (LineUnavailableException e) {  // But if there's no line to put it on...
                    e.printStackTrace();                    // Print a Stack Trace
                }
                try {
                    music.open(stream);                 // Try to use the clip to open the previously created stream.
                } catch (LineUnavailableException e) {  // But if there's no line to put it on...
                    e.printStackTrace();                    // Print a Stack Trace.
                } catch (IOException e) {               // Or if there's an I/O Error.
                    e.printStackTrace();                    // Print a Stack Trace.
                }
                music.start();  // Finally, play the music.
            }
        });
        // Finally end the listener.
        // Buttons
        add(Box.createRigidArea(new Dimension(50, 135)), BorderLayout.NORTH); // Invisible Box to shift things around

        Font btn = new Font("Sans-serif", Font.BOLD, 30);
        Button stop = new Button("■");
        stop.setFont(btn);
        add(stop,BorderLayout.EAST);
    }

    public void paint(Graphics g) {
        // No more fuzzy text! Anti-aliasing on!
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        //Define some stuff!
        String title = "Song name";
        String artist = "Song artist";
        String length = "0:00";
        Font fntT = new Font("Trebuchet MS", Font.PLAIN, 22);
        Font fntA = new Font("Trebuchet MS", Font.PLAIN, 18);
        Font fntL = new Font("Trebuchet MS", Font.PLAIN, 16);

        //Let's have a mediatracker.
        MediaTrack = new MediaTracker(this);

        // Gets album art and scales it to 150x150 pixels
        img = getImage(getCodeBase(), "default.jpg");
        MediaTrack.addImage(img,0);
        g2.drawImage(img, 10, 10, 150, 150, this);

        // Implement the song information
        g2.setFont(fntT);
        g2.drawString(title, 170, 50);
        g2.setFont(fntA);
        g2.drawString(artist, 180, 80);
        g2.setFont(fntL);
        g2.drawString(length,180,100);
    }

}