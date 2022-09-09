public class Atm {
    //properties
    String atmLocation;
    String atmManagedBy;
    int balance;

    //methods
    void deposit(int amount){
        if(amount !=0){
           balance = balance + amount;
        }
    }
}
