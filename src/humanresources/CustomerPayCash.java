package humanresources;

public class CustomerPayCash extends Customer {

    public CustomerPayCash(int cid, String cname, PaymentMethodOption payMedOpt) {
        super(cid, cname, payMedOpt);
    }

    public void Pay() {
        System.out.println("Payment is settled with cash.");
    }
}
