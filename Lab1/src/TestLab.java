public class TestLab {

public static void main(String [] args) {
    Player p1 = new Player("Derek", "defence", 10);

    System.out.println(p1.getName());
    System.out.println(p1.getPosition());
    System.out.println(p1.getJerseyNum());

    p1.setName("John");
    p1.setPosition("goalie");
    p1.setJerseyNum(11);

    System.out.println(p1.getName());
    System.out.println(p1.getPosition());
    System.out.println(p1.getJerseyNum());

    System.out.println(p1);

    Player p2 = new Player("John", "goalie", 11);   

    if (p1.equals(p2)) {
        System.out.println("Same player");
    } else {
        System.out.println("Different player");
    }
    }
}