import com.opencsv.CSVReader;
import lombok.NoArgsConstructor;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * class to hold methods relating to csv operations
 */
@NoArgsConstructor
public class CsvUtils {
    /**
     * reads in details of loans
     * @param file - file to be read
     * @return - ArrayList of loans
     * @throws IOException
     */
    public ArrayList<LoanImpl> readLoans(String file) throws IOException {
        FileReader filereader = new FileReader(file);
        CSVReader csvReader = new CSVReader(filereader);
        ArrayList<LoanImpl> loanImpls = new ArrayList<>();
        csvReader.skip(1);
        csvReader.readAll().forEach(loan -> loanImpls.add(new LoanImpl(loan)));
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
        csvReader.readAll().forEach(investor -> investors.add(new InvestorImpl(investor)));
        return investors;
    }
}
