import java.util.ArrayList;
import java.util.Scanner;

public class Case {
    public static Scanner scanner = new Scanner(System.in);
    private String caseName;
    private DetectiveWitness detective;
    private CulpritWitness culprit;
    ArrayList<Evidence> evidences;
    private int penalty;

    Case(String CaseName, DetectiveWitness detective, CulpritWitness culprit, ArrayList<Evidence> evidences){
        this.caseName=CaseName;
        this.detective=detective;
        this.culprit=culprit;
        this.evidences=evidences;
        this.penalty=3;

    }
    public static void displayText(String text){
        char[] letter = text.toCharArray();
        try {
            for (char c:letter){
                System.out.print(c);
                Thread.sleep(1);
            }
            System.out.println();
            blinkingPrompt bp = new blinkingPrompt();
            Thread thread = new Thread(bp);
            thread.start();
            scanner.nextLine();
            bp.stopTask();
            thread.join();
            System.out.print("\r         \r");
        }catch (InterruptedException e){
            System.out.println("Thread Interrupted");
        }


    }
    public void runTestimony(Testimony testimony, Witness witness, int testimonyNumber){
        ArrayList<Statement> currentStatements = testimony.getStatements();
        boolean contradictionCaught=false;
        int i = 0;
        while (penalty>0&&contradictionCaught==false){
            System.out.println(witness.getRole()+" "+testimony.getName());
            String healthBar = "❤️".repeat(penalty);
            displayText(currentStatements.get(i).getText());
                if (i==0){
                    System.out.println("[Enter: Next | P: Press | E: Evidence | I: How to Play]"+"[HEALTH]:"+healthBar);
                }
                else {
                    System.out.println("[Enter: Next | P: Press | E: Evidence | B: Back | I: How to Play]"+"[HEALTH:"+healthBar+"]");
                }
            String inp = scanner.nextLine().toLowerCase();
                Statement pressStatement = currentStatements.get(i);
                switch (inp){
                    case "p" -> {
                        System.out.println(witness.getRole()+" "+testimony.getName());
                        if (i<currentStatements.size()-1){
                            displayText(pressStatement.getPress());
                            i++;
                        }
                        else {
                            displayText(pressStatement.getPress());
                            i=0;
                        }
                    }
                    case "e" -> {
                        System.out.println("Case Record:");
                        for (Evidence e:evidences){
                            System.out.println(e.getName()+" : "+e.getDescription());
                        }
                        System.out.println("8. to go back");
                        System.out.println("Pick an evidence to present(1-8): ");
                        int pick = scanner.nextInt();
                        scanner.nextLine();
                        if (pick==8){
                            System.out.println("Cancelled evidence presentation");
                        }
                        else {
                            Statement current = currentStatements.get(i);
                            if (pick==current.getMatchingEvidenceID()){
                                System.out.println("OBJECTION!");
                                contradictionCaught=true;
                            }
                            else {
                                displayText("Judge: Objection overruled, defense!");
                                displayText("That piece of evidence has absolutely nothing to do with this statement.");
                                penalty--;
                                healthBar = "❤️".repeat(penalty);
                                System.out.println("Health left: "+healthBar);
                                System.out.println();

                            }
                        }

                    }
                    case  "b" ->{
                        if (i==0){
                            System.out.println("Cannot go back from the first statement!");
                        }
                        else
                            i--;
                    }
                    case "" -> {
                        if (i<currentStatements.size()-1){
                            i++;
                        }
                        else{
                            i=0;
                        }
                    }
                    case "i" -> {
                        System.out.println("""
                            ---------- HOW TO PLAY ----------
                            
                            [Enter] : Next statement, press only when prompted to                           
                            P       : Press statement
                            E       : View/Present evidence
                            B       : Previous statement
                            I       : Instructions
                            
                            Find lies in testimonies, open
                            evidence (E), and present the
                            correct item
                            
                           
                            You have 3 penalties; reaching
                            zero means Game Over!
                            
                            """);
                    scanner.nextLine();
                    }
                    default -> System.out.println("Invalid input! Please input P/E/B/Enter");

                }
        }
    }

    public String getCaseName() {
        return caseName;
    }

    public CulpritWitness getCulprit() {
        return culprit;
    }

    public DetectiveWitness getDetective() {
        return detective;
    }

    public int getPenalty() {
        return penalty;
    }

    public void start(){
        System.out.println("***********************");
        System.out.println("------CASE FILE--------");
        System.out.println("***********************");
        System.out.println("Press [ENTER] to Begin");
        scanner.nextLine();
        System.out.println("""
                            ---------- HOW TO PLAY ----------
                            
                            [Enter] : Next statement , press only when prompted to                            
                            P       : Press statement
                            E       : View/Present evidence
                            B       : Previous statement
                            I       : Instructions
                            
                            Find lies in testimonies, open
                            evidence (E), and present the
                            correct item
                            
                           
                            You have 3 penalties; reaching
                            zero means Game Over!
                            
                            PRESS [ENTER] TO PROCEED                            
                            """);
        scanner.nextLine();
        displayText("""
        Turnabout Forgery
        SEPTEMBER 2026
        Courtroom No. 4
        """);
        displayText("System: The trial begins for Alana Larm, accused of murdering her sister, Kara Larm");
        displayText("System: The prosecution's case relies entirely on a shattered grandfather clock frozen at 2:00 AM");
        displayText("System: locking the defendant into an airtight trap.");
        displayText("Judge: The Courtroom is now in Session for the trial of Alana Larm,");
        displayText("Judge: accused of the murder of her sister.");
        displayText("Judge: The prosecution may call its first witness.");
        displayText("*The Detective takes the stand*");
        getDetective().introduction();
        displayText("Prosecutor: Detective please begin your testimony.");
        displayText("Detective Pie: At once sir!");
        displayText("Judge: Defense please be ready for cross examination.");
        displayText("You: Yes, Your Honor");
        runTestimony(getDetective().getTestimony1(),getDetective(),1);
        if (penalty==0){
            System.out.println("The Judge has lost his patience! GAME OVER!");
            return;
        }
        displayText("You: You claim the victim died at 2:00 AM because of the shattered grandfather clock.");
        displayText("You: But take a look at this Autopsy Report!");
        displayText("You: The estimated time of death is clearly listed as exactly 11:00 PM!");
        displayText("Detective Pie: *Shocked reaction*");
        displayText("Detective Pie: No, that's impossible! Are you telling me the clock lied to me?!");
        displayText("Judge: Order! Order in the court! Detective Pie,");
        displayText("Judge: are you telling me your entire investigation was based on a broken piece of furniture?!");
        getDetective().finish(1);
        displayText("System: Detective Smores Pie hastily retreats from the witness stand,");
        displayText("System: clutching his stomach and muttering about doughnuts.");
        displayText("System: The prosecution's airtight 2:00 AM timeline is officially in shambles.");
        displayText("Judge: Prosecution! Your lead detective just fled,");
        displayText("Judge: and his timeline is completely upside down!");
        displayText("Judge: Do you have any actual proof, or should I dismiss this case right now?");
        displayText("Prosecutor: *Sweating* I apologize, Your Honor.");
        displayText("Prosecutor: We concede the 2:00 AM timeline is completely inaccurate.");
        displayText("Prosecutor:(Slamming the desk) However, we have a reliable eyewitness!");
        displayText("Prosecutor: One who was at the victim's premises that very night");
        displayText("Prosecutor: The prosecution calls the victim's private art appraiser,");
        displayText("Prosecutor: Mr. Artie Fisher to the stand!");
        displayText("System: The court now calls upon next witness");
        displayText("System: The courtroom doors swing open.");
        displayText("System: A flamboyant man with a magnifying glass takes the witness stand.");
        displayText("Judge: Witness, state your name and occupation for the court.");
        getCulprit().introduction();
        displayText("Prosecutor: Mr. Fisher, please tell the court what you witnessed...");
        displayText("Prosecutor: ...at the scene of crime on the night of the incident.");
        displayText("Fisher: Certainly. It paints a rather tragic picture, but I shall paint the canvas for you");
        runTestimony(getCulprit().getTestimony1(),getCulprit(),1);
        if (penalty==0){
            System.out.println("The Judge has lost his patience! GAME OVER!");
            return;
        }
        displayText("You: You claim the defendant was holding a heavy bronze statue.");
        displayText("You: But take a close look at this crime scene photo!");
        displayText("You: The actual murder weapon was clearly a shattered glass vase!");
        displayText("Fisher: *Gasps* A-A glass vase?!");
        displayText("Fisher: But... the shadows in that room were terribly deceptive!");
        displayText("Judge: Mr. Fisher! You are a master art appraiser!");
        displayText("Judge: Shouldn't you easily be able to tell glass from bronze?!");
        displayText("Fisher: W-well, modern art is highly interpretive, Your Honor!");
        displayText("Prosecutor: (Slamming the desk) OBJECTION!");
        displayText("Prosecutor: The defense is grasping at straws!");
        displayText("Prosecutor: The witness merely misidentified the weapon in a moment of panic.");
        displayText("Prosecutor: That doesn't change the fact that he saw the defendant at the scene!");
        displayText("Judge: Hmm... I suppose that is true.");
        displayText("Judge: Mr. Fisher, you will need to clarify your claims.");
        displayText("Judge: Please testify again, in detail, about what you saw next.");
        getCulprit().finish(1);
        displayText("Judge: Very well, Mr. Fisher. Proceed.");
        runTestimony(getCulprit().getTestimony2(),getCulprit(),2);
        if (penalty==0){
            System.out.println("The Judge has lost his patience! GAME OVER!");
            return;
        }
        displayText("Judge: Mr. Fisher! Explain yourself immediately!");
        displayText("Judge: You distinctly claimed you saw the defendant drive away!");
        displayText("Fisher: *Sweating profusely* I... I must have been hallucinating!");
        displayText("Fisher: The trauma of the evening... it played tricks on my fragile artistic mind!");
        displayText("Prosecutor: Y-Yes! Exactly! The witness was simply disoriented by the shock!");
        displayText("You: OBJECTION!");
        displayText("You: This wasn't a hallucination, and it wasn't a trick of the light!");
        displayText("You: You deliberately fabricated this entire story, didn't you, Mr. Fisher?!");
        displayText("Fisher: H-How dare you?! Why would I do such a thing?!");
        displayText("Judge: That is a bold accusation, defense.");
        displayText("Judge: Why would a respectable art appraiser lie on the stand to frame the defendant?");
        displayText("You: The answer is quite simple, Your Honor.");
        displayText("You: There is only one logical reason why he would want Alana to take the fall!");
        displayText("Judge: Very well. Let's hear it then!");
        finalQuestion();
        if (penalty==0){
            System.out.println("The Judge has lost his patience! GAME OVER!");
            return;
        }
        displayText("Fisher: N-No! You're wrong! I am a preserver of beauty, not a destroyer!");
        displayText("You: You didn't just go there to appraise a masterpiece, did you?");
        displayText("You: Kara Larm figured it out. She realized the million-dollar painting was a fake!");
        displayText("Fisher: *Trembling violently* N-No... stop...");
        displayText("You: And not just any fake. It was YOUR forgery! She was going to expose you!");
        getCulprit().finish(2);
        displayText("System: *Artie Fisher falls to his knees, sobbing dramatically into his ruined beret.*");
        displayText("Judge: My word... To think a dispute over a forged painting led to murder.");
        displayText("Prosecutor: *Sighs* The prosecution withdraws its indictment against the defendant.");
        displayText("Judge: Bailiff! Take Mr. Fisher into custody immediately.");
        displayText("System: *The bailiffs drag a weeping Artie Fisher out of the courtroom.*");
        displayText("Judge: This court sees no reason to further prolong this trial.");
        displayText("Judge: The true culprit has confessed, and the defense has brilliantly uncovered the truth.");
        displayText("Judge: Therefore, this court finds the defendant, Alana Larm...");
        displayText("Judge: NOT GUILTY.");
        displayText("System: *Confetti falls as the courtroom erupts into deafening cheers!*");
        displayText("Judge: Court is adjourned!");
        displayText("""
                Defendent Lobby
                September 2026
                Courtroom no. 4
                """);
        displayText("Alana: I... I can't believe it. It's really over.");
        displayText("You: You're safe now, Alana. Fisher will pay for what he did to Kara.");
        displayText("Alana: Thank you... You believed in me when the entire world pointed the finger at me.");
        displayText("Alana: I still have a long way to go to process losing my sister...");
        displayText("Alana: But thanks to you, I can finally seek justice for her in peace.");
        displayText("Alana: *Smiles* I think I'll finally take off these earmuffs now. The noise isn't so scary anymore.");
        displayText("You: I'm just glad I could help you find the truth, Alana.");
        displayText("You: (It was a tough case, but seeing that smile makes it all worth it.)");
        System.out.println("\n******************************************");
        System.out.println(" 🌟 CONGRATULATIONS! 🌟");
        System.out.println(" You have successfully completed:");
        System.out.println("     TURNABOUT FORGERY");
        System.out.println("*********************************************");
        System.out.println(" Thank you for playing!");
        System.out.println("*******************************************\n");

    }
    public void finalQuestion(){
        int choice=0;
        while (choice!=2&&penalty>0){
            displayText("Judge: What is the witness's true motive for framing the defendant?!");
            System.out.println("\n***************************************");
            System.out.println(" WHY IS ARTIE FISHER FRAMING ALANA?");
            System.out.println(" 1. He was paid off by the real killer.");
            System.out.println(" 2. He is the actual murderer!");
            System.out.println(" 3. He hates industrial earmuffs.");
            System.out.println("*****************************************");
            System.out.print("Select your answer (1-3): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice==2){
                displayText("You: Because YOU are the actual murderer, Artie Fisher!");
                displayText("Fisher: *GAAAAAAAAASP!*");
            }
            else {
                displayText("Judge: That makes absolutely no sense, defense!");
                displayText("Judge: I am docking a penalty for wasting the court's time!");
                penalty--;
                System.out.println("Penalties left: " + penalty);
            }

        }

        }
    }



