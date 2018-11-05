import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * class for implementation of investor
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class InvestorImpl implements IInvestor {
    private String name;
    private int investmentAmount;
    private int remaining;
    private LoanType investmentType;
    private int term;
    @ToString.Exclude
    private Map<Integer, Integer> mapInvestmentAmountToLoanId;

    InvestorImpl(String[] details) {
        this.name = details[0];
        this.investmentAmount = this.remaining = Integer.parseInt(details[1]);
        this.investmentType = LoanIdentifier.assignLoanType(details[2].toUpperCase());
        this.term = Integer.parseInt(details[3]);
        this.mapInvestmentAmountToLoanId = new HashMap<>();
    }

    /**
     * method to have investor invest in a particular loan
     *
     * @param loan loan to be invested in
     */
    @Override
    public void invest(LoanImpl loan) {
        int amount = Math.min(loan.leftToInvest(), remaining);
        loan.fund(amount, this);
        mapInvestmentAmountToLoanId.put(amount, loan.getLoanId());
        remaining -= amount;
    }
}
