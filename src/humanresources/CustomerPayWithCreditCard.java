package humanresources;

public class CustomerPayWithCreditCard extends Customer {

    public CustomerPayWithCreditCard(int cid, String cname, PaymentMethodOption payMedOpt) {
        super(cid, cname, payMedOpt);
    }

    public void Pay() {
        System.out.println("Payment is settled with credit card.");
    }
}
