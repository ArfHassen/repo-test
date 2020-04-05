package java8;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author : arfaoui
 * @since : 07/01/2020
 * project : Test
 */
public class DebugingJAVA8 {
    static public  void main (String...args){
        Integer[] numbers = new Integer[]{2,3,4,5};
        //use peek to debug stream pipeline
        Arrays.stream(numbers).peek(n->System.out.println("from stream : "+n)).
                map(n->n+17).peek(n->System.out.println("after map : "+n)).
                filter(n->n%2==0).peek(n->System.out.println("after filter : "+n)).
                limit(3).peek(n->System.out.println("after limit : "+n)).collect(Collectors.toSet());
    }
}
