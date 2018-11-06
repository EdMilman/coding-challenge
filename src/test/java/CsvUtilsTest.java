import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvUtilsTest extends TestBase{

    private static CsvUtils csvUtils;

    @BeforeAll
    static void setUp() {
        csvUtils = injector.getInstance(CsvUtils.class);
    }

    @Test
    void readLoans() throws IOException {
        String[] loanDetails = new String[]{"1", "100000", "FIXED", "18", "2015-01-01"};
        assertEquals(csvUtils.readLoans("loanTest.csv"), List.of(new LoanImpl(loanDetails)));
    }

    @Test
    void readInvestors() throws IOException {
        String[] investorDetails = new String[]{"Alice", "100", "FIXED", "12"};
        assertEquals(csvUtils.readInvestors("investmentTest.csv"), List.of(new InvestorImpl(investorDetails)));
    }
}