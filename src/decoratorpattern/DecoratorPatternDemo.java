package decoratorpattern;

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        System.out.println("***Decorator Pattern Demo***"); 
        System.out.println();
        IComponent auderysPhoto = new Photo("Audery Bitoni");
        System.out.println(auderysPhoto.operate());
        IComponent auderysRetouchedPhoto_Stage1 = new ContrastFilter(auderysPhoto, 35);
        System.out.println(auderysRetouchedPhoto_Stage1.operate());
        IComponent auderysRetouchedPhoto_Stage2 = new BrightnessFilter(auderysRetouchedPhoto_Stage1, 70);
        System.out.println(auderysRetouchedPhoto_Stage2.operate());
        IComponent auderysRetouchedPhoto_Stage3 = new SaturationFilter(auderysRetouchedPhoto_Stage2, 23);
        System.out.println(auderysRetouchedPhoto_Stage3.operate());
        System.out.println();
        IComponent sophiasFinalRetouchedPhoto = 
                        new ContrastFilter(
                            new BrightnessFilter(
                                new SaturationFilter(
                                    new Photo("Sophia Santi"), 
                                    40
                                ), 
                                38
                            ), 
                            45
                        );
        System.out.println(sophiasFinalRetouchedPhoto.operate());
        System.out.println();
        IComponent anotherSophiasFinalRetouchedPhoto = 
                        new BrightnessFilter(
                            new SaturationFilter(
                                new BrightnessFilter(
                                    new ContrastFilter(
                                            new Photo("Another Sophia Santi"), 
                                            6
                                        ), 
                                    40), 
                                38), 
                            45);
        System.out.println(anotherSophiasFinalRetouchedPhoto.operate());	
    }
}
