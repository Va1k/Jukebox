/**
 * Created with IntelliJ IDEA.
 * Author: Andreas
 * Date  : 28/03/13
 * Time  : 9:28 AM
 */
import java.applet.Applet;
import java.awt.*;

public class Jukebox extends Applet{
//***************************************//
    Image img;
    MediaTracker MediaTrack;
//***************************************//

    public void init() {
        // Sizes the applet on init to the preferred/intended dimensions
        resize(600,300);
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
        g2.drawString(artist,180,80);
        g2.setFont(fntL);
        g2.drawString(length,180,100);
    }

}