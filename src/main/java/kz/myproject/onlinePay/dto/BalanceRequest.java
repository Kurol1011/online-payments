package kz.myproject.onlinePay.dto;

public class BalanceRequest {
    private long balance;

    public BalanceRequest() {
    }

    public BalanceRequest(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
