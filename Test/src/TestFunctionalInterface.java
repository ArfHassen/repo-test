import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestFunctionalInterface {
    static public void main(String... ars) {
        testSupplier();
    }

    private static void printNames(String name) {
        System.out.println(name);
    }

    private static void printNames(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }

    static void testConsumer() {
        System.out.println("E.g. #1 - Java8 Consumer Example\n");

        Consumer<String> consumer = TestFunctionalInterface::printNames;
        consumer.accept("C++");
        consumer.accept("Java");
        consumer.accept("Python");
        consumer.accept("Ruby On Rails");
    }

    static void testSupplier(){
        System.out.println("E.g. #2 - Java8 Supplier Example\n");

        List<String> names = new ArrayList<>();
        names.add("Harry.");
        names.add("Daniel.");
        names.add("Lucifer");
        names.add("April O' Neil");
        names.stream().forEach(item -> {
            printNames(()->item);
        });
    }
}
