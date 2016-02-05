package vandy.mooc.presenter;

import vandy.mooc.common.Utils;
import vandy.mooc.model.ImageDownloadsModel;
import vandy.mooc.utils.loader.ImageLoaderThreadPool;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadImageAsyncTask extends AsyncTask<Uri, Void, Uri> {

	protected final static String TAG = DownloadImageAsyncTask.class.getSimpleName();
	
	ImagePresenter mPresenter;
	Context mContext;
	Uri mImgUrl;
	Uri mDirPathname;
	
	public DownloadImageAsyncTask(ImagePresenter presenter, Context context) {
		mPresenter = presenter;
		mContext = context;
	}
		
	@Override
	protected Uri doInBackground(Uri... params) {
		mImgUrl = params[0];
		mDirPathname = params[1];
		
		return mPresenter.getModel().downloadImage(mContext, params[0], params[1]);
	}

	protected void onPostExecute(Uri imagePath) {
	    if (imagePath != null) {
			Log.i(TAG, "Image downloaded.");
		    
		    // start the filter task
		    FilterImageAsyncTask filterTask = new FilterImageAsyncTask(mPresenter, mContext);
		    
		    // start the added AsyncTask
		    filterTask.executeOnExecutor(ImageLoaderThreadPool.MY_THREAD_POOL_EXECUTOR, imagePath, mDirPathname);
	    } else {
	    	Log.e(TAG, "Image NOT downloaded.");
	    	mPresenter.onProcessingComplete(mImgUrl, null);
	    }
	}
}
