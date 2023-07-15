package kz.myproject.onlinePay.dto;

public class BalanceRequest {
    private long balance;

    public BalanceRequest(long balance) {
        this.balance = balance; //todo Is will be worked without construct?
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
