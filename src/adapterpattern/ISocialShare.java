package adapterpattern;

public interface ISocialShare {
	public abstract void shareURL(String postURL, String postTitle, Boolean isVideo);
}
