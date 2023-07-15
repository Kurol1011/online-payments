package kz.myproject.onlinePay.dto;

public class TransactionData {
    private long fromBankAccount;
    private long toBankAccount;
    private double amount;

    public TransactionData() {
    }

    public TransactionData(long fromBankAccount, long toBankAccount, double amount) {
        this.fromBankAccount = fromBankAccount;
        this.toBankAccount = toBankAccount;
        this.amount = amount;
    }

    public long getFromBankAccount() {
        return fromBankAccount;
    }

    public void setFromBankAccount(long fromBankAccount) {
        this.fromBankAccount = fromBankAccount;
    }

    public long getToBankAccount() {
        return toBankAccount;
    }

    public void setToBankAccount(long toBankAccount) {
        this.toBankAccount = toBankAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
