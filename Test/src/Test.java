public class Test {
    public static void main (String...args){
        NameParser<String> parser = new NameParser();

        String res = parser.parse("Eric Clapton", Factory::test);
        /*Name res = parser.parse("Eric Clapton", new Creator<Name>() {
            @Override
            public Name create(String firstName, String lastName) {
                return new Name(firstName, lastName);
            }
        });*/
        System.out.println(res);
    }
}

class Factory{
    static public String test(String n, String n2){
        return "test";
    }
}

class NameParser<T> {
    public  T parse(String name, Creator creator) {
        String[] tokens = name.split(" ");
        String firstName = tokens[0];
        String lastName = tokens[1];
        return (T)creator.create(firstName, lastName);
    }
}

@FunctionalInterface
interface Creator<T> {
    T create(String firstName, String lastName);
}

class Name {

    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Name() {
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
