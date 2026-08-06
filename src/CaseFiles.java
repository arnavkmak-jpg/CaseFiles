import java.util.ArrayList;
//main class of the project
public class CaseFiles {
    public static void main(String[] args) {



        Evidence evi1 = new Evidence("1. Autopsy Report","A medical document stating the victim's rigor mortis indicates a time of death closer to 11:00 PM.",1);
        Evidence evi2 = new Evidence("2. Shattered Vase Pieces","Broken shards of the vase found near the smashed grandfather clock.",-1);
        Evidence evi3 = new Evidence("3. Kara's Coffee Receipt","A receipt from a local cafe stamped at 8:30 PM on the night of the murder.",-1);
        Evidence evi4 = new Evidence("4. Crime Scene Photo","A photo of the victim in the living room ,the rug under her is completely spotless with no blood splatter.",2);
        Evidence evi5 = new Evidence("5. Silk Dog Leash","A ridiculously expensive silk-made leash belonging to an Afghan Hound.",-1);
        Evidence evi6 = new Evidence("6. Steering Wheel Lock","A heavy and yellow anti-theft device found securely locked to the victim's car steering wheel parked in the driveway.",3);
        Evidence evi7 = new Evidence("7. Alana's Earmuffs","Heavy duty noise-canceling earmuffs that claim to block out 100% of background noise.",-1);
        ArrayList<Evidence> evi = new ArrayList<>();
        evi.add(evi1);
        evi.add(evi2);
        evi.add(evi3);
        evi.add(evi4);
        evi.add(evi5);
        evi.add(evi6);
        evi.add(evi7);


//Witness no 1
        Statement stat0 = new Statement("\"The defendant, Alana Larm, is undeniably the culprit!\"", false, -1, "\"I've locked up a hundred criminals and her alibi is way too convenient!\""); //-1 indicates non contradictable statement, any positive number corresponds to the evidence id
        Statement stat1 = new Statement("\"The victim, Kara Larm, was struck down right in the center of the living room at their house\"",false,-1,"\"The signs of a violent struggle were stamped all over the floor\"");
        Statement stat2 = new Statement("\"The suspect used a heavy household object to bludgeon the poor victim in the dead of night.\"",false,-1,"\"My forensic team swept the area clean, everything pointed straight to a domestic dispute.\"");
        Statement stat3 = new Statement("\"We know it happened at exactly 2:00 AM because the falling body smashed the grandfather clock!\"",true,1,"\"A broken clock never lies, pal. The hands stopped ticking right at the exact minute she died.\"");
        Statement stat4 = new Statement("\"No other timeline is even remotely possible. Case closed, Your Honor!\"",false,-1,"\"Now, if we're done here, can someone please check if the cafeteria has any doughnuts left?\"");
        ArrayList<Statement> s1 = new ArrayList<>();
        s1.add(stat0);
        s1.add(stat1);
        s1.add(stat2);
        s1.add(stat3);
        s1.add(stat4);
        Testimony t1 =new Testimony("Smores Pie",s1);
        DetectiveWitness d =new DetectiveWitness("Detective",t1);

//Witness no 2
        Statement stat5 = new Statement("\"I arrived at the scene of crime around 10:30 PM to authenticate a newly acquired, million-dollar masterpiece for Kara.\"", false, -1, "\"Art never sleeps, my friend! And when a newly discovered Renaissance piece is on the line, one does not wait for morning!\"");
        Statement stat6 = new Statement("\"I was working quietly in the adjacent gallery room when I heard a dreadful thud from the living room at exactly 11:00 PM.\"", false, -1, "\"Just dead silence, and then—SMASH! It sounded like a priceless sculpture meeting a tragic end on the hardwood floor.\"");
        Statement stat7 = new Statement("\"I peeked through the doorway and saw the defendant fiercely gripping a heavy bronze statue!\"", true, 2, "\"My eyes are my livelihood! It was definitely the 19th-century bronze 'Thinker' replica. A devastating weapon in the wrong hands!\"");
        Statement stat8 = new Statement("\"She was standing right over poor Kara's fallen body, frozen like a statue herself!\"", false, -1, "\"Oh, I'd recognize those ridiculous industrial earmuffs anywhere! She was caught red-handed.\"");
        Statement stat9 = new Statement("\"Fearing I would be the next victim of her artistic rampage, I quietly slipped out the back door and fled!\"", false, -1, "\"I am a lover of beauty, not a brawler! I had to preserve my own life—and my pristine velvet coat—from the madness!\"");
        ArrayList<Statement> s2 = new ArrayList<>();
        s2.add(stat5);
        s2.add(stat6);
        s2.add(stat7);
        s2.add(stat8);
        s2.add(stat9);
        Testimony t2 =new Testimony("Artie Fisher",s2);

//Witness no 3
        Statement stat10 = new Statement("\"I admit I was mistaken about the weapon, but I am absolutely certain the killer was Alana!\"", false, -1, "\"How can you be so sure? The room was dark and you were panicking!\"");
        Statement stat11 = new Statement("\"After she struck poor Kara, she didn't even stop to check if she was alive.\"", false, -1, "\"So she just struck her and immediately fled?\"");
        Statement stat12 = new Statement("\"She bolted out the front door of the manor and ran straight towards the driveway.\"", false, -1, "\"You had a clear view of the driveway from inside the gallery room?\"");
        Statement stat13 = new Statement("\"I was left all alone with the tragedy, paralyzed by fear for a brief moment.\"", false, -1, "\"So you just stood there doing nothing?\"");
        Statement stat14 = new Statement("\"Then, I watched from the window as she hopped into her car and sped off into the night!\"", true, 3, "\"You are absolutely certain you saw her drive away from the crime scene?\"");
        ArrayList<Statement> s3 = new ArrayList<>();
        s3.add(stat10);
        s3.add(stat11);
        s3.add(stat12);
        s3.add(stat13);
        s3.add(stat14);
        Testimony t3 =new Testimony("Mr. Mare",s2);


        CulpritWitness c1= new CulpritWitness("Witness",t2,t3);

        Case mycase = new Case("The murder",d,c1,evi);

        mycase.start();







    }

}
