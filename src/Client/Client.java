package Client;

public class Client {
    private String email;
    private String name;
    private int age;

    public Client(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
