import java.util.*;
import java.sql.*;
import java.sql.DriverManager;

public class Bank {
    //properties
   public String name;
   public int code;
   public int address;

    //methods
    public static void main(String[] args){
       // Account der = new Account();
        Customer billy = new Customer();
        //billy.addCustomer();
        //der.addAccount();
       // der.searchAccount("checkoo5");
       billy.searchCustomer(3);
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
}


