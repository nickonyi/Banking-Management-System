import java.util.*;
import java.sql.*;
import java.sql.DriverManager;


public class Account {
    //properties
    int accountNumber;
    String accountName;
    int balance;
    String accountSerial;

    //methods
    public void addAccount(){
      Scanner input = new Scanner(System.in);
      System.out.println("Enter the account name");
      accountName = input.nextLine();
      System.out.println("Enter account serial");
      accountSerial = input.nextLine();
      System.out.println("Enter account number");
      accountNumber = input.nextInt();
      System.out.println("Enter the account balance");
      balance = input.nextInt();
      

       try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank-Management-System-DB","root","");
    Statement stmt = conn.createStatement();
    System.out.println("Connected to the database");
    String sql = "insert into accounts() values('"+accountNumber+"','"+accountName+"','"+balance+"','"+accountSerial+"')";
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
}




