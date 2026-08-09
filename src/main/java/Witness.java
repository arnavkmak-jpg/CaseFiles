//witness class to inculcate different behaviour patterns for both witness extended by DetectiveWitness and CulpritWitness subclasses
abstract class Witness {
    private String role;


    Witness(String role){
        this.role =role;
    }

    abstract void introduction();
    abstract void finish(int testimonyNumber);

    public String getRole() {
        return role;
    }
}
