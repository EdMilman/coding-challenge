import java.util.List;

interface IMatchStrategy {
    void execute(List<InvestorImpl> investors, List<LoanImpl> loans);

    List<InvestorImpl> findPotentialInvestors(LoanImpl loan, List<InvestorImpl> investors);

    int findMaxPotentialFunding(List<InvestorImpl> potentialInvestors);

    void fundLoan(List<InvestorImpl> investors, LoanImpl loan);
}
