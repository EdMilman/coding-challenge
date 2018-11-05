import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

    LoanImpl(String[] details) {
        this.mapInvestorsToAmount = new HashMap<>();
        this.loanId = Integer.parseInt(details[0]);
        this.loanAmount = Integer.parseInt(details[1]);
        this.term = Integer.parseInt(details[3]);
        this.type = LoanIdentifier.assignLoanType(details[2].toUpperCase());
        this.completedDate = LocalDate.parse(details[4]);
    }

    @Override
    public int compareTo(LoanImpl o) {
        return this.completedDate.compareTo(o.completedDate);
    }

    @Override
    public Boolean isFullyFunded() {
        return fundedAmount == loanAmount;
    }

    @Override
    public int leftToInvest() {
        return loanAmount - fundedAmount;
    }

    @Override
    public void fund(int amount, InvestorImpl investor) {
        this.fundedAmount += amount;
        mapInvestorsToAmount.put(investor, amount);
    }
}
