package java8;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author : arfaoui
 * @since : 31/01/2020
 * project : Test
 */
public class Kata {
/*@Easy @Test
public void simplestStringJoin() {
    List<Customer> customerList = this.mall.getCustomerList();

    *//**
     * Implement a {@link Collector} which can create a String with comma separated names shown in the assertion.
     * The collector will be used by serial stream.
     *//*
    Supplier<StringBuilder> supplier = ()->new StringBuilder();
    BiConsumer<StringBuilder, String> accumulator = (c, s)->c.append(s).append(",");
    BinaryOperator<StringBuilder> combiner = null;
    Function<StringBuilder, String> finisher = c->c.toString().substring(0,c.length()-1);

    Collector<String, ?, String> toCsv =
            new CollectorImpl<String,StringBuilder,String>(supplier, accumulator, combiner, finisher, Collections.emptySet());
    String nameAsCsv = customerList.stream().map(Customer::getName).collect(toCsv);
    assertThat(nameAsCsv, is("Joe,Steven,Patrick,Diana,Chris,Kathy,Alice,Andrew,Martin,Amy"));
}

    @Difficult @Test
    public void mapKeyedByItems() {
        List<Customer> customerList = this.mall.getCustomerList();

        *//**
         * Implement a {@link Collector} which can create a {@link Map} with keys as item and
         * values as {@link Set} of customers who are wanting to buy that item.
         * The collector will be used by parallel stream.
         *//*
        Supplier<Map<String, Set<String>>> supplier = ()->new HashMap<>();
        BiConsumer<Map<String, Set<String>>, Customer> accumulator = (m,c)->{
            List<Item> items = c.getWantToBuy();
            for(Item item : items){
                Set<String> customorNames = m.get(item.getName());
                if(customorNames == null){
                    customorNames = new HashSet<>();
                    m.put(item.getName(), customorNames);
                }
                customorNames.add(c.getName());
            }
        };
        BinaryOperator<Map<String, Set<String>>> combiner = (m,l)->{
            Map<String, Set<String>> map = new HashMap<>(m);
            for(Map.Entry<String, Set<String>> entry : l.entrySet()){
                if(map.containsKey(entry.getKey())){
                    map.get(entry.getKey()).addAll(entry.getValue());
                }
                else{
                    map.put(entry.getKey(), entry.getValue());
                }
            }
            return map;
        };
        Function<Map<String, Set<String>>, Map<String, Set<String>>> finisher = Function.identity();

        Collector<Customer, ?, Map<String, Set<String>>> toItemAsKey =
                new CollectorImpl<>(supplier, accumulator, combiner, finisher, EnumSet.of(
                        Collector.Characteristics.CONCURRENT,
                        Collector.Characteristics.IDENTITY_FINISH));
        Map<String, Set<String>> itemMap = customerList.stream().parallel().collect(toItemAsKey);
        assertThat(itemMap.get("plane"), containsInAnyOrder("Chris"));
        assertThat(itemMap.get("onion"), containsInAnyOrder("Patrick", "Amy"));
        assertThat(itemMap.get("ice cream"), containsInAnyOrder("Patrick", "Steven"));
        assertThat(itemMap.get("earphone"), containsInAnyOrder("Steven"));
        assertThat(itemMap.get("plate"), containsInAnyOrder("Joe", "Martin"));
        assertThat(itemMap.get("fork"), containsInAnyOrder("Joe", "Martin"));
        assertThat(itemMap.get("cable"), containsInAnyOrder("Diana", "Steven"));
        assertThat(itemMap.get("desk"), containsInAnyOrder("Alice"));
    }

    @Difficult @Test
    public void bitList2BitString() {
        String bitList = "22-24,9,42-44,11,4,46,14-17,5,2,38-40,33,50,48";

        *//**
         * Create a {@link String} of "n"th bit ON.
         * for example
         * "3" will be "001"
         * "1,3,5" will be "10101"
         * "1-3" will be "111"
         * "7,1-3,5" will be "1110101"
         *//*
        Supplier<StringBuilder> supplier = ()->new StringBuilder();
        BiConsumer<StringBuilder, String> accumulator = (sb,s)->{
            if(s.length()>sb.length()){
                if(s.indexOf("-") != -1){
                    //s.codePointBefore()
                }
            }else{

            }
            sb.append(s);
        };
        Collector<String, ?, String> toBitString = null;

        String bitString = Arrays.stream(bitList.split(",")).collect(toBitString);
        assertThat(bitString, is("01011000101001111000011100000000100001110111010101")

        );
    }*/
}
