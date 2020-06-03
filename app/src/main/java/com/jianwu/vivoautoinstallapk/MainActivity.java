package com.jianwu.vivoautoinstallapk;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText passwordInputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordInputView = findViewById(R.id.password);

        passwordInputView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    savePassWord();
                    return true;
                }
                return false;
            }
        });

        String initValue = getPassWord();
        if (initValue != null) {
            passwordInputView.setText(initValue);
            passwordInputView.setSelection(initValue.length());
        }
    }

    private String getPassWord() {
        return SharePreferencesUtils.getParam(getApplication(), AppConstants.KEY_PASSWORD, "");
    }

    private void savePassWord() {
        String pwdStr = passwordInputView.getText().toString().trim();
        if (TextUtils.isEmpty(pwdStr)) {
            return;
        }

        SharePreferencesUtils.setParam(getApplication(), AppConstants.KEY_PASSWORD, pwdStr);
        Toast.makeText(getApplication(), "保存成功", Toast.LENGTH_SHORT).show();
    }

    public void openAccessibility(View view) {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }

}
