import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeChallengeStrategyTest {

    private static IMatchStrategy strategy;
    private static ArrayList<InvestorImpl> investors;
    private static CsvUtils csvUtils;
    private static LoanImpl loan;

    @BeforeAll
    static void setUp() throws IOException {
        strategy = new CodeChallengeStrategy();
        csvUtils = new CsvUtils();
        investors = csvUtils.readInvestors("investmentRequests.csv");
    }

    @Test
    void findPotentialInvestors() {
        loan = new LoanImpl(new String[]{"1", "10", "FIXED", "36", "2015-01-01"}); // loan term too long for any fixed loan investor
        assertEquals(strategy.findPotentialInvestors(loan, investors), Collections.emptyList());
    }

    @Test
    void findPotentialInvestors1() {
        loan = new LoanImpl(new String[]{"1", "10", "TRACKER", "45", "2015-01-01"}); // matches only Dan,20000,TRACKER,50 - checks term
        assertEquals(strategy.findPotentialInvestors(loan, investors), List.of(new InvestorImpl(new String[]{"Dan", "20000", "TRACKER", "50"})));
    }

    @Test
    void findPotentialInvestors2() {
        loan = new LoanImpl(new String[]{"1", "10", "OTHER", "36", "2015-01-01"}); // loan term does not exist
        assertEquals(strategy.findPotentialInvestors(loan, investors), Collections.emptyList());
    }

    @Test
    void findPotentialInvestors3() {
        loan = new LoanImpl(new String[]{"1", "10", "TRACKER", "12", "2015-01-01"}); // matches all tracker loan applicants
        assertEquals(strategy.findPotentialInvestors(loan, investors), List.of(
                new InvestorImpl(new String[]{"Bob", "330000", "TRACKER", "30"}),
                new InvestorImpl(new String[]{"Dan", "20000", "TRACKER", "50"}),
                new InvestorImpl(new String[]{"Eve", "13000", "TRACKER", "30"}),
                new InvestorImpl(new String[]{"Leo", "67800", "TRACKER", "30"}),
                new InvestorImpl(new String[]{"Mark", "180", "TRACKER", "30"}),
                new InvestorImpl(new String[]{"Nina", "10000", "TRACKER", "30"})
        ));
    }


    @Test
    void findMaxPotentialFunding() {
        assertEquals(strategy.findMaxPotentialFunding(investors), 851190); // max funding for all loans
    }

    @Test
    void findMaxPotentialFunding2() {
        loan = new LoanImpl(new String[]{"1", "10", "TRACKER", "12", "2015-01-01"}); // max funding for tracker loans
        List<InvestorImpl> filteredInvestors = strategy.findPotentialInvestors(loan, investors);
        assertEquals(strategy.findMaxPotentialFunding(filteredInvestors), 440980);
    }


}