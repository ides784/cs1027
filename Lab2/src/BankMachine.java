import java.util.Scanner;

public class BankMachine {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("Enter your desired account type: 'c' for checking or 's' for savings.");
		String accountType = input.next();

		if (accountType.equals("c")) {
			CheckingAccount newAcc;
			newAcc = new CheckingAccount(100);
			String accountInfo = newAcc.toString();
			System.out.println("Your new account has been successfully created.");
			System.out.println(accountInfo);
			System.out.println("Transactions: " + newAcc.getTransactionCount());
		} else if (accountType.equals("s")) {
			SavingsAccount newAcc;
			newAcc = new SavingsAccount(100, 0.14);
			String accountInfo = newAcc.toString();
			System.out.println("Your new account has been successfully created.");
			System.out.println(accountInfo);
			System.out.println("InterestRate: " + newAcc.getInterestRate());
		} else {
			System.out.println("Invalid account type.");
			return; // If bad input, then end program.
		}
	}
}