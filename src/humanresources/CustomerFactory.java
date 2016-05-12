package humanresources;

//factory pattern
public final class CustomerFactory {
    public Customer createCustomer(int cid, int eid, String cname,
                                String aname, PaymentMethodOption payMedOpt) {
        switch (payMedOpt) {
            case CASH:
                return new CustomerPayCash(cid, eid, cname, aname, payMedOpt);
            case CREDITCARD:
            default:
                return new CustomerPayWithCreditCard(cid, eid, cname, aname, payMedOpt);
        }
    }
}
