import java.time.LocalDate;

public class Transaction {
    LocalDate date;
    String description;
    int amount;

    public Transaction(LocalDate date , String description , int amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;


    }
}
