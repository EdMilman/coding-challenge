import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classes representing different types of loan
 */

@EqualsAndHashCode
abstract class LoanType {
    private String loanType = this.getClass().getName();

    /**
     * function to compare class types
     * @param other - other class to be compared
     * @return boolean representing if classes are the same
     */
    boolean compare(LoanType other){
        return this.getClass().equals(other.getClass());
    }

    @Override
    public String toString(){
        return loanType;
    }
}

@NoArgsConstructor
class TrackerLoan extends LoanType{
}

@NoArgsConstructor
class FixedLoan extends LoanType{
}

@NoArgsConstructor
class UnidentifiedLoan extends LoanType{
}