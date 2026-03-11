package EditingTools;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Add_Upcoming {
    public static void main(String[] args) {
    String code = "";
            Scanner input = new Scanner(System.in);
            System.out.println("Input headline:");
            String headline = input.nextLine();
            System.out.println("Input blurb:");
            String blurb = input.nextLine();
            addUpdate(headline,blurb);

            
    }
    public static void addUpdate(String headline, String blurb){
        int changed = 0;
        String code = "";
        String state = "Searching";
        try (Scanner in = new Scanner(new File("timeline.html"))) {
            for (String line = in.nextLine(); in.hasNextLine(); line = in.nextLine()) {
                if (changed != 8){
                    if (state.equals("Header") && line.contains("<h3>")) {
                        System.out.println(3333);
                        code += line + "\n";
                        code += "\t\t\t\t\t\t\t\t\t\t\t\t\t" + headline + "\n";
                        headline = in.nextLine();
                        state = "Body";
                        continue;
                    }else if (state.equals("Body") && line.contains("<p>")) {
                        code += line + "\n";
                        code += "\t\t\t\t\t\t\t\t\t\t\t\t" + blurb + "\n";
                        blurb = in.nextLine();
                        state = "Header";
                        continue;
                    }
                    if (line.contains("<!--First-->")) {
                        state = "Header";
                    }
                }
                code += line + "\n";
            }
            if(state.equals("Searching")){
                System.out.println("Add update failed although did not error");
                return;
            }
            PrintWriter pw = new PrintWriter("timeline2.html");
            pw.write(code);
            pw.close();

        } catch (Exception e) {
            System.out.println("Add update failed");
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}
