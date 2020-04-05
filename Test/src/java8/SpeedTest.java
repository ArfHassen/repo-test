package java8;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : arfaoui
 * @since : 16/01/2020
 * project : Test
 */
public class SpeedTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc","zrt","adf","ber","zed","aws");
        Set<String> set = list.stream().peek(s->System.out.println("from stream : "+s)).filter(x->x.startsWith("a")).map(String::toUpperCase).peek(s->System.out.println("after filter : "+s)).peek(s->System.out.println("after map : "+s)).collect(Collectors.toSet());
        System.out.println(set);
    }
}
