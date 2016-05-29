package prototypepattern;

public class PrototypePatternDemo {

	public static void main(String[] args) {
		System.out.println("***Prototype Pattern Demo***"); 
		System.out.println();
		
		Mercurial originMercurial = new Mercurial(100, 76);
		originMercurial.render();
		originMercurial.setLife(35);
		originMercurial.render();
		System.out.println();
		
		Terrorblade originTerrorblade = new Terrorblade(100, 48);
		originTerrorblade.render();
		originTerrorblade.setLife(75);
		originTerrorblade.render();
		System.out.println();
		
		Mercurial reflectionIllusion1_Mercurial = (Mercurial)originMercurial.clone();
		reflectionIllusion1_Mercurial.render();
		Mercurial reflectionIllusion2_Mercurial = (Mercurial)originMercurial.clone();
		reflectionIllusion2_Mercurial.render();
		System.out.println();
		
		Terrorblade reflectionIllusion1_Terrorblade = (Terrorblade)originTerrorblade.clone();
		reflectionIllusion1_Terrorblade.setLife(20);
		reflectionIllusion1_Terrorblade.render();
		Terrorblade reflectionIllusion2_Terrorblade = (Terrorblade)originTerrorblade.clone();
		reflectionIllusion2_Terrorblade.setLife(14);
		reflectionIllusion2_Terrorblade.render();
		System.out.println();
		
		
	}

}
