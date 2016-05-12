package humanresources;

public class Customer {

    private int cid;
    private int eid;
    private String cname;
    private String agentName;
    private PaymentMethodOption paymentMethod;

    public Customer(int cid, int eid, String cname, String aname, 
                    PaymentMethodOption payMedOpt) {
        this.cid = cid;
        this.eid = eid;
        this.cname = cname;
        this.agentName = aname;
        this.paymentMethod = payMedOpt;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
    
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
    
    public String getAgentName() {
        return this.agentName;
    }

    public void setAgentName(String aname) {
        this.agentName = aname;
    }

    public PaymentMethodOption getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodOption paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
