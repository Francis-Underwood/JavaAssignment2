package humanresources;

public class CustomerPayWithCreditCard extends Customer {

    public CustomerPayWithCreditCard(int cid, int eid, String cname, 
                            String aname, PaymentMethodOption payMedOpt) {
        super(cid, eid, cname, aname, payMedOpt);
    }

    public void Pay() {
        System.out.println("Payment is settled with credit card.");
    }
}
