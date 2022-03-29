package Client;

public class PremiumClient extends Client{

    private float discount;

    public PremiumClient(String email, String name, int age, float discount) {
        super(email, name, age);
        this.discount = discount;
    }
}
