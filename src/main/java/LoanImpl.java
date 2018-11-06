import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of a Loan
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class LoanImpl implements ILoan, Comparable<LoanImpl> {
    private int loanId;
    @ToString.Exclude
    private int fundedAmount;
    private int loanAmount;
    private LoanType type;
    private int term;
    private LocalDate completedDate;
    @ToString.Exclude
    private Map<InvestorImpl, Integer> mapInvestorsToAmount;

    @Inject
    LoanImpl(@Assisted String[] details) {
        this.mapInvestorsToAmount = new HashMap<>();
        this.loanId = Integer.parseInt(details[0]);
        this.loanAmount = Integer.parseInt(details[1]);
        this.term = Integer.parseInt(details[3]);
        this.type = LoanIdentifier.assignLoanType(details[2].toUpperCase());
        this.completedDate = LocalDate.parse(details[4]);
    }

    public interface LoanFactory{
        LoanImpl create(String[] details);
    }

    /**
     * function used for sorting by date
     * @param o - other loan
     * @return int
     */
    @Override
    public int compareTo(LoanImpl o) {
        return this.completedDate.compareTo(o.completedDate);
    }

    /**
     * function showing if loan is fully funded
     * @return boolean if loan is fully funded
     */
    @Override
    public Boolean isFullyFunded() {
        return fundedAmount == loanAmount;
    }

    /**
     * function showing difference between total loan amount and amount invested
     * @return int
     */
    @Override
    public int leftToInvest() {
        return loanAmount - fundedAmount;
    }

    /**
     * adds funds to a load
     * @param amount int - amount to be invested
     * @param investor - investor investing in loan
     */
    @Override
    public void fund(int amount, InvestorImpl investor) {
        this.fundedAmount += amount;
        mapInvestorsToAmount.put(investor, amount);
    }
}
