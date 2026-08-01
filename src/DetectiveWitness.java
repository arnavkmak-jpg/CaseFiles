public class DetectiveWitness extends Witness{

    private Testimony testimony1;

    DetectiveWitness(String role, Testimony testimony1){
        super(role);
        this.testimony1=testimony1;

    }
    @Override
    void introduction(){
        System.out.println("Detective");
    }
    @Override
    void finish(int testimonyNumber){
        System.out.println("My mistake");
    }

    @Override
    public String toString() {
        return getRole()+" "+testimony1;
    }

    public Testimony getTestimony1() {
        return testimony1;
    }
}
