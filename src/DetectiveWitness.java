import java.util.Scanner;

public class DetectiveWitness extends Witness{

    private Testimony testimony1;
    Scanner scanner=new Scanner(System.in);

    DetectiveWitness(String role, Testimony testimony1){
        super(role);
        this.testimony1=testimony1;

    }
    @Override
    void introduction(){
        System.out.println("Detective intro");
        scanner.nextLine();

    }
    @Override
    void finish(int testimonyNumber){
        System.out.println("My mistake");
        scanner.nextLine();
    }

    @Override
    public String toString() {
        return getRole()+" "+testimony1;

    }

    public Testimony getTestimony1() {
        return testimony1;
    }
}
