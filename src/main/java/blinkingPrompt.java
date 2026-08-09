public class blinkingPrompt implements Runnable{

    private volatile boolean input=true;
    @Override
    public void run() {
        try {
            while (input==true){
                System.out.print("[ENTER]");
                Thread.sleep(500);
                System.out.print("\r       \r");
                Thread.sleep(500);
            }
        }catch (InterruptedException e){
            System.out.println("Thread Interrupted");
        }


    }
    public void stopTask(){
        this.input=false;
    }
}
