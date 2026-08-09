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
        Case.displayText("Fisher: Ah, Your Honor, The name is Artie Fisher.");
        Case.displayText("Fisher: I am a master art appraiser, I was Kara Larm's personal art consultant.");
    }

    @Override
    void finish(int testimonyNumber) {
        if (testimonyNumber==1){
            Case.displayText("Fisher: Hmph! You lawyers have no appreciation for the abstract!");
            Case.displayText("Fisher: Very well! I shall paint a picture so vivid, so undeniable...");
            Case.displayText("...that even a blind critic would see the truth!");
            Case.displayText("Fisher: Let me tell you exactly how the suspect reacted!");

        } else if (testimonyNumber==2) {
            Case.displayText("Fisher: *Grips his beret tightly* S-Silence! You uncultured swine!");
            Case.displayText("Fisher: She had no eye for true art! My brushstrokes were flawless! FLAWLESS!");
            Case.displayText("Fisher: But she threatened to ruin me! To destroy my pristine reputation in the art world!");
            Case.displayText("Fisher: (Tearing his beret in half) I couldn't let her do it!");
            Case.displayText("Fisher: I picked up that hideous, tacky glass vase and I... I...");
            Case.displayText("...I SHATTERED IT! I SHATTERED EVERYTHING!");
        }
    }

    public Testimony getTestimony1() {
        return testimony1;
    }

    public Testimony getTestimony2() {
        return testimony2;
    }
}

