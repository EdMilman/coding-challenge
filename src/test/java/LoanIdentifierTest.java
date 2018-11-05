import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanIdentifierTest {
    private static TrackerLoan trackerLoan;
    private static FixedLoan fixedLoan;
    private static UnidentifiedLoan unidentifiedLoan;
    private static LoanType loan;

    @BeforeAll
    static void setUp() {
        trackerLoan = new TrackerLoan();
        fixedLoan = new FixedLoan();
        unidentifiedLoan = new UnidentifiedLoan();
    }

    @Test
    void assignLoanTypeTracker() {
        loan = LoanIdentifier.assignLoanType("TRACKER");
        assert loan.compare(trackerLoan);
        assertFalse(loan.compare(fixedLoan));
        assertFalse(loan.compare(unidentifiedLoan));
    }

    @Test
    void assignLoanTypeFixed(){
        loan = LoanIdentifier.assignLoanType("FIXED");
        assert loan.compare(fixedLoan);
        assertFalse(loan.compare(trackerLoan));
        assertFalse(loan.compare(unidentifiedLoan));
    }

    @Test
    void assignLoanTypeUnidentified(){
        loan = LoanIdentifier.assignLoanType("UNIDENTIFIED");
        assert loan.compare(unidentifiedLoan);
        assertFalse(loan.compare(trackerLoan));
        assertFalse(loan.compare(fixedLoan));
    }

    @Test
    void assignLoanTypeNonsense(){
        loan = LoanIdentifier.assignLoanType("aag4234d");
        assert loan.compare(unidentifiedLoan);
    }
}