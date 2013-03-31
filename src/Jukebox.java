/**
 * Created with IntelliJ IDEA.
 * Author: Andreas
 * Date  : 28/03/13
 * Time  : 9:28 AM
 */
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.io.File;
import java.io.*;
import java.net.*;

public class Jukebox extends Applet{
//***************************************//
    Image img;
    MediaTracker MediaTrack;
//***************************************//

    public void init() {
        // Sizes the applet on init to the preferred/intended dimensions
        resize(600, 260);
        setLayout(new BorderLayout(0,3));

        // Song list
        List list = new List(5);

        /* *
        * Big complicated block of code does a bunch of stuff.
        *
        * Firstly it gets a URL to the /data folder that's correct because it's worked out relative to the java file.
        * This means it'll work when uploaded to a web server/anywhere.
        *
        * It then adds each .mp3 file to the list component in the window, but this is surrounded in a try/catch expression.
        *
        * Simply meaning that if it fails (kind of likely, what if no songs are available, etc.) it'll know what to do still
        * */

        URL url = this.getClass().getResource("/data");

        File dir = null;
        try {
            dir = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        for (File child : dir.listFiles()) {
            if(child.getName().endsWith(".mp3")) {
                list.addItem(child.getName());
            } else {
                // Do nothing?
            }
        }

        // Add the list to the window.
        add(list, BorderLayout.SOUTH);

        //Buttons
        add(Box.createRigidArea(new Dimension(50,135)),BorderLayout.NORTH); // Invisible Box to shift things around

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
        String title = "Strangeness & Charm";
        String artist = "Florence & The Machine";
        String length = "5:49";
        Font fntT = new Font("Trebuchet MS", Font.PLAIN, 22);
        Font fntA = new Font("Trebuchet MS", Font.PLAIN, 18);
        Font fntL = new Font("Trebuchet MS", Font.PLAIN, 16);

        //Let's have a mediatracker.
        MediaTrack = new MediaTracker(this);

        // Gets album art and scales it to 150x150 pixels
        img = getImage(getCodeBase(), "0.jpg");
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