import java.util.List;
import java.util.Scanner;

public abstract class ListingItems {

    public static Object pick(List<?> l, Scanner in){
        if(l == null || l.size() == 0)
            return null;

        Object picked = null;
        int i;
        while(picked == null) {
            try {
                i = 1;
                for(Object o : l) {
                    System.out.println(i + ". " + o.toString());
                    ++i;
                }
                System.out.println("Pick an item by entering the index next to it:");
                i = Integer.parseInt(in.nextLine().trim());
                picked = l.get(i-1);
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Index out of range; Try again.");
            }
            catch (NumberFormatException e){
                System.out.println("Not a valid index");
            }
        }
        return picked;
    }

    public static void print(List<?> l){
        if(l == null || l.size() == 0)
            return;

        int i = 1;
        for(Object o:l){
            System.out.println(i + ". " + o.toString());
            ++i;
        }

    }

}
