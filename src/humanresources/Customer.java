package humanresources;

public class Customer {

    private int cid;
    private String cname;
    private PaymentMethodOption paymentMethod;

    public Customer(int cid, String cname, PaymentMethodOption payMedOpt) {
        this.cid = cid;
        this.cname = cname;
        this.paymentMethod = payMedOpt;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public PaymentMethodOption getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodOption paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
