package vandy.mooc.presenter;

import java.io.File;

import vandy.mooc.common.BitmapUtils;
import vandy.mooc.common.Utils;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class FilterImageAsyncTask extends AsyncTask<Uri, Void, Uri> {

	protected final static String TAG = FilterImageAsyncTask.class.getSimpleName();
	
	ImagePresenter mPresenter;
	Context mContext;
	Uri mColorImagePath;
	Uri mDirPathname;
	
	public FilterImageAsyncTask(ImagePresenter presenter, Context context) {
		mPresenter = presenter;
		mContext = context;
	}
		
	@Override
	protected Uri doInBackground(Uri... params) {
		mColorImagePath =  params[0];
		mDirPathname = params[1];
		
		return BitmapUtils.grayScaleFilter(mContext, params[0], params[1]);
	}

	protected void onPostExecute(Uri imagePath) {
	    Log.i(TAG, "Image filtered. Deleting color image. "
	    		+ "Filtered image path: " + imagePath);
	    
	    //Delete the original (colored) image
	    if (!(new File(mColorImagePath.toString()).delete())) {
	    	Log.e(TAG, "Colored image NOT deleted");
	    }
	    
	    mPresenter.onProcessingComplete(imagePath, mDirPathname);
	}
}
