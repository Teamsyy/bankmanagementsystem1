package bankapplication1;


public class Account {
    private int number;
    private String name;
    private double balanced;

    public Account(int number, String name, double balanced) {
        this.number = number;
        this.name = name;
        this.balanced = balanced;
    }
    
    public void deposit(double amount){
        this.balanced = this.balanced + amount;
    }
    
    public void withdraw(double amount){
        this.balanced = this.balanced - amount;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public double getBalanced() {
        return balanced;
    }
    
    
}
