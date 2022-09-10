import java.util.*;
import java.sql.*;
import java.sql.DriverManager;

public class Customer {
    //properties
int customerId;
String customerName;
String customerEmail;
String customerPhoneNo;
String customerUsername;
String customerAddress;
String customerSerial;

//methods
public void addCustomer(){
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the customer name");
    customerName = input.nextLine();
    System.out.println("Enter the customer email");
    customerEmail = input.nextLine();
    System.out.println("Enter the customer phone Number");
    customerPhoneNo = input.nextLine();
    System.out.println("Enter the customer username");
    customerUsername = input.nextLine();
    System.out.println("Enter the customer address");
    customerAddress = input.nextLine();
    System.out.println("Enter customer serial No");
    customerSerial = input.nextLine();
    System.out.println("Enter the customer id");
    customerId = input.nextInt();

     try {
  Class.forName("com.mysql.cj.jdbc.Driver");
  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank-Management-System-DB","root","");
  Statement stmt = conn.createStatement();
  System.out.println("Connected to the database");
  String sql = "insert into customers() values('"+customerId+"','"+customerName+"','"+customerEmail+"','"+customerPhoneNo+"','"+customerUsername+"','"+customerAddress+"','"+customerSerial+"')";
  stmt.executeUpdate(sql);
  System.out.println("Customer added successfully");
     } catch (Exception  e){System.out.println(e);}
}

public void deleteCustomer(int serialNo){
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank-Management-System-DB","root","");
        Statement stmt = conn.createStatement();
        String sql = "DELETE FROM customers where customerId ='"+serialNo+"'";
        stmt.executeUpdate(sql);
        System.out.println("data deleted successfully");
    } catch (Exception e){System.out.println(e);}
}

public void searchCustomer(int  serialNo){
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank-Management-System-DB","root","");
        Statement stmt = conn.createStatement();
        String sql = "Select * from customers where  customerId='"+serialNo+"'";
        ResultSet res = stmt.executeQuery(sql);
        res.next();
        System.out.println(res.getString(1) +" "+res.getString(2)+" "+res.getString(3));
    }catch(Exception e){System.out.println(e);}
}


}
