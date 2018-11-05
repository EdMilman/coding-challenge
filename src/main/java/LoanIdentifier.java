/**
 * utility class used to identify loan
 */
public class LoanIdentifier {
    /**
     * static method to dynamically create appropriate LoanType class based on string input
     *
     * @param loan - string of LoanType
     * @return implementation of correct sort of loan, or unidenified loan if error is thrown
     */
    static LoanType assignLoanType(String loan) {
        String name = loan.substring(0, 1).toUpperCase() + loan.substring(1).toLowerCase() + "Loan";
        try {
            Class clazz = Class.forName(name);
            return (LoanType) clazz.getConstructor().newInstance();
        } catch (Exception e) {
            return new UnidentifiedLoan();
        }
    }
}