package at.htl.junit;

/**
 * Einfaches Bankkonto - die "Unit", die wir testen.
 * Regeln:
 * - Einzahlen und Abheben nur mit positiven Beträgen
 * - Das Konto darf nicht überzogen werden
 */
public class BankAccount {
    private final String owner;
    private double balance;

    public BankAccount(String owner, double startBalance) {
        if (startBalance < 0) {
            throw new IllegalArgumentException("Startguthaben darf nicht negativ sein");
        }
        this.owner = owner;
        this.balance = startBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Einzahlung muss positiv sein");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Abbuchung muss positiv sein");
        }
        if (amount > balance) {
            throw new IllegalStateException("Nicht genug Guthaben");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }
}
