package humanresources;

public class CustomerPayCash extends Customer {

    public CustomerPayCash(int cid, int eid, String cname, 
                        String aname, PaymentMethodOption payMedOpt) {
        super(cid, eid, cname, aname, payMedOpt);
    }

    public void Pay() {
        System.out.println("Payment is settled with cash.");
    }
}
