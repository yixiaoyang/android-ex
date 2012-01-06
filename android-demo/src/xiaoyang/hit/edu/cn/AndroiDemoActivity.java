package xiaoyang.hit.edu.cn;

import android.app.Activity;
import android.os.Bundle;

/*
 * xiaoyang
 * 2011-10-22
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.view.Gravity;
import android.widget.Toast;

/*
 * 注意创建实体的顺序
 * 比如ls不能放在bnt1.setOnClickListener(ls);等注册语句之后，否则虽然能够运行，但是按钮没有反应
 */
public class AndroiDemoActivity extends Activity  {
    View.OnClickListener ls;
    Button bnt1;
    Button bnt2;
    Button bnt3;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        bnt1 = (Button)findViewById(R.id.Button01);
        bnt2 = (Button)findViewById(R.id.Button02);
        bnt3 = (Button)findViewById(R.id.Button03);
       
        /**
         * 按钮响应事件
         */
        ls = new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                switch (arg0.getId()) {
                    case R.id.Button01: {
                        setTitle("this is button yi"); 
                        Toast toast = Toast.makeText(getApplication(), 
                                                    "你点击了按钮" + bnt1.getText().toString(),
                                                    Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP,0,250);
                        toast.show();
                        break;
                    }
                    case R.id.Button02: {
                        setTitle("this is button xiaoyang");break;
                    }
                    
                    case R.id.Button03: {
                        finish(); 
                        break;
                    }
                }
            }
        };
        
        bnt1.setOnClickListener(ls);
        bnt2.setOnClickListener(ls);
        bnt3.setOnClickListener(ls);
    }
}