public class Audiotest {

    public static void main(String[] args) throws InterruptedException {
        // 1. Load all your audio into the HashMap first!
        // NOTE: If your loadALL() method is in a different class (like Game or Main),
        // change "SoundManager" to match your actual class name.
        Sound.loadALL();

        System.out.println("--- TEST 1: Standard Rhythm (30ms letters, 60ms spaces) ---");
        testText("Objection! The witness is clearly lying to the court!", "dBlip", 100, 20);

        Thread.sleep(1500); // Gives you a 1.5 second breather between tests

        System.out.println("--- TEST 2: Faster Rhythm (20ms letters, 40ms spaces) ---");
        testText("Objection! The witness is clearly lying to the court!", "dBlip", 15, 50);

        Thread.sleep(1500);

        System.out.println("--- TEST 3: Slower Rhythm (40ms letters, 80ms spaces) ---");
        testText("Objection! The witness is clearly lying to the court!", "dBlip", 20, 50);

        Thread.sleep(1500);

        System.out.println("--- TEST 4: Continuous Machine-Gun (30ms for everything) ---");
        testText("Objection! The witness is clearly lying to the court!", "dBlip", 1, 50);

        System.out.println("--- TESTS COMPLETE ---");
    }

    // This is your exact text method, but upgraded to accept custom speeds for testing.
    public static void testText(String text, String soundKey, int letterSpeed, int spaceSpeed) {

        Sound.loopSfx(soundKey);

        try {
            for (char c : text.toCharArray()) {
                System.out.print(c);

                if (c == ' ') {
                    Sound.stopSfx(soundKey);
                    Thread.sleep(spaceSpeed);
                    Sound.loopSfx(soundKey);
                } else {
                    Thread.sleep(letterSpeed);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Sound.stopSfx(soundKey);
        System.out.println("\n");
    }
}
