package cn.edu.hit;

/*
 * auther:xiaoyang 2012-2@HIT
 * 
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class IFileManagerActivity extends Activity implements OnClickListener {
	/* declare the components on this activity */
	private ImageButton bntHome;
	private ImageButton bntGridview;
	private ImageButton bntSearch;
	private ImageButton bntUplevel;
	private ImageButton bntOperate;
	private ImageButton bntSetting;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		/* load buttom menu */
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.itemNew: {
			break;
		}
		case R.id.itemsetting: {
			break;
		}
		case R.id.itemAbout: {
			break;
		}
		case R.id.itemExit: {
			break;
		}
		}
		return true;
	}

	/* bind the imageButton (toolbars) icons */
	public void bindToolbarView() {
		bntHome = (ImageButton) findViewById(R.id.imageButtonHome);
		bntGridview = (ImageButton) findViewById(R.id.imageButtonGridview);
		bntSearch = (ImageButton) findViewById(R.id.imageButtonHome);
		bntUplevel = (ImageButton) findViewById(R.id.imageButtonUplevel);
		bntOperate = (ImageButton) findViewById(R.id.imageButtonOperation);
		bntSetting = (ImageButton) findViewById(R.id.imageButtonSetting);

		bntHome.setOnClickListener(this);
		bntGridview.setOnClickListener(this);
		bntSearch.setOnClickListener(this);
		bntUplevel.setOnClickListener(this);
		bntOperate.setOnClickListener(this);
		bntSetting.setOnClickListener(this);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		/*bind the toolbar actions*/
		bindToolbarView();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imageButtonHome:
			Toast toast0 = Toast.makeText(getApplication(), 
                    "你点击了home按钮",
                    Toast.LENGTH_SHORT);
			toast0.setGravity(Gravity.TOP,0,250);
			toast0.show();
			break;
		case R.id.imageButtonGridview:
			Toast toast1 = Toast.makeText(getApplication(), 
                    "你点击了Gridview按钮",
                    Toast.LENGTH_SHORT);
			toast1.setGravity(Gravity.TOP,0,250);
			toast1.show();
			break;
		case R.id.imageButtonSearch:
			
			break;
		case R.id.imageButtonUplevel:
			
			break;
		case R.id.imageButtonOperation:
			
			break;
		case R.id.imageButtonSetting:
			
			break;
		default:
	
			break;
		}

	}
}