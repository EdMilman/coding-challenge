import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class GuiceInjector extends AbstractModule {
    @Override
    protected void configure(){
        install(new FactoryModuleBuilder().implement(ILoan.class, LoanImpl.class).build(LoanImpl.LoanFactory.class));
        install(new FactoryModuleBuilder().implement(IInvestor.class, InvestorImpl.class).build(InvestorImpl.InvestorFactory.class));
    }

}
