public class SavingsAccount extends BankAccount{
    private double interestRate;
    private double balance;
public SavingsAccount(double initialAmount, double rate) {
    super (initialAmount);
    interestRate = rate;
    balance = initialAmount;
}
public double getInterestRate() {
    return interestRate;
}
public double calculateInterest() {
    double interest_cost;
    interest_cost = balance * interestRate;
    return (interest_cost + balance);
}
public String toString() {
    return("SavingsAccount: balance " + balance + ", interest rate " + interestRate);
}
public static void main(String[] args) {
        SavingsAccount myAccount = new SavingsAccount(100.0,0.15);
        myAccount.calculateInterest();
        System.out.println(myAccount.toString());
    }}