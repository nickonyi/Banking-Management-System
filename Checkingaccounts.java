

public class Checkingaccounts extends Account{

    Checkingaccounts(String cName, String cId) {
        super(cName, cId);
        
    }
 
    //@overide the calculate interest method
    //Function calculating interest of current funds after a number of years
	public void calculateInterest(int years) {
		double interestRate = .0175;
		double newBalance = (balance * interestRate * years) + balance;
		System.out.println("The current interest rate is " + (100 * interestRate) + "%");
		System.out.println("After " + years + " years, you balance will be: " + newBalance);
	}
    
}
