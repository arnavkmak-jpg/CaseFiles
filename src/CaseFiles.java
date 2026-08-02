import java.util.ArrayList;
//main class of the project
public class CaseFiles {
    public static void main(String[] args) {



        Evidence evi1 = new Evidence("Blue umbrella","An umbrella that is blue in colour and is still wet with strange marks",1);
        Evidence evi2 = new Evidence("Ball","Description 2",2);
        Evidence evi3 = new Evidence("Statue","Description 3",3);
        ArrayList<Evidence> evi = new ArrayList<>();


//Witness no 1
        Statement stat0 = new Statement("I did this and that and that",false,-1,"I am sure of what I did");//-1 indicates non contradictable statement, any positive number corresponds to the evidence id
        Statement stat1 = new Statement("I saw her at 9:30 pm",false,-1,"I am sure of what I did");
        Statement stat2 = new Statement("I picked up my blue umbrella and exited the house",true,1,"I am sure of what I did");
        ArrayList<Statement> s1 = new ArrayList<>();
        s1.add(stat0);
        s1.add(stat1);
        s1.add(stat2);
        Testimony t1 =new Testimony("Smores Pie",s1);

        DetectiveWitness d =new DetectiveWitness("Detective",t1);

//Witness no 2
        Statement stat3 = new Statement("test 3",false,-1,"I am sure of what I did");
        Statement stat4 = new Statement("test 4",true,2,"I am sure of what I did");
        Statement stat5 = new Statement("test 5",false,-1,"I am sure of what I did");
        ArrayList<Statement> s2 = new ArrayList<>();
        s2.add(stat3);
        s2.add(stat4);
        s2.add(stat5);
        Testimony t2 =new Testimony("Mr. Mare",s2);

//Witness no 3
        Statement stat6 = new Statement("test 6",false,-1,"I am sure of what I did");
        Statement stat7 = new Statement("test 7",false,-1,"I am sure of what I did");
        Statement stat8 = new Statement("test 8",true,3,"I am sure of what I did");
        ArrayList<Statement> s3 = new ArrayList<>();
        s3.add(stat6);
        s3.add(stat7);
        s3.add(stat8);
        Testimony t3 =new Testimony("Mr. Mare",s2);


        CulpritWitness c1= new CulpritWitness("Witness",t2,t3);

//        System.out.println(c1.getRole()+" "+c1.getTestimony1());
//        c1.finish(1);
//        System.out.println(c1.getRole()+" "+c1.getTestimony2());
//        c1.finish(2);

        Case mycase = new Case("The murder",d,c1,evi);

        mycase.start();







    }

}
