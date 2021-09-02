package changeobjecttype;

interface WithdrawPermit {
//  boolean permit(Account a, long amount);
  boolean permit(long resultingBalance, int transactionsIn30Days);
}

class MortgageWithdrawPermit implements WithdrawPermit {
  @Override
  public boolean permit(long resultingBalance, int trans) {
    return false;
  }
}

class MinBalWithdrawPermit implements WithdrawPermit {
  private long minBalance;

  public MinBalWithdrawPermit(long minBalance) {
    this.minBalance = minBalance;
  }

  @Override
  public boolean permit(long resultingBalance, int trans) {
    return minBalance <= resultingBalance;
  }
}

class Account {
  private long balance;
  // object containing behavior to which we delegate (and which we can change
  // at runtime!!!) is called the Strategy.
  // The "account type" is no longer a compile time "thing"
  // It's a mortgage because it BEHAVES like a mortgage
  private WithdrawPermit withdrawPermit;

  public Account(long balance, WithdrawPermit withdrawPermit) {
    this.balance = balance;
    this.withdrawPermit = withdrawPermit;
  }

  public void deposit(long amount) {
    // log the transaction..
    this.balance += amount;
    if (balance > 0) {
      System.out.println("Changing withdraw permit behavior");
      withdrawPermit = new MinBalWithdrawPermit(0);
    }
  }

  // withdraw a positive amount!
  public boolean withdraw(long amount) {
    long expectedBalance = balance - amount;
    if (withdrawPermit.permit(expectedBalance, 3)) {
      System.out.println("withdraw successful");
      this.balance = expectedBalance;
      return true;
    } else {
      System.out.println("withdraw denied");
    }
    return false;
  }

}

public class Bank {
  public static void main(String[] args) {
    Account a = new Account(-1000, new MortgageWithdrawPermit());
    boolean res = a.withdraw(1000);
    System.out.println("withdrawal approved? " + res);
    a.deposit(2400);

    System.out.println("withdrawal approved? " + a.withdraw(1000));
    System.out.println("withdrawal approved? " + a.withdraw(1000));
  }
}
