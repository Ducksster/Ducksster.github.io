package EditingTools;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Add_Update {
    public static void main(String[] args) {
    String code = "";
        try (Scanner in = new Scanner(new File("index.html")); Scanner input = new Scanner(System.in);) {
            System.out.println("Input headline:");
            String headline = input.nextLine();
            System.out.println("Input blurb:");
            String blurb = input.nextLine();
            String state = "Searching";
            int changed = 0;
            for (String line = in.nextLine(); in.hasNextLine(); line = in.nextLine()) {
                if (changed != 8){
                    if (state.equals("Header") && line.contains("<h2>")) {
                        code += line + "\n";
                        code += headline + "\n";
                        headline = in.nextLine();
                        state = "Body";
                        continue;
                    }else if (state.equals("Body") && line.contains("<td valign=\"top\">")) {
                        code += line + "\n";
                        code += blurb + "\n";
                        blurb = in.nextLine();
                        state = "Header";
                        continue;
                    }
                    if (line.contains("<!--First-->")) {
                        state = "Header";
                        continue;
                    }
                }
                code += line + "\n";
            }
            PrintWriter pw = new PrintWriter("index2.html");
            pw.write(code);
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}
