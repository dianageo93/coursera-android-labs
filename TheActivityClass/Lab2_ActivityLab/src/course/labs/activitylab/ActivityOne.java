package course.labs.activitylab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityOne extends Activity {

	// Use these as keys when you're saving state between reconfigurations
	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityOne";

	// Lifecycle counters
    private int mCreate = 0;
	private int mRestart = 0;
	private int mStart = 0;
	private int mResume = 0;

	// You will need to increment these variables' values when their
	// corresponding lifecycle methods get called.
    private TextView mTvCreate;
    private TextView mTvRestart;
    private TextView mTvStart;
    private TextView mTvResume;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);

		mTvCreate = (TextView) findViewById(R.id.create);
        mTvRestart = (TextView) findViewById(R.id.restart);
        mTvStart = (TextView) findViewById(R.id.start);
        mTvResume = (TextView) findViewById(R.id.resume);

        final Context mContext = this;

		Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo);
		launchActivityTwoButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Create an intent stating which Activity you would like to
                // start
                Intent intent = new Intent(mContext, ActivityTwo.class);

                // Launch the Activity using the intent
                startActivity(intent);

            }
        });

		// Has previous state been saved?
		if (savedInstanceState != null) {
            mStart = ((Integer) savedInstanceState.get(START_KEY)).intValue();
            mRestart = ((Integer) savedInstanceState.get(RESTART_KEY)).intValue();
            mResume = ((Integer) savedInstanceState.get(RESUME_KEY)).intValue();
            mCreate = ((Integer) savedInstanceState.get(CREATE_KEY)).intValue();
		}

		// Emit LogCat message
		Log.i(TAG, "Entered the onCreate() method");

        mCreate++;
        displayCounts();

	}

	// Lifecycle callback overrides

	@Override
	public void onStart() {
		super.onStart();

		// Emit LogCat message
		Log.i(TAG, "Entered the onStart() method");

		mStart++;
        displayCounts();
	}

	@Override
	public void onResume() {
		super.onResume();

		// Emit LogCat message
		Log.i(TAG, "Entered the onResume() method");

		mResume++;
        displayCounts();

	}

	@Override
	public void onPause() {
		super.onPause();

		// Emit LogCat message
		Log.i(TAG, "Entered the onPause() method");
	}

	@Override
	public void onStop() {
		super.onStop();

		// Emit LogCat message
		Log.i(TAG, "Entered the onStop() method");
	}

	@Override
	public void onRestart() {
		super.onRestart();

		// Emit LogCat message
		Log.i(TAG, "Entered the onRestart() method");

		mRestart++;
        displayCounts();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		// Emit LogCat message
		Log.i(TAG, "Entered the onDestroy() method");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putInt(RESTART_KEY, new Integer(mRestart));
        savedInstanceState.putInt(RESUME_KEY, new Integer(mResume));
        savedInstanceState.putInt(START_KEY, new Integer(mStart));
        savedInstanceState.putInt(CREATE_KEY, new Integer(mCreate));

        super.onSaveInstanceState(savedInstanceState);
	}

	// Updates the displayed counters
	// This method expects that the counters and TextView variables use the
	// names
	// specified above
	public void displayCounts() {
        mTvCreate.setText("onCreate() calls: " + mCreate);
        mTvStart.setText("onStart() calls: " + mStart);
        mTvResume.setText("onResume() calls: " + mResume);
        mTvRestart.setText("onRestart() calls: " + mRestart);

	}
}
