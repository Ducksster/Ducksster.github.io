package EditingTools;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Add_Story {
    public static void main(String[] args) {
    String timeLine = "";
    String story = "";
        try (Scanner in = new Scanner(new File("timeline.html")); Scanner input = new Scanner(System.in);) {
            System.out.println("Input headline:");
            String headline = input.nextLine();
            System.out.println("Input date:");
            String date = input.nextLine();
            System.out.println("Input internal title (used for [titlt].png] :");
            String title = input.nextLine();
            for (String string = in.nextLine(); in.hasNext(); string = in.nextLine()) {
                if(string.contains("<!--Start of stories-->")){
                    timeLine += string + "\n" + "<!--"+ headline +"-->" + "\n" ;
                    try (Scanner savedIn = new Scanner(new File("EditingTools/saved.html"))) {
                        for (String line = savedIn.nextLine(); savedIn.hasNext(); line = savedIn.nextLine()) {
                            if(line.contains("<!--Date <br> title-->")){
                                timeLine += ("\t\t\t\t\t\t\t\t\t\t\t\t") + date + "<br>" + headline + "\n";
                                continue;
                            }
                            if(line.contains("<!--image button-->")){
                                timeLine += ("\t\t\t\t\t\t\t\t\t\t\t\t") + "<a href=\"Stories/"+ title +".html\"><img src=\"images/"+ title +".png\"></a>\n";
                                continue;
                            }
                            if(line.contains("<!--end-->")){
                                savedIn.nextLine();
                                break;
                            }
                            timeLine += line + "\n";
                        }

                        
                        for (String line = savedIn.nextLine(); savedIn.hasNext(); line = savedIn.nextLine()) {
                            if(line.contains("<!--headline-->")){
                                story += "\t\t\t\t\t\t" + headline + "\n";
                                continue;
                            }
                            if(line.contains("<!--image-->")){
                                story += "\t\t\t\t\t<img src=\"../images/"+title+".png\" class=\"Story-header\">\n";
                                continue;
                            }
                            if(line.contains("<!--end-->")){

                                break;
                            }
                            story += line + "\n";
                        }
                        continue;
                    }
                }
                timeLine += string + "\n";
            }

            PrintWriter pw = new PrintWriter("timeline2.html");
            pw.write(timeLine);
            pw.close();

            pw = new PrintWriter("Stories/"+title+".html");
            pw.write(story);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}
