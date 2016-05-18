package adapterpattern;

public class FaceBook {
	public FaceBook() {}
	public void ShareURL(String postURL, String desc) {
		System.out.println("Share a post on user's Facebook page.");
		System.out.println("URL:\t\t" + postURL);
		System.out.println("Description:\t" + desc);
	}
}
