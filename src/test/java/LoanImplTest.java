import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoanImplTest {
    private static LoanImpl loan;
    private static InvestorImpl investor;

    @BeforeEach
    void setUp() {
        loan = new LoanImpl(new String[]{"1", "100000", "FIXED", "18", "2015-01-01"});
    }

    @Test
    void isFullyFunded() {
        assertFalse(loan.isFullyFunded());
        loan.setFundedAmount(loan.getLoanAmount());
        assert loan.isFullyFunded();
    }

    @Test
    void leftToInvest() {
        assertEquals(loan.getLoanAmount(), loan.leftToInvest());
        loan.setFundedAmount(loan.getLoanAmount() / 2);
        assertEquals(loan.getLoanAmount() / 2, loan.leftToInvest());
    }

    @Test
    void fund() {
        loan = new LoanImpl(new String[]{"1", "100000", "FIXED", "18", "2015-01-01"});
        investor = new InvestorImpl(new String[]{"Alice", "100000", "FIXED", "12"});
        loan.fund(100000, investor);
        assertTrue(loan.isFullyFunded());
    }

    @Test
    void fund1() {
        loan = new LoanImpl(new String[]{"1", "100000", "FIXED", "18", "2015-01-01"});
        investor = new InvestorImpl(new String[]{"Alice", "50000", "FIXED", "12"});
        loan.fund(50000, investor);
        assertFalse(loan.isFullyFunded());
        assertEquals(50000, loan.leftToInvest());
    }
}