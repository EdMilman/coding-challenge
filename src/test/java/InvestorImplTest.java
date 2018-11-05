import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvestorImplTest {
    private static LoanImpl loan;
    private static InvestorImpl investor;

    @BeforeAll
    static void setUp(){
        loan = new LoanImpl(new String[]{"1", "100000", "FIXED", "18", "2015-01-01"});
        investor = new InvestorImpl(new String[]{"Alice", "50000", "FIXED", "12"});
    }

    @Test
    void invest() {
        investor.invest(loan);
        assertFalse(loan.isFullyFunded());
        assertEquals(50000, loan.leftToInvest());
        assertEquals(0, investor.getRemaining());
    }
}