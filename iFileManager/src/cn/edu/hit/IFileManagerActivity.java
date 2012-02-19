package cn.edu.hit;

/*
 * auther:xiaoyang 2012-2@HIT
 * 
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class IFileManagerActivity extends ListActivity implements
		OnClickListener {
	/* declare the components on this activity */
	private ImageButton bntHome;
	private ImageButton bntGridview;
	private ImageButton bntSearch;
	private ImageButton bntUplevel;
	private ImageButton bntOperate;
	private ImageButton bntSetting;

	public static boolean if_root = false;
	/* global data */
	private static final String[] type = { "图像", "音乐", "视频", "apk", "自定义" };
	private ArrayAdapter<String> typeAdapter;
	private List<FileUnit> files = new ArrayList<FileUnit>();
	ScanFileTask scanFileTask;

	// private GridViewAdapter gridViewAdapter;
	private FileListAdapter fileListAdapter;

	/* 文件相关声明 */
	private String favoritPath = "/sdcard"; // 个人目录
	private String rootPath = favoritPath; // rootPath：起始文件夹
	private String currentDirctory = favoritPath;
	private boolean isHomeDirctory = true;
	private boolean isListViewShow = true;

	private boolean getRootJurisdiction() {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("su");
			process.waitFor();
			//if_root = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			process.destroy();
		}
		return false;
	}

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

	public boolean checkSD() {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast toast0 = Toast.makeText(getApplication(), "没有检测到SD卡！",
					Toast.LENGTH_SHORT);
			toast0.setGravity(Gravity.TOP, 0, 250);
			toast0.show();
			return false;
		}

		return true;
	}

	/* actions */
	public void backToParent() {
		File file = new File(currentDirctory.trim());
		// 是否退出
		if (rootPath.equals(currentDirctory.trim())) {
			isExit();
		} else {
			scanFile(file.getParent());
			currentDirctory = file.getParent();
			this.setTitle(currentDirctory);

		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/* bind the toolbar actions */
		bindToolbarView();

		/* check SD card */
		if (checkSD()) {
			fileListAdapter = new FileListAdapter(this, files);
			setListAdapter(fileListAdapter);
			scanFile(rootPath);
			this.setTitle(currentDirctory);
		}

		getRootJurisdiction();
	}

	/* button clicked actions */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imageButtonHome:
			Toast toast0 = Toast.makeText(getApplication(), "你点击了home按钮",
					Toast.LENGTH_SHORT);
			toast0.setGravity(Gravity.TOP, 0, 250);
			toast0.show();
			break;
		case R.id.imageButtonGridview:
			Toast toast1 = Toast.makeText(getApplication(), "你点击了Gridview按钮",
					Toast.LENGTH_SHORT);
			toast1.setGravity(Gravity.TOP, 0, 250);
			toast1.show();
			break;
		case R.id.imageButtonSearch:

			break;
		case R.id.imageButtonUplevel:
			backToParent();
			break;
		case R.id.imageButtonOperation:

			break;
		case R.id.imageButtonSetting:

			break;
		default:

			break;
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		File file;
		try {
			file = new File(files.get(position).getPath());
		} catch (NullPointerException e) {
			return;
		}
		
		String fpath = currentDirctory+"/"+files.get(position).getName();
		/* system file */
		if (!if_root) {
			if (SystemFilesList.ifSysFile(fpath)) {
				new AlertDialog.Builder(this)
						.setTitle("提示")
						.setMessage("抱歉，您没有权限访问系统文件！")
						.setPositiveButton("关闭",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface di,
											int ii) {
									}
								}).show();
				return;
			}
		}
		
		if (file.isDirectory()) {
			scanFile(file.getPath());

			currentDirctory = file.getPath();
			this.setTitle(currentDirctory);
		} else {
			// openFile(file);
		}
	}


	/*
	 * setting uplevel actions and override onKeyDown(int keyCoder,KeyEvent
	 * event)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			// uplevel
			backToParent();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/* loaf file list */
	public void scanFile(String s) {
		if (scanFileTask != null
				&& scanFileTask.getStatus() == ScanFileTask.Status.RUNNING) {
			scanFileTask.cancel(true);
		}
		scanFileTask = new ScanFileTask();
		scanFileTask.execute(s);
	}

	/* exit program */
	public void isExit() {

	}

	/* search files */
	class ScanFileTask extends AsyncTask<String, Integer, Integer> {
		public ScanFileTask() {
			files.clear();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Integer doInBackground(String... params) {
			if (params == null || params[0] == null || "".equals(params[0])) {
				return 0;
			}

			File file = new File(params[0]);
			int fileCount = 0;
			File[] allfiles = null;
			allfiles = file.listFiles();
			List<FileUnit> data_dir = new ArrayList<FileUnit>();
			List<FileUnit> data_file = new ArrayList<FileUnit>();

			if (allfiles != null) {
				for (File f : allfiles) {
					FileUnit fileBean = new FileUnit();
					if (f.isDirectory()) {
						fileBean.setFile(f);
						fileBean.setName(f.getName());
						fileBean.setPath(f.getPath());
						fileBean.setFileSize("");
						data_dir.add(fileBean);
					}
				}
				Collections.sort(data_dir, new FileUnit());
				files.addAll(data_dir);

				for (File f : allfiles) {
					fileCount++;
					FileUnit fileBean = new FileUnit();
					if (f.isFile()) {
						fileBean.setFile(f);
						fileBean.setName(f.getName());
						fileBean.setPath(f.getPath());
						fileBean.setFileSize(FileHandle.getFileSize(f));
						data_file.add(fileBean);
					}
				}
				Collections.sort(data_file, new FileUnit());
				files.addAll(data_file);

			} else {// 文件目录为空
				FileUnit fileBean = new FileUnit();
				fileBean.setName("");
				fileBean.setPath(null);
				fileBean.setFileSize("");
				files.add(fileBean);
				fileCount = 1;
			}
			return fileCount;
		}

		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}

		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			if (isListViewShow) {
				fileListAdapter.notifyDataSetChanged();// ListView
			} else {
				// emptyTextView.setVisibility(View.GONE);
				// gridViewAdapter.notifyDataSetChanged();// GridView
				fileListAdapter.notifyDataSetChanged();// ListView
			}

		}

	}
}