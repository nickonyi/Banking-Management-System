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
    try (Scanner input = new Scanner(System.in)) {
		System.out.println("Enter customer name");
		String customerName = input.nextLine();
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

public void searchCustomer(String  serialNo){
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank-Management-System-DB","root","");
        Statement stmt = conn.createStatement();
        String sql = "Select * from customers where  customerSerial='"+serialNo+"'";
        ResultSet res = stmt.executeQuery(sql);
       if(res.next()){
        String sql2 = "SELECT customers.customerName,customers.customer_email,customers.customer_phone_No,accountSerialNo,accountName,balance FROM accounts INNER JOIN customers on accounts.accountId = customers.customerId where  customerSerial='"+serialNo+"'";
        ResultSet res2 = stmt.executeQuery(sql2);
        res2.next();
        System.out.println("Name:" + res2.getString(1));
        System.out.println("Email:" + res2.getString(2));
        System.out.println("Phione_Number:" + res2.getString(3));
        System.out.println("Account_serialNo:" + res2.getString(4));
        System.out.println("Account_name:" + res2.getString(5));
        System.out.println("Account_balance:" + res2.getString(6));

       } else {
        System.out.println("This account is not available");
       }
        
    }catch(Exception e){System.out.println(e);}
}


}
