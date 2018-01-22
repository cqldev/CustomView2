
package com.cql.customview2;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements TopBarView.TopBarClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        ViewGroup mContentParent = (ViewGroup) getWindow().getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
//        TopBarView topBar = new TopBarView(this);
//        mContentParent.addView(topBar,0);
        
        TopBarView topBar = (TopBarView) findViewById(R.id.topbar);
        topBar.setTopBarListener(this);
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        
    }
}
