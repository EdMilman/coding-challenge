import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvestorImplTest extends TestBase{
    private static LoanImpl loan;
    private static InvestorImpl investor;
    private static CsvUtils csvUtils;

    @BeforeAll
    static void setUp(){
        csvUtils = injector.getInstance(CsvUtils.class);
        loan = csvUtils.getLoanFactory().create(new String[]{"1", "100000", "FIXED", "18", "2015-01-01"});
        investor = csvUtils.getInvestorFactory().create(new String[]{"Alice", "50000", "FIXED", "12"});
    }

    @Test
    void invest() {
        investor.invest(loan);
        assertFalse(loan.isFullyFunded());
        assertEquals(50000, loan.leftToInvest());
        assertEquals(0, investor.getRemaining());
    }
}