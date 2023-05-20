
package bankapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Bank {
    private String name;
    
    public Bank(){
        
    }
    
    public Bank(String name){
        this.name = name;
    }

    public void listAccounts(){
        Connection connection = BankingConnection.connect();
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM account";
            ResultSet results = statement.executeQuery(sql);
            
            while(results.next()){
                System.out.println(results.getString(1)+" "+results.getString(2)+" "
                        +results.getString(3));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
        public void openAccount(Account account){
        Connection connection = BankingConnection.connect();
        String sql = "INSERT INTO account (accNumber,accName,accBalance) VALUES(?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getNumber());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalanced());
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeAccount(int number){
    Connection connection = BankingConnection.connect();
    String sql = "DELETE from account WHERE accNumber=?";
    PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void depositMoney(int accountNumber, double amount){
        Account account = getAccount(accountNumber);
        account.deposit(amount);
        Connection connection = BankingConnection.connect();
        String sql = "UPDATE account SET accBalance=? WHERE accNumber=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalanced());
            preparedStatement.setInt(2, account.getNumber());
            System.out.println("Balance: " + account.getBalanced());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void withdrawMoney(int accountNumber, double amount){
        Account account = getAccount(accountNumber);
        account.withdraw(amount);
        Connection connection = BankingConnection.connect();
        String sql = "UPDATE account SET accBalance=? WHERE accNumber=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalanced());
            preparedStatement.setInt(2, account.getNumber());
            preparedStatement.executeUpdate();
            System.out.println("Balance: " + account.getBalanced());
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Account getAccount(int accountNumber){
        Connection connection = BankingConnection.connect();
        String sql = "SELECT * FROM account WHERE accNumber=?";
        PreparedStatement preparedStatement;
        Account account = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            ResultSet result = preparedStatement.executeQuery();
            
            result.next();
            String accName = result.getString(2);
            double balance = result.getDouble(3);
            account = new Account(accountNumber, accName, balance);
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return account;
    }
    
    
    
}
