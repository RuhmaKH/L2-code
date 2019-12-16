import java.util.ArrayList;
import java.awt.*;
import java.lang.*;

public class Interact {
    private static String state;
    private static String talk;
    private static int cursor;
    private static final Image imageTalk = Images.getImage("Dialogue");;
    private static final Image imageCursor = Images.getImage("Cursor");;

    public static String getState(){
        return state;
    }

    public static void choose () {
        state = "choose";
    }

    public static void end () {
        state = "end";
    }

    public static void play () {
        state = "play";
    }

    public static void meet () {
        state = "meet";
    }
    
    public static void talk (String str) {
        state = "talk";
        talk = str;
        while (state != "meet"){
            Monde.world.repaint();
            System.out.println("LOUL");
            try{
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
             }
        }
    }

    public static void shop (String str) {
        state = "shop";
        talk = str;
        while (state != "choose"){
            Monde.world.repaint();
            try{
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
             }
        }
    }

    public static void cursorUp () {
        cursor++;
    }

    public static void cursorDown () {
        cursor--;
    }
    
    public static int getCursor () {
        return cursor;
    }

    private static ArrayList<String> getTalk () {
        String temp = "";
        String[] disc = talk.split(" ");
        String[] split = new String[2];
        ArrayList<String> talk = new ArrayList<String>();
        for (String s : disc) {
            if (s.contains("\n")) {
                split = s.split("\n");
                temp += split[0];
                talk.add(temp);
                if (split.length > 1) {
                    temp = split[1] + " ";
                }
                continue;
            }
            if ( (temp + s).length() > 64 ) {
                talk.add(temp);
                temp = "";
            }
            temp += s + " ";
        }
        talk.add(temp);
        return talk;
    }

    public static void dessinerTalk (Graphics g) {
        int k = 0;
        ArrayList<String> talk = getTalk();
        String space = "";
        int height;
        if (talk.size() < 5)
            height = 200;
        else
            height = 200 + 20 * (talk.size() - 4);
        int size = Monde.taille * Monde.tailleCase;
        g.setFont(new Font ("Trajan", Font.BOLD, 22));
        g.setColor(new Color(95, 0, 0));
        //############# BOITE DE DIALOGUE #############
        g.fillRect(15, size - height, size - 30, height - 15);
        g.drawImage(imageTalk, 20, size - height + 3, size - 40, height - 20, Monde.world);
        g.setColor(Color.BLACK);
        //############# DIALOGUE #############
        for (int i = 0; i < talk.size(); i++){
            if (talk.get(i).contains("\t")){
                if (k == cursor && state == "shop")
                    g.drawImage(imageCursor, 55, size - height + 37 + 25 * i, Monde.world);
                k++;
                space = "    ";
            }
            else
                space = "";
            g.drawString(space + talk.get(i), 55, size - height + 50 + 25 * i);
        }
    }
}