import com.google.inject.Inject;
import com.opencsv.CSVReader;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * class to hold methods relating to csv operations
 */

public class CsvUtils {
    /**
     * reads in details of loans
     * @param file - file to be read
     * @return - ArrayList of loans
     * @throws IOException
     */

    @Getter
    private LoanImpl.LoanFactory loanFactory;
    @Getter
    private InvestorImpl.InvestorFactory investorFactory;


    @Inject
    private CsvUtils(FactoryFactory factory) {
        this.loanFactory = factory.getLoanFactory();
        this.investorFactory = factory.getInvestorFactory();
    }

    public ArrayList<LoanImpl> readLoans(String file) throws IOException {
        FileReader filereader = new FileReader(file);
        CSVReader csvReader = new CSVReader(filereader);
        ArrayList<LoanImpl> loanImpls = new ArrayList<>();
        csvReader.skip(1);
        csvReader.readAll().forEach(loan -> loanImpls.add(loanFactory.create(loan)));
        Collections.sort(loanImpls);
        return loanImpls;
    }

    /**
     * reads in details of investors
     * @param file - file to be read
     * @return - ArrayList of investors
     * @throws IOException
     */
    public ArrayList<InvestorImpl> readInvestors(String file) throws IOException {
        FileReader filereader = new FileReader(file);
        CSVReader csvReader = new CSVReader(filereader);
        ArrayList<InvestorImpl> investors = new ArrayList<>();
        csvReader.skip(1);
        csvReader.readAll().forEach(investor -> investors.add(investorFactory.create(investor)));
        return investors;
    }
}
