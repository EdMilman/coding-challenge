import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Injector guice = Guice.createInjector(new GuiceInjector());
        InvestmentMatcher matcher = guice.getInstance(InvestmentMatcher.class);
        try {
            matcher.readInvestors("investmentRequests.csv");
            matcher.readLoans("loans.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        matcher.match();
        System.out.println(matcher.printJson());
    }
}
