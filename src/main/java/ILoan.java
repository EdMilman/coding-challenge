/**
 * interface for loan
 */
public interface ILoan {
    void fund(int amount, InvestorImpl investor);

    int leftToInvest();

    Boolean isFullyFunded();
}
