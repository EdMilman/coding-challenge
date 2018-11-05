import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * implementation of IMatchStrategy
 */
public class CodeChallengeStrategy implements IMatchStrategy {
    /**
     * performs matching strategy
     * @param investors - list of investors
     * @param loans - list of loans
     */
    @Override
    public void execute(List<InvestorImpl> investors, List<LoanImpl> loans) {
        for (LoanImpl loan : loans) {
            List<InvestorImpl> potentialInvestors = findPotentialInvestors(loan, investors);
            fundLoan(potentialInvestors, loan);
        }
    }

    /**
     * filters investors based on requirements
     * @param loan - loan requirements are taken from
     * @param investors - list of investors to be filtered
     * @return filtered list of investors
     */
    @Override
    public List<InvestorImpl> findPotentialInvestors(LoanImpl loan, List<InvestorImpl> investors) {
        return investors.stream()
                .filter(investor -> investor.getRemaining() > 0) // remove investors with no money left to fund
                .filter(investor -> investor.getTerm() >= loan.getTerm()) // remove short term investors
                .filter(investor -> investor.getInvestmentType().compare(loan.getType())) // remove incorrect loanImpl type
                .collect(Collectors.toList());
    }

    /**
     * finds maximum potential funding amount for a group of investors
     * @param potentialInvestors - list of investors
     * @return int amount that can be funded
     */
    @Override
    public int findMaxPotentialFunding(List<InvestorImpl> potentialInvestors) {
        return potentialInvestors.stream().mapToInt(InvestorImpl::getRemaining).sum();
    }

    /**
     * funds a loan
     * @param investors - list of investors
     * @param loan - loan to be invested in
     */
    @Override
    public void fundLoan(List<InvestorImpl> investors, LoanImpl loan) {
        if (findMaxPotentialFunding(investors) >= loan.getLoanAmount()) {
            Iterator<InvestorImpl> it = investors.iterator();
            while (!loan.isFullyFunded() && it.hasNext()) {
                it.next().invest(loan);
            }
        }
    }
}
