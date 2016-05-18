package adapterpattern;

public class SocialMedia implements ISocialShare {
	public SocialMedia() {}
	@Override
	public void shareURL(String postURL, String postTitle, Boolean isVideo) {
		System.out.println("Share a post on user's general social media site page.");
		System.out.println("URL:\t" + postURL);
		System.out.println("Title:\t" + postTitle);
		System.out.println("Whether it's a video:\t" + (isVideo?"Yes":"No"));
	}
}
