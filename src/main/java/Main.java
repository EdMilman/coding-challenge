import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
        InvestmentMatcher matcher = new InvestmentMatcher(new CodeChallengeStrategy());
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
