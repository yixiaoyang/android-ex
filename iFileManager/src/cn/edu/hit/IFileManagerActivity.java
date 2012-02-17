package cn.edu.hit;

/*
 * auther:xiaoyang 2012-2@HIT
 * 
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class IFileManagerActivity extends Activity {
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		/*load buttom menu*/
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		 super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case R.id.itemNew:{
			break;
		}
		case R.id.itemsetting:{
			break;
		}
		case R.id.itemAbout:{
			break;
		}
		case R.id.itemExit:{
			break;
		}
		}
		return true;
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}