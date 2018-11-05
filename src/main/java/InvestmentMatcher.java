import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

/**
 * main class for performing the given task
 */

public class InvestmentMatcher {
    private CsvUtils csvUtils;
    @Getter
    private List<InvestorImpl> investors;
    @Getter
    private List<LoanImpl> loans;
    @Setter
    private IMatchStrategy strategy;
    private GsonBuilder gsonBuilder;

    @Inject
    public InvestmentMatcher(CsvUtils csvUtils, IMatchStrategy strategy, GsonBuilder gsonBuilder) {
        this.csvUtils = csvUtils;
        this.strategy = strategy;
        this.gsonBuilder = gsonBuilder;
    }

    /**
     * calls appropriate IMatchStrategy
     */
    public void match() {
        strategy.execute(investors, loans);
    }

    /**
     * calls csvutils to read in details of loans
     *
     * @param file string - file to be read
     * @throws IOException
     */
    public void readLoans(String file) throws IOException {
        this.loans = csvUtils.readLoans(file);
    }

    /**
     * calls csvutils to read in details of investors
     *
     * @param file string - file to be read
     * @throws IOException
     */
    public void readInvestors(String file) throws IOException {
        this.investors = csvUtils.readInvestors(file);
    }

    /**
     * calls gson to return json format of data
     *
     * @return String in json format
     */
    public String printJson() {
        return gsonBuilder.disableHtmlEscaping().setPrettyPrinting().create().toJson(loans);

    }
}
