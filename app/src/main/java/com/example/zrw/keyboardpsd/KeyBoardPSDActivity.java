package com.example.zrw.keyboardpsd;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.zip.Inflater;

public class KeyBoardPSDActivity extends AppCompatActivity {
    PopupWindow p;
    View view;
    PasswordView pwdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        //设置长宽度
        p = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view =LayoutInflater.from(KeyBoardPSDActivity.this).inflate(R.layout.activity_main, null);
        pwdView = (PasswordView) view.findViewById(R.id.pwd_view);
        p.setContentView(view);

        //添加密码输入完成的响应
        pwdView.setOnFinishInput(new OnPasswordInputFinish() {
            @Override
            public void inputFinish() {
                //输入完成后我们简单显示一下输入的密码
                //也就是说——>实现你的交易逻辑什么的在这里写
                Toast.makeText(KeyBoardPSDActivity.this, pwdView.getStrPassword(), Toast.LENGTH_SHORT).show();
            }
        });

        /**
         *  可以用自定义控件中暴露出来的cancelImageView方法，重新提供相应
         *  如果写了，会覆盖我们在自定义控件中提供的响应
         *  可以看到这里toast显示 "Biu Biu Biu"而不是"Cancel"*/
        pwdView.getCancelImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pwdView.isNull()){
                    pwdView.clearText();
                }
//                Log.e("sdfsdf",""+pwdView.getTextSize());
                p.dismiss();
//                Toast.makeText(KeyBoardPSDActivity.this, "Biu Biu Biu", Toast.LENGTH_SHORT).show();
            }
        });
       pwdView.getForgetTextView().setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(KeyBoardPSDActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
           }
       });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void click(View v) {
        p.showAsDropDown(v);
    }


}
