package adapterpattern;

public class AdapterPatternDemo {
    public static void main(String[] args) {
        String url = "www.codebabe.com";
        String title = "A hot babe teaching how to code";
        Boolean isVideo = true;
        System.out.println("***Adapter Pattern Demo***"); 
        System.out.println();
        ISocialShare twitterAccount = new SocialMedia();
        twitterAccount.shareURL(url, title, isVideo);
        System.out.println();
        ISocialShare facebookAccount = new FaceBookAdapter();
        facebookAccount.shareURL(url, title, isVideo);
    }
}
