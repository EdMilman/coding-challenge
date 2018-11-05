import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CodeChallengeStrategy implements IMatchStrategy {
    @Override
    public void execute(List<InvestorImpl> investors, List<LoanImpl> loans) {
        for (LoanImpl loan : loans) {
            List<InvestorImpl> potentialInvestors = findPotentialInvestors(loan, investors);
            fundLoan(potentialInvestors, loan);
        }
    }

    @Override
    public List<InvestorImpl> findPotentialInvestors(LoanImpl loan, List<InvestorImpl> investors) {
        return investors.stream()
                .filter(investor -> investor.getRemaining() > 0) // remove investors with no money left to fund
                .filter(investor -> investor.getTerm() >= loan.getTerm()) // remove short term investors
                .filter(investor -> investor.getInvestmentType().compare(loan.getType())) // remove incorrect loanImpl type
                .collect(Collectors.toList());
    }

    @Override
    public int findMaxPotentialFunding(List<InvestorImpl> potentialInvestors) {
        return potentialInvestors.stream().mapToInt(InvestorImpl::getRemaining).sum();
    }

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
