package adapterpattern;

public class FaceBookAdapter extends SocialMedia{
	private FaceBook _fb = new FaceBook();
	public FaceBookAdapter(){}
	@Override
	public void shareURL(String postURL, String postTitle, Boolean isVideo) {
		this._fb.ShareURL(postURL, postTitle);
	}
}
