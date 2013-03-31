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
        resize(400,300);
    }

    public void paint(Graphics g) {
        // Basic Image
        MediaTrack = new MediaTracker(this);
        img = getImage(getCodeBase(), "0.jpg");
        MediaTrack.addImage(img,0);
        g.drawImage(img, 0, 0, this);
    }

}