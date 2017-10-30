package com.frame.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ReplacementTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.frame.util.ConfigSettings;
import com.frame.util.keybord.KeyBordUtil;
import com.frame.util.storage.SharepreferenceUtil;
import com.frame.util.view.edit_view.ClearEditText;


/**
 * 作者:Created by 简玉锋 on 2017/7/24 16:36
 * 邮箱: jianyufeng@38.hn
 * <p>
 * 登录界面获取软键盘的高度 存储到配置文件中
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {


    private static final String TAG = "LoginActivity";

    private ScrollView scrollView;
    private TextView login;



    private ClearEditText account;
    private ClearEditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局
        setContentView(R.layout.login_activity);


        //获取组件
        scrollView = (ScrollView) findViewById(R.id.scrollView_id);
        login = (TextView) findViewById(R.id.login_id);
        //设置点击监听
        login.setOnClickListener(this);

        account = (ClearEditText) findViewById(R.id.account);

        pass = (ClearEditText) findViewById(R.id.password);
        pass.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        pass.setOnEditorActionListener(this);
        //替换密码默认显示形式
        pass.setTransformationMethod(new PasswordReplace());

    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_id://登录点击事件
                //登录的逻辑
                login();
                break;
            default:
                break;
        }
    }

    private void scrollBottom(final View v) {
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                //滑动到底部
                scrollView.scrollTo(0, scrollView.getHeight());
            }
        }, 300);
    }

    private void login() {
        /*隐藏软键盘*/
        KeyBordUtil.hideKeyBoard(this);
        int h = (int) SharepreferenceUtil.get(getApplicationContext(), ConfigSettings.KEYBOARD_HEIGHT, 0);
        Toast.makeText(getApplicationContext(), "H:" + h + "  pass::" + pass.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    //软件键盘 点击的事件回调
    //处理确定按钮
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            login();
            return true;
        }
        return false;
    }


    /**
     * 密码默认显示替换成  * 好
     */


     static class  PasswordReplace extends ReplacementTransformationMethod {

        String strWord = null;

        @Override
        protected char[] getOriginal() {
            //循环ASCII值 字符串形式累加到String
            for (char i = 0; i < 256; i++) {
                strWord += String.valueOf(i);
            }
            //strWord转换为字符形式的数组
            char[] charOriginal = strWord.toCharArray();
            return charOriginal;
        }

        @Override
        protected char[] getReplacement() {
            char[] charReplacement = new char[255];
            //输入的字符在ASCII范围内，将其转换为*
            for (int i = 0; i < 255; i++) {
                charReplacement[i] = '*';
            }

            return charReplacement;
        }

    }
}
