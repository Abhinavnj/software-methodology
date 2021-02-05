/**
 * Class to instantiate a Kiosk and call its run method to begin the session.
 * It allows for multithreading as multiple Kiosks can be ran simultaneously.
 *
 * @author Jin Sebastian, Abhinav Sirohi
 */
public class RunProject1 {
    /**
     * Main method where the Kiosk is instantiated and ran
     *
     * @param args Command line arguments passed (not used)
     */
    public static void main(String[] args) {
        new Kiosk().run();
    }
}
