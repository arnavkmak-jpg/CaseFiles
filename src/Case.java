import java.util.ArrayList;
import java.util.InputMismatchException;
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
        displayText(text,"dBlip");
    }
    public static void displayText(String text, String soundKey){
        Sound.loopSfx(soundKey);
        char[] letter = text.toCharArray();
        int letterSpeed;
        int spaceSpeed;
        try {
            if (soundKey.equals("lBlip")){
                letterSpeed=100;
                spaceSpeed=20;
            }
            else {
                letterSpeed=60;
                spaceSpeed=80;
            }
            for (char c:letter){
                System.out.print(c);
                System.out.flush();
                if (c==' '){
                    if (soundKey.equals("lBlip")) {
                        Sound.stopSfx(soundKey);
                        Thread.sleep(spaceSpeed);
                        Sound.loopSfx(soundKey);
                    } else {
                        Thread.sleep(spaceSpeed);
                    }
                }
                else {
                    Thread.sleep(letterSpeed);
                }
            }
            Sound.stopSfx(soundKey);
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
            String healthBar = "[ ! ]".repeat(penalty);
            displayText(currentStatements.get(i).getText());
                if (i==0){
                    System.out.println("[Enter: Next | P: Press | E: Evidence | I: How to Play]"+"[HEALTH:"+healthBar+"]");
                }
                else {
                    System.out.println("[Enter: Next | P: Press | E: Evidence | B: Back | I: How to Play]"+"[HEALTH:"+healthBar+"]");
                }
            String inp = scanner.nextLine().toLowerCase();
                Statement pressStatement = currentStatements.get(i);
                switch (inp){
                    case "p" -> {
                        Sound.startSfx("HoldIt");
                        try {
                            System.out.println("HOLD IT!");
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            System.out.println("Thread interrupted");
                        }
                        System.out.println();
                        System.out.println(witness.getRole()+" "+testimony.getName());
                        displayText(pressStatement.getPress());
                        if (i<currentStatements.size()-1){
                            i++;
                        }
                        else {
                            i=0;
                        }
                    }
                    case "e" -> {
                        System.out.println("Case Record:");
                        for (Evidence e:evidences){
                            System.out.println(e.getName()+" : "+e.getDescription());
                        }
                        try {
                            System.out.println("8. to go back");
                            System.out.println("Pick an evidence to present(1-8): ");
                            int pick = scanner.nextInt();
                            scanner.nextLine();
                            if (pick==8){
                                System.out.println("Cancelled evidence presentation");
                                System.out.println();
                            }
                            else if (pick>8){
                                System.out.println("Invalid option! please pick between 1-8");
                                System.out.println();
                            }
                            else {
                                Statement current = currentStatements.get(i);
                                if (pick==current.getMatchingEvidenceID()){
                                    System.out.println("OBJECTION!");
                                    contradictionCaught=true;
                                }
                                else {
                                    displayText("Judge: Objection overruled, defense!");
                                    displayText("Judge: That piece of evidence has absolutely nothing to do with this statement.");
                                    penalty--;
                                    healthBar = "[ ! ]".repeat(penalty);
                                    System.out.println("Health left: "+healthBar);
                                    System.out.println();

                                }
                            }

                        }catch (InputMismatchException e){
                            System.out.println("Invalid option! please pick between 1-8");
                            System.out.println();
                        }
                        }

                    case  "b" ->{
                        if (i==0){
                            System.out.println("Cannot go back from the first statement!");
                            System.out.println();
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
                            
                            [Enter] : Next statement (Press only when prompted to)                           
                            P       : Press statement (Reveals more information helpful when stuck)
                            E       : View/Present evidence (Presenting evidence on contradictable statements is key part)
                            B       : Previous statement 
                            I       : Instructions 
                            Health  : Displays by number of "[ ! ]"s
                            
                            Find lies in testimonies, open
                            evidence (E), and present the
                            correct item
                            
                           
                            You have 3 penalties; reaching
                            zero means Game Over!
                            
                            [ENTER] to Go back 
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
        Sound.loadALL();
        System.out.println("***********************");
        System.out.println("------CASE FILES--------");
        System.out.println("***********************");
        System.out.println("Press [ENTER] to Begin");
        scanner.nextLine();
        System.out.println("""
                            ---------- HOW TO PLAY ----------
                            
                            [Enter] : Next statement (Press only when prompted to)                           
                            P       : Press statement (Reveals more information helpful when stuck)
                            E       : View/Present evidence (Presenting evidence on contradictable statements is key part)
                            B       : Previous statement 
                            I       : Instructions 
                            Health  : Displays by number of "[ ! ]"s
                            
                            Find lies in testimonies, open
                            evidence (E), and present the
                            correct item
                            
                           
                            You have 3 penalties; reaching
                            zero means Game Over!
                            
                            [ENTER] to Proceed
                            """);
        scanner.nextLine();
        displayText("""
        Turnabout Forgery
        SEPTEMBER 2026
        Courtroom No. 4
        ""","lBlip");
        displayText("System: The trial begins for Alana Larm, accused of murdering her sister, Kara Larm");
        displayText("System: The prosecution's case relies entirely on a shattered grandfather clock frozen at 2:00 AM...");
        displayText("...locking the defendant into an airtight trap.");
        Sound.startSfx("Doors");
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }
        Sound.startSfx("Gavel");
        Sound.startBgm("Courtroom");
        displayText("Judge: The Courtroom is now in Session for the trial of Alana Larm...");
        displayText("...accused of the murder of her sister.");
        displayText("Judge: The prosecution may call its first witness.");
        displayText("*The Detective takes the stand*");
        getDetective().introduction();
        Sound.stopBgm("Courtroom");
        displayText("Prosecutor: Detective please begin your testimony.");
        displayText("Detective Pie: At once sir!");
        displayText("Judge: Defense please be ready for cross examination.");
        displayText("You: Yes, Your Honor");
        Sound.startSfx("Testimony");
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }
        Sound.startBgm("Moderato");
        runTestimony(getDetective().getTestimony1(),getDetective(),1);
        Sound.stopBgm("Moderato");
        if (penalty==0){
            System.out.println("The Judge has lost his patience! GAME OVER!");
            return;
        }
        Sound.startSfx("dObjection");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }
        Sound.startBgm("Objection");
        displayText("You: You claim the victim died at 2:00 AM because of the shattered grandfather clock.");
        displayText("You: But take a look at this Autopsy Report!");
        displayText("You: The estimated time of death is clearly listed as exactly 11:00 PM!");
        Sound.startSfx("Shocked");
        displayText("Detective Pie: *Shocked reaction*");
        displayText("Detective Pie: No, that's impossible! Are you telling me the clock lied to me?!");
        Sound.startSfx("Crowd");
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }
        Sound.startSfx("Gavel");
        displayText("Judge: Order! Order in the court! Detective Pie...");
        displayText("...are you telling me your entire investigation was based on a broken piece of furniture?!");
        getDetective().finish(1);
        Sound.stopBgm("Objection");
        displayText("System: Detective Smores Pie hastily retreats from the witness stand...");
        displayText("...clutching his stomach and muttering about doughnuts.");
        displayText("System: The prosecution's airtight 2:00 AM timeline is officially in shambles.");
        displayText("Judge: Prosecution! Your lead detective just fled,...");
        displayText("...and his timeline is completely upside down!");
        displayText("Judge: Do you have any actual proof, or should I dismiss this case right now?");
        displayText("Prosecutor: *Sweating* I apologize, Your Honor.");
        displayText("Prosecutor: We concede the 2:00 AM timeline is completely inaccurate.");
        Sound.startSfx("Slam");
        displayText("Prosecutor:(Slamming the desk) However, we have a reliable eyewitness!");
        displayText("Prosecutor: One who was at the victim's premises that very night");
        displayText("Prosecutor: The prosecution calls the victim's private art appraiser,...");
        displayText("...Mr. Artie Fisher to the stand!");
        displayText("System: The court now calls upon next witness");
        displayText("System: The courtroom doors swing open.");
        Sound.startSfx("Doors");
        displayText("System: A flamboyant man with a magnifying glass takes the witness stand.");
        displayText("Judge: Witness, state your name and occupation for the court.");
        getCulprit().introduction();
        displayText("Prosecutor: Mr. Fisher, please tell the court what you witnessed...");
        displayText("...at the scene of crime on the night of the incident.");
        displayText("Fisher: Certainly. It paints a rather tragic picture, but I shall paint the canvas for you");
        Sound.startSfx("Testimony");
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }
        Sound.startBgm("Moderato");
        runTestimony(getCulprit().getTestimony1(),getCulprit(),1);
        Sound.stopBgm("Moderato");
        if (penalty==0){
            System.out.println("The Judge has lost his patience! GAME OVER!");
            return;
        }
        Sound.startSfx("dObjection");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }
        Sound.startBgm("Objection");
        displayText("You: You claim the defendant was holding a heavy bronze statue.");
        displayText("You: But take a close look at these Shattered Vase Pieces!");
        displayText("You: The actual murder weapon was clearly a shattered glass vase!");
        Sound.startSfx("Shocked");
        displayText("Fisher: *Gasps* A-A glass vase?!");
        displayText("Fisher: But... the shadows in that room were terribly deceptive!");
        displayText("Judge: Mr. Fisher! You are a master art appraiser!");
        displayText("Judge: Shouldn't you easily be able to tell glass from bronze?!");
        displayText("Fisher: W-well, modern art is highly interpretive, Your Honor!");
        Sound.startSfx("Slam");
        Sound.startSfx("pObjection");
        displayText("Prosecutor: (Slamming the desk) OBJECTION!");
        displayText("Prosecutor: The defense is grasping at straws!");
        displayText("Prosecutor: The witness merely misidentified the weapon in a moment of panic.");
        displayText("Prosecutor: Furthermore, there was a shattered grandfather clock at the scene!");
        displayText("Prosecutor: How can you definitively prove the vase was the weapon and not the clock?!");
        displayText("You: That's simple, Prosecutor.");
        displayText("You: Mr. Fisher just testified that the killer was 'fiercely gripping' the weapon.");
        displayText("You: Have you ever tried to pick up and swing a 150-pound grandfather clock?");
        displayText("Prosecutor: *Gack...!*");
        displayText("Prosecutor: T-That doesn't change the fact that he saw the defendant at the scene!");
        Sound.stopBgm("Objection");
        displayText("Judge: Hmm... I suppose that is true.");
        displayText("Judge: Mr. Fisher, you will need to clarify your claims.");
        displayText("Judge: Please testify again, in detail, about what you saw next.");
        getCulprit().finish(1);
        displayText("Judge: Very well, Mr. Fisher. Proceed.");
        Sound.startSfx("Testimony");
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }
        Sound.startBgm("Allegro");
        runTestimony(getCulprit().getTestimony2(),getCulprit(),2);
        Sound.stopBgm("Allegro");
        if (penalty==0){
            System.out.println("The Judge has lost his patience! GAME OVER!");
            return;
        }
        Sound.startSfx("dObjection");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }
        displayText("You: You just testified that you saw Alana hop into her car and speed off into the night.");
        Sound.startBgm("Pursuit");
        displayText("You: But that is physically impossible!");
        displayText("Fisher: I-Impossible? What do you mean? My eyes are flawless!");
        displayText("You: Take a look at this Steering Wheel Lock!");
        displayText("You: The police found this firmly attached to the defendant's steering wheel that night!");
        displayText("You: She couldn't have driven that car an inch, let alone 'sped off'!");
        Sound.startSfx("Shocked");
        displayText("Fisher: *GAAAASP!*");
        Sound.startSfx("Gavel");
        displayText("Judge: Mr. Fisher! Explain yourself immediately!");
        displayText("Judge: You distinctly claimed you saw the defendant drive away!");
        Sound.startSfx("Shocked");
        displayText("Fisher: *Sweating profusely* I... I must have been hallucinating!");
        displayText("Fisher: The trauma of the evening... it played tricks on my fragile artistic mind!");
        displayText("Prosecutor: Y-Yes! Exactly! The witness was simply disoriented by the shock!");
        Sound.startSfx("dObjection");
        displayText("You: OBJECTION!");
        displayText("You: This wasn't a hallucination, and it wasn't a trick of the light!");
        displayText("You: You deliberately fabricated this entire story, didn't you, Mr. Fisher?!");
        displayText("Fisher: H-How dare you?! Why would I do such a thing?!");
        Sound.stopBgm("Pursuit");
        Sound.startSfx("Gavel");
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
        Sound.startSfx("Shocked");
        displayText("Fisher: *Trembling violently* N-No... stop...");
        displayText("You: And not just any fake. It was YOUR forgery! She was going to expose you!");
        getCulprit().finish(2);
        displayText("System: *Artie Fisher falls to his knees, sobbing dramatically into his ruined beret.*");
        displayText("Judge: My word... To think a dispute over a forged painting led to murder.");
        displayText("Prosecutor: *Sighs* The prosecution withdraws its indictment against the defendant.");
        displayText("Judge: Bailiff! Take Mr. Fisher into custody immediately.");
        Sound.stopBgm("Truth");
        displayText("System: *The bailiffs drag a weeping Artie Fisher out of the courtroom.*");
        displayText("Judge: This court sees no reason to further prolong this trial.");
        displayText("Judge: The true culprit has confessed, and the defense has brilliantly uncovered the truth.");
        displayText("Judge: Therefore, this court finds the defendant, Alana Larm...");
        Sound.startSfx("NotGuilty");
        displayText("NOT GUILTY");
        Sound.startSfx("Victory");
        displayText("System: *Confetti falls as the courtroom erupts into deafening cheers!*");
        displayText("Judge: Court is adjourned!");
        Sound.startSfx("Gavel");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }
        displayText("""
                Defendent Lobby
                September 2026
                Courtroom no. 4
                ""","lBlip");
        Sound.startBgm("Lobby");
        displayText("Alana: I... I can't believe it. It's really over.");
        displayText("You: You're safe now, Alana. Fisher will pay for what he did to Kara.");
        displayText("Alana: Thank you... You believed in me when the entire world pointed the finger at me.");
        displayText("Alana: I still have a long way to go to process losing my sister...");
        displayText("Alana: But thanks to you, I can finally seek justice for her in peace.");
        displayText("Alana: *Smiles* I think I'll finally take off these earmuffs now. The noise isn't so scary anymore.");
        displayText("You: I'm just glad I could help you find the truth, Alana.");
        displayText("You: (It was a tough case, but seeing that smile makes it all worth it.)");
        Sound.stopBgm("Lobby");
        Sound.startSfx("TheEnd");
        try {
            Thread.sleep(4000);
        }catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }
        System.out.println("""
                ******************************************
                           🌟 CONGRATULATIONS! 🌟
                        You have successfully completed:
                -------------TURNABOUT FORGERY-------------
                *******************************************
                            Thank you for playing!
                *******************************************
                """);


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
                Sound.startSfx("Shocked");
                displayText("Fisher: *GAAAAAAAAASP!*");
                Sound.startBgm("Truth");
            }
            else {
                displayText("Judge: That makes absolutely no sense, defense!");
                displayText("Judge: I am docking a penalty for wasting the court's time!");
                penalty--;
                System.out.println("Penalties left: " + "[ ! ]".repeat(penalty));
            }

        }

        }
    }



