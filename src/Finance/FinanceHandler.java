/*
 * @author He "Holy Warrior" Engelund
 * 20/05/2021 18:53
 *
 * DAT21V2-Projekt-Delfinen
 */

package Finance;
import Member.Member;
import Service.UserInput;

// ONLY CONNECT TO THIS CLASS TO INTERACT WITH FINANCE DEPT.
public class FinanceHandler {
    // TODO: MAKE SURE YOU CANNOT PAY MORE THAN YOU'RE SUPPOSED TO! IF WE GOT TIME
    public void makePayment(Member member, double amount) {
        new PaymentLog().writePaymentToLog(new Payment(member, amount));
    }

    public void displayAnnualBudget() {
        UserInput.console.println(new Budget().toString());
    }

    public void displayAllPayments() {
        new PaymentLog().displayAllPayments();
    }

    public void viewOverduePayments() {
        new FinanceReport().printOverduePayments();
    }
}
