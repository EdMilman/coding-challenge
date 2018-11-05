/**
 * interface for loan
 */
interface ILoan {
    public void fund(int amount, InvestorImpl investor);

    public int leftToInvest();

    public Boolean isFullyFunded();
}
