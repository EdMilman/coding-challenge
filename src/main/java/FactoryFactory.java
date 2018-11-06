import com.google.inject.Inject;
import lombok.Getter;

public class FactoryFactory {
    @Getter
    private final LoanImpl.LoanFactory loanFactory;
    @Getter
    private final InvestorImpl.InvestorFactory investorFactory;

    @Inject
    private FactoryFactory(LoanImpl.LoanFactory loanFactory, InvestorImpl.InvestorFactory investorFactory) {
        this.loanFactory = loanFactory;
        this.investorFactory = investorFactory;
    }
}
