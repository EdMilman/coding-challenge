public class LoanIdentifier {
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