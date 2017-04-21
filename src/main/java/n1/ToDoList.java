package n1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 14.04.2017.
 */
public class ToDoList {
    private ArrayList<Smth> list = new ArrayList<>();
    private int count = 1;

    public ToDoList(){
    }

    public ToDoList(int n){
        for (int i = 0; i < n; i++) {
            list.add(new Smth("", count));
            count++;
        }
    }

    void add(String message){
        count++;
        list.add(new Smth(message, count));

    }

    void delete(int id){
        for (Smth l1: list
                ) { if(l1.getId()==id){list.remove(l1);
            break;}

        }
    }

    List<Smth> view(){
//        for (Smth smth : list) {
//            System.out.println(smth.toString());
//        }
        return list;
    }

    /*List<Smth> view(){
        return list;
    }*/

}
