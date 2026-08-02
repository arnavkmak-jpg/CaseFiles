import java.util.Scanner;

public class CulpritWitness extends Witness{
    Scanner scanner = new Scanner(System.in);
    private Testimony testimony1;
    private Testimony testimony2;

    CulpritWitness(String role, Testimony testimony1, Testimony testimony2){
        super(role);
        this.testimony1=testimony1;
        this.testimony2=testimony2;
    }

    @Override
    void introduction() {
        System.out.println("The culprit");
        scanner.nextLine();
    }

    @Override
    void finish(int testimonyNumber) {
        if (testimonyNumber==1){
            System.out.println("You won't get me that easily");
            scanner.nextLine();
        } else if (testimonyNumber==2) {
            System.out.println("It can't be.....");
            scanner.nextLine();
        }
    }

    public Testimony getTestimony1() {
        return testimony1;
    }

    public Testimony getTestimony2() {
        return testimony2;
    }
}

