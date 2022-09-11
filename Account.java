import java.util.*;
import java.sql.*;
import java.sql.DriverManager;


public class Account {
    //properties
    int accountId;
    int balance;
    int previousTransaction;
    int customerId;
    String accountSerial;
    String accountName;
    String customerName;
   

    Account(String cName,String cId){
        customerName = cName;
        accountSerial = cId;
        
    }

    //methods
    public void addAccount(){
      try (Scanner input = new Scanner(System.in)) {
        System.out.println("Enter the account name");
          accountName = input.nextLine();
          System.out.println("Enter account id");
          accountId = input.nextInt();
          System.out.println("Enter the account balance");
          balance = input.nextInt();
    }
      try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank-Management-System-DB","root","");
    Statement stmt = conn.createStatement();
    System.out.println("Connected to the database");
    String sql = "insert into accounts() values('"+accountId+"','"+accountName+"','"+balance+"','"+accountSerial+"')";
    stmt.executeUpdate(sql);
    System.out.println("Account added successfully");
       } catch (Exception  e){System.out.println(e);}
    }

   public void deleteAccount(String serialNo){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank-Management-System-DB","root","");
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM accounts where accountSerialNo ='"+serialNo+"'";
            stmt.executeUpdate(sql);
            System.out.println("data deleted successfully");
        } catch (Exception e){System.out.println(e);}
    }

    public void searchAccount(String serialNo){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank-Management-System-DB","root","");
            Statement stmt = conn.createStatement();
            String sql = "Select * from accounts where  accountSerialNo='"+serialNo+"'";
            ResultSet res = stmt.executeQuery(sql);
            res.next();
            System.out.println(res.getString(1) +" "+res.getString(2)+" "+res.getString(3));
        }catch(Exception e){System.out.println(e);}
    }

    public void deposit (int amount){
       balance = getBalance("check002");
        if(amount !=0){
            balance = balance + amount;
            storetoDb(balance,accountSerial);
        }
        }

        public void withdraw (int amount){
            if(amount !=0){
                balance = balance - amount;
                storetoDb(balance,accountSerial);

            }
        }

        void getPreviousTransactions(){
            if(previousTransaction > 0){
                System.out.println("You deposited "+ previousTransaction);
            } else if(previousTransaction < 0){
                System.out.println("You withdrew "+ previousTransaction);
            }
        }
    
        void showDisplay(){
            char option = '\0';
            try (Scanner input = new Scanner(System.in)) {
                System.out.println("Welcome to stanbick bank, " + customerName + "!");
                System.out.println("Your ID is: " + accountSerial);
                System.out.println();
                System.out.println("What would you like to do?");
                System.out.println();
                System.out.println("A. Check your balance");
                System.out.println("B. Make a deposit");
                System.out.println("C. Make a withdrawal");
                System.out.println("D. View previous transaction");
                System.out.println("E. Calculate interest");
                System.out.println("F. Exit");
   
                do {
                    System.out.println();
                    System.out.println("Enter an option");
                    char option1 = input.next().charAt(0);
                    option = Character.toUpperCase(option1);
                    System.out.println();
   
                    switch(option){
                       //allows the user to check the account balance
                        case 'A':
                        System.out.println("====================================");
                        System.out.println("Balance = Ksh" + balance);
                        System.out.println("====================================");
                        break;
                        //allows the user to deposit the money in the bank account
                        case 'B':
                        System.out.println("Enter the amount your want to deposit");
                        int amount = input.nextInt();
                        deposit(amount);
                        System.out.println("You have successfully deposited!");
                        break;
                        //allows the user to withdraw the money in the bank
                        case 'C':
                        System.out.println("Enter the amount your want to withdraw");
                        int amount2 = input.nextInt();
                        withdraw(amount2);
                        System.out.println("You have successfully withdrawn!");
                        break;
                        //allows you to view previous transactions
                        case 'D':
                        System.out.println("====================================");
                        getPreviousTransactions();
                        System.out.println("====================================");
                        System.out.println();
                        break;
                        case 'E':
                        System.out.println("Enter how many years of accrued interest: ");
                	    int years = input.nextInt();
                	    calculateInterest(years);
                	    break;
                        //The default case let's the user know that they entered an invalid character and how to enter a valid one
                    default:
                    System.out.println("Error: invalid option. Please enter A, B, C, D, or E or access services.");
                    break;
   
   
   
                    }
                }while(option !='F');
            }
            System.out.println("Thank you for choosing stanbick bank");
        }
       public void storetoDb (int amount,String accSer){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank-Management-System-DB","root","");
                Statement stmt = conn.createStatement();
                String sql = "UPDATE accounts SET balance ='"+balance+"' WHERE accountSerialNo ='"+accSer+"'";
                stmt.executeUpdate(sql);
                System.out.println("Data successfully submitted");
                }catch (Exception e){System.out.println(e);}
        }
    public int getBalance(String accountSerialNo){
        int balance = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank-Management-System-DB","root","");
            Statement stmt = conn.createStatement();
            String sql = "Select balance from accounts where accountSerialNo='"+accountSerialNo+"' ";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            balance = rs.getInt(1);

        }catch(Exception e){System.out.println(e);}
        return balance;
    }
   //Function calculating interest of current funds after a number of years
	public void calculateInterest(int years) {
		double interestRate = .0185;
		double newBalance = (balance * interestRate * years) + balance;
		System.out.println("The current interest rate is " + (100 * interestRate) + "%");
		System.out.println("After " + years + " years, you balance will be: " + newBalance);
	}
}




