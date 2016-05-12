package humanresources;

//factory pattern
public final class CustomerFactory {
    public Customer createCustomer(int cid, String cname, PaymentMethodOption payMedOpt) {
        switch (payMedOpt) {
            case CASH:
                return new CustomerPayCash(cid, cname, payMedOpt);
            case CREDITCARD:
            default:
                return new CustomerPayWithCreditCard(cid, cname, payMedOpt);
        }
    }
}
