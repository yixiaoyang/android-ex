package hit.edu.cn;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class YoudaoActivity extends Activity {
	private Button bntSearch;
	private Button bntClean;
	private EditText textContent;
	private WebView mWebViewRes;

	String youdao_URL = "http://dict.youdao.com/m/search?keyfrom=dict.mindex&q=";
	String jinshan_URL = "http://www.iciba.com/";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// 获得布局的几个控件
		bntSearch = (Button) findViewById(R.id.buttonSearch);
		bntClean = (Button) findViewById(R.id.buttonClean);
		textContent = (EditText) findViewById(R.id.editTextContent);
		mWebViewRes = (WebView) findViewById(R.id.myWebViewRes);

		// 如果有js必须设置js使能
		mWebViewRes.getSettings().setJavaScriptEnabled(true);

		// 如果页面中链接，如果希望点击链接继续在当前browser中响应，
		// 而不是新开Android的系统browser中响应该链接，必须覆盖
		// webview的WebViewClient对象
		mWebViewRes.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

		// 查询按钮添加事件
		bntSearch.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String strURI = (textContent.getText().toString());
				strURI = strURI.trim();
				// 如果查询内容为空提示
				if (strURI.length() == 0) {
					Toast.makeText(getApplication(), "查询内容不能为空!",
							Toast.LENGTH_SHORT).show();
				}
				// 否则则以参数的形式从http://dict.youdao.com/m取得数据，加载到WebView里.
				else {
					String strURL = youdao_URL + strURI;
					// String strURL= "www.baidu.com";
					mWebViewRes.loadUrl(strURL);
				}
			}
		});

		// 清空按钮添加事件，将EditText置空
		bntClean.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				textContent.setText("");
			}
		});
	}

	// 设置回退,覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebViewRes.canGoBack()) {
			// goBack()表示返回WebView的上一页面
			mWebViewRes.goBack();
			return true;
		}
		//使用系统的回退设置
		return super.onKeyDown(keyCode, event); 
	}
}