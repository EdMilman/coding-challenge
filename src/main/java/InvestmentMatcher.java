import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvestmentMatcher {
    private CsvUtils csvUtils;
    @Getter
    private List<InvestorImpl> investors;
    @Getter
    private List<LoanImpl> loans;
    @Setter
    private IMatchStrategy strategy;
    private Gson gson;


    public InvestmentMatcher() {
        this.investors = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.csvUtils = new CsvUtils();
        this.gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    }

    public InvestmentMatcher(IMatchStrategy strategy){
        this();
        this.strategy = strategy;
    }

    public void match(){
         strategy.execute(investors, loans);
    }

    public void readLoans(String file) throws IOException {
        this.loans = csvUtils.readLoans(file);
    }

    public void readInvestors(String file) throws IOException {
        this.investors = csvUtils.readInvestors(file);
    }

    public String printJson(){
        return gson.toJson(loans);
    }
}
