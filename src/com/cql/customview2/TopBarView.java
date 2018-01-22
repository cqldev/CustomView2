package com.cql.customview2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopBarView extends RelativeLayout {
    
    private Context mContext;
    
    private TypedArray ta;
    
    private Button leftButton;
    
    private TextView titleView;
    
    private Button rightButton;
    
    private String leftText;
    private int leftTextColor;
    private Drawable leftBackground;
    
    private String titleText;
    private int titleTextColor;
    private float titleSize;
    
    private String rightText;
    private int rightTextColor;
    private Drawable rightBackground;
    
    private TopBarClickListener mListener;
    
    public void setTopBarListener(TopBarClickListener listener){
        this.mListener = listener;
    }

    public TopBarView(Context context) {
        this(context, null);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
        ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        initAttrs(ta);
        initViews();
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    
    @Override
    protected void onFinishInflate() {

        leftButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
        
        rightButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });
    }

    /**
     * RelativeLayout中已经重写了 onMeasure() 方法，支持了 wrap_content属性，因此此处不需要重写，直接调用RelativeLayout 中的方法
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        
    }
    
    private void initAttrs(TypedArray ta){
        
        leftText = ta.getString(R.styleable.TopBar_leftText);
        leftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        leftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        
        titleText = ta.getString(R.styleable.TopBar_titleText);
        titleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        titleSize = ta.getDimension(R.styleable.TopBar_titleSize, 20);
        
        rightText = ta.getString(R.styleable.TopBar_rightText);
        rightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        rightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        
        ta.recycle();
    }
    
    private void initViews(){
        leftButton = new Button(mContext);
        titleView = new TextView(mContext);
        rightButton = new Button(mContext);
        
        leftButton.setText(leftText);
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        
        titleView.setText(titleText);
        titleView.setTextColor(titleTextColor);
        titleView.setTextSize(titleSize);
        titleView.setGravity(Gravity.CENTER);
        
        rightButton.setText(rightText);
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        
        LayoutParams leftLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(leftButton, leftLayoutParams);
        
        LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        titleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(titleView, titleLayoutParams);
        
        LayoutParams rightLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightButton, rightLayoutParams);
    }
    
    public interface TopBarClickListener {
         
        void leftClick();
        
        void rightClick();
    }

}
