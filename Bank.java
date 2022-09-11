import java.util.*;
import java.sql.*;
import java.sql.DriverManager;

public class Bank {
    //properties
   public String name;
   public String code;
   public String password;
   public int address;

    //methods
    public static void main(String[] args){
      Bank stanbic = new Bank();
       stanbic.registerAccount();
      Account account1 = new Account("Mercy","savings001");
      account1.showDisplay();
      
     
      
    }

    public void getAccount(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank-Management-System-DB","root","");
            Statement stmt = conn.createStatement();
            String sql = "SELECT customers.customerName,customers.customer_email,customers.customer_phone_No,accountId,accountName,balance FROM accounts INNER JOIN customers on accounts.accountId = customers.customerId";
            ResultSet res =stmt.executeQuery(sql);
              while(res.next())
              System.out.println(res.getString(2));
        }catch (Exception e){System.out.println(e);}
    }
public void registerAccount(){
    try (Scanner input = new Scanner(System.in)) {
        System.out.println("Enter your teller name");
        String tName = input.nextLine();
        System.out.println("Enter your teller id");
        String tId = input.nextLine();
        System.out.println("Enter password");
        String password = input.nextLine();
        String cPassword = "12345";

        if(password.equals(cPassword)){
          char option = '\0';
          try (Scanner inn = new Scanner(System.in)) {
            System.out.println("Welcome back teller, " + tName + "!");
              System.out.println("Your ID is: " + tId);
              System.out.println();
              System.out.println("What would you like to do?");
              System.out.println();
              System.out.println("A. Open customer Bank account");
              System.out.println("B. Delete customer Bank account");
              System.out.println("C. Search customer Bank account");
              System.out.println("D. Exit");


              do {
                  System.out.println();
                  System.out.println("Enter an option");
                  char option1 = input.next().charAt(0);
                  option = Character.toUpperCase(option1);
                  System.out.println();

                  switch(option){
                     //allows the user to check the account balance
                      case 'A':
                      System.out.println("Enter Account serial No");
                      String accSerial = inn.nextLine();
                      String name="";
                      Customer customer1 = new Customer();
                      Account account1 = new Account (name,accSerial);
                      customer1.addCustomer();
                      account1.addAccount();
                      break;
                      //allows the user to deposit the money in the bank account
                      case 'B':
                      System.out.println("Enter the id of the customer you want to delete");
                      int customerId = inn.nextInt();
                      Customer customer2 = new Customer();
                      customer2.deleteCustomer(customerId);
                      break;
                      //allows the user to withdraw the money in the bank
                      case 'C':
                      System.out.println("Enter the serialNo of the customer you want to search");
                      String customerid = inn.nextLine();
                      Customer customer3 = new Customer();
                      customer3.searchCustomer(customerid);
                     
                      break;
                     
                     
                      //The default case let's the user know that they entered an invalid character and how to enter a valid one
                  default:
                  System.out.println("Error: invalid option. Please enter A, B, C or D to access services.");
                  break;



                  }
              }while(option !='D');
        }
          System.out.println("You have exited the Admin priviledges");
        } else {
            System.out.println("Wrong pasword");
        }
    }
     
}
    
}


