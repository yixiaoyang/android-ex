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

		// ��ò��ֵļ����ؼ�
		bntSearch = (Button) findViewById(R.id.buttonSearch);
		bntClean = (Button) findViewById(R.id.buttonClean);
		textContent = (EditText) findViewById(R.id.editTextContent);
		mWebViewRes = (WebView) findViewById(R.id.myWebViewRes);

		// �����js��������jsʹ��
		mWebViewRes.getSettings().setJavaScriptEnabled(true);

		// ���ҳ�������ӣ����ϣ��������Ӽ����ڵ�ǰbrowser����Ӧ��
		// �������¿�Android��ϵͳbrowser����Ӧ�����ӣ����븲��
		// webview��WebViewClient����
		mWebViewRes.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

		// ��ѯ��ť����¼�
		bntSearch.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String strURI = (textContent.getText().toString());
				strURI = strURI.trim();
				// �����ѯ����Ϊ����ʾ
				if (strURI.length() == 0) {
					Toast.makeText(getApplication(), "��ѯ���ݲ���Ϊ��!",
							Toast.LENGTH_SHORT).show();
				}
				// �������Բ�������ʽ��http://dict.youdao.com/mȡ�����ݣ����ص�WebView��.
				else {
					String strURL = youdao_URL + strURI;
					// String strURL= "www.baidu.com";
					mWebViewRes.loadUrl(strURL);
				}
			}
		});

		// ��հ�ť����¼�����EditText�ÿ�
		bntClean.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				textContent.setText("");
			}
		});
	}

	// ���û���,����Activity���onKeyDown(int keyCoder,KeyEvent event)����
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebViewRes.canGoBack()) {
			// goBack()��ʾ����WebView����һҳ��
			mWebViewRes.goBack();
			return true;
		}
		//ʹ��ϵͳ�Ļ�������
		return super.onKeyDown(keyCode, event); 
	}
}