package java8;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @author : arfaoui
 * @since : 27/01/2020
 * project : Test
 */
public class CollectorDemo {
    public static void main(String[] args) {
        //implementCollectorWithSimpleClass();
        implementCollectorWithDeclaringNestedClassImplCollector();
    }

    private static void implementCollectorWithDeclaringNestedClassImplCollector() {
        String[] array = new String[]{"a","b","c","d","e"};
        Stream<String> stream = Arrays.stream(array);
        List<String> reduceList = stream.collect(getCollector());
        System.out.println(reduceList);
    }

    private static void implementCollectorWithSimpleClass() {
        String[] array = new String[]{"a","b","c"};
        Stream<String> stream = Arrays.stream(array);
        List<String> reduceList = stream.collect(new MyCollector<>());
        System.out.println(reduceList);
    }
    static class MyCollector<T> implements Collector<T, List<T>, List<T>>{

        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            return List::add;
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            return null;
        }

        @Override
        public Function<List<T>, List<T>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return EnumSet.of(Characteristics.IDENTITY_FINISH);
        }
    }


    private static <T> Collector<T,?,List<T>> getCollector(){
        return new MyCollectorImpl<>(ArrayList::new,List::add, null,Function.identity(),EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
    }
    static class MyCollectorImpl<T,A,R> implements Collector<T,A,R>{
        Supplier<A> supplier;
        BiConsumer<A,T> accumulator;
        BinaryOperator<A> combiner;
        Function<A,R> finisher;
        Set<Characteristics> characteristics;

        public MyCollectorImpl(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner, Function<A, R> finisher, Set<Characteristics> characteristics) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.combiner = combiner;
            this.finisher = finisher;
            this.characteristics = characteristics;
        }

        @Override
        public Supplier<A> supplier() {
            return supplier;
        }

        @Override
        public BiConsumer<A, T> accumulator() {
            return accumulator;
        }

        @Override
        public BinaryOperator<A> combiner() {
            return combiner;
        }

        @Override
        public Function<A, R> finisher() {
            return finisher;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return characteristics;
        }
    }
}
