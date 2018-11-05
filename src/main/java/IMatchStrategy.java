import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * Interface for strategy pattern
 */
@ImplementedBy(CodeChallengeStrategy.class)
interface IMatchStrategy {
    void execute(List<InvestorImpl> investors, List<LoanImpl> loans);

    List<InvestorImpl> findPotentialInvestors(LoanImpl loan, List<InvestorImpl> investors);

    int findMaxPotentialFunding(List<InvestorImpl> potentialInvestors);

    void fundLoan(List<InvestorImpl> investors, LoanImpl loan);
}
