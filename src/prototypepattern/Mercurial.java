package prototypepattern;

public class Mercurial extends Character {
    public Mercurial(int l, int p) {
        super(l, p);
    }
    @Override
    public void render() {
        System.out.println("A Mercurial is rendered, with life of " + 
                        this.getLife() + ", and power of " + this.getPower() + ".");
    }
}
