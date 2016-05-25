package mediatorpattern;

public class MediatorPatternDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MessageSwitch messageSwitch = new MessageSwitch();
		Messager facebookUserA = new Messager(messageSwitch, "Bree Olson");
		Messager facebookUserB = new Messager(messageSwitch, "Ashlynn Brooke");
		
		System.out.println("***Mediator Pattern Demo***"); 
		System.out.println();
		
		facebookUserA.send("Ashlynn Brooke", "Purus quam, nonummy et ornare, in ligula quam.");
		facebookUserB.send("Bree Olson", "Facilisis id nulla, ornare elementum id. Aliquam quis odio.");
	}

}
