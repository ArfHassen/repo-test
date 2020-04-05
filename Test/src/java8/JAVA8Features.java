package java8;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author : arfaoui
 * @since : 10/12/2019
 * project : espace-adherent-v2
 */
public class JAVA8Features {
    static private List<Transaction> transactions;

    public JAVA8Features() {
    }

    static public void main(String...args){
        //convertListToArrayImplementation();
        initData();
        //groupTransactionsByCurrencyWithJava7();
        testSomeCollectors();

    }

    private static void convertListToArrayImplementation() {
        IntFunction<String[]> intFunction = new IntFunction<String[]>() {
            @Override
            public String[] apply(int value) {
                return new String[value];
            }
        };//we can do intFunction = String[]::new (in compile time java will transform it to lambda s->new String[s]
        String[] stringList = {"Arfaoui","Hassen","Arf"};
        Stream<String> stringStream = Arrays.stream(stringList);
        Stream<String> myNewStream = stringStream.map(s->s.toUpperCase());
        String[] myNewArray = myNewStream.toArray(intFunction);
        System.out.println(myNewArray);
    }

    private static void testSomeCollectors() {
        //Function<Transaction, Integer> func = Transaction::getId;//implement function with reference method instead of lambda expression
        //group taransaction by currency
        System.out.println(transactions.stream().collect(groupingBy(Transaction::getCurrency)));
        Comparator<Transaction> comparator = Comparator.comparing(Transaction::getAmount);
        //get max transaction
        Optional<Transaction> maxTransaction = transactions.stream().collect(maxBy(comparator));
        System.out.println("max transaction amount : "+maxTransaction.get().getAmount());
        //get min transaction
        Optional<Transaction> minTransaction = transactions.stream().collect(minBy(comparator));
        System.out.println("min transaction amount : "+minTransaction.get().getAmount());
        createTransactionObjectWithStream();
        //sum transaction
        int sum = transactions.stream().collect(summingInt(Transaction::getAmount));
        System.out.println("sum of transactions is : "+sum);
        // AVG transactions
        double avg = transactions.stream().collect(averagingInt(Transaction::getAmount));
        System.out.println("average transcations is : "+avg);
        // statistics regrouped in one object
        IntSummaryStatistics statics = transactions.stream().collect(summarizingInt(Transaction::getAmount));
        System.out.println("staticts : "+statics);
        // get type transaction as a string delimited by a comma
        String str = transactions.stream().map(t->t.getCurrency().name()).collect(joining(","));
        System.out.println("transaction string delimiter by cuurencies "+str);
        // get sum transactions with reducing method
        int sumWithReducing = transactions.stream().collect(reducing(0,Transaction::getAmount, (i,j)->i+j));//le 0 is the starting point if there is no element in the stream
        System.out.println("sum amount transactions using reducing collecter "+sumWithReducing);
        // get max transaction amount with reducing method, it return an optional because there is only one argument in reduce method so tf the stream is empty there is no result bu default
        Optional<Transaction> maxWithReducing = transactions.stream().collect(reducing((t1,t2)->t1.getAmount()>t2.getAmount()?t1:t2));
        System.out.println("max transaction using reducing collector "+maxWithReducing.get());
    }

    private static void createTransactionObjectWithStream() {
        //IntFunction<Transaction[]> generator = Transaction[]::new;
        List<Currency> currencies = Arrays.asList(Currency.EUR, Currency.DINAR, Currency.USD);
        Transaction[] newTransactionObjectFromCurrencies = currencies.stream().map(Transaction::new).toArray(Transaction[]::new);
        Arrays.stream(newTransactionObjectFromCurrencies).forEach(System.out::println);
    }

    private static void groupTransactionsByCurrencyWithJava7() {
        Map<Currency, List<Transaction>> transactionsByCurrency = new HashMap<>();
        for(Transaction transaction : transactions){
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrency.get(currency);
            if(Objects.isNull(transactionsForCurrency)){
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrency.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }
        System.out.println(transactionsByCurrency);
    }

    static private void initData(){
        Transaction t1 = new Transaction(1,Currency.DINAR,new Integer(20));
        Transaction t2 = new Transaction(2,Currency.DINAR,new Integer(100));
        Transaction t3 = new Transaction(3,Currency.DINAR,new Integer(75));
        Transaction t4 = new Transaction(4,Currency.EUR,new Integer(35));
        Transaction t5 = new Transaction(5,Currency.EUR,new Integer(40));
        Transaction t6 = new Transaction(6,Currency.USD,new Integer(62));
        transactions = Arrays.asList(t1,t2,t3,t4,t5,t6);
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof JAVA8Features)) return false;
        final JAVA8Features other = (JAVA8Features) o;
        if (!other.canEqual((Object) this)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof JAVA8Features;
    }

    public int hashCode() {
        int result = 1;
        return result;
    }

    public String toString() {
        return "Test8()";
    }

    static class Transaction{
        private int id;
        private Currency currency;
        private Integer amount;

        public Transaction(int id, Currency currency, Integer amount) {
            this.id = id;
            this.currency = currency;
            this.amount = amount;
        }

        public Transaction(Currency currency) {
            this.currency = currency;
        }

        public Transaction() {
        }

        public int getId() {
            return this.id;
        }

        public Currency getCurrency() {
            return this.currency;
        }

        public Integer getAmount() {
            return this.amount;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Transaction)) return false;
            final Transaction other = (Transaction) o;
            if (!other.canEqual((Object) this)) return false;
            if (this.getId() != other.getId()) return false;
            final Object this$currency = this.getCurrency();
            final Object other$currency = other.getCurrency();
            if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) return false;
            final Object this$amount = this.getAmount();
            final Object other$amount = other.getAmount();
            if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Transaction;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            result = result * PRIME + this.getId();
            final Object $currency = this.getCurrency();
            result = result * PRIME + ($currency == null ? 43 : $currency.hashCode());
            final Object $amount = this.getAmount();
            result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
            return result;
        }

        public String toString() {
            return "Test8.Transaction(id=" + this.getId() + ", currency=" + this.getCurrency() + ", amount=" + this.getAmount() + ")";
        }
    }
    private enum Currency{
        EUR,
        USD,
        DINAR
    }
}
