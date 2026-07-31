import java.util.ArrayList;

public class CaseFiles {
    public static void main(String[] args) {

        Statement stat0 = new Statement("I did this and that and that",false,-1);
        Statement stat1 = new Statement("I saw her at 9:30 pm",false,-1);
        Statement stat2 = new Statement("I picked up my blue umbrella and exited the house",true,2);

        Evidence evi = new Evidence("Blue umbrella","An umbrella that is blue in colour and is still wet with strange marks",2);
        ArrayList<Statement> s = new ArrayList<>();

        s.add(stat0);
        s.add(stat1);
        s.add(stat2);

        Testimony t =new Testimony("Detective",s);

        System.out.println(t);

    }

}
