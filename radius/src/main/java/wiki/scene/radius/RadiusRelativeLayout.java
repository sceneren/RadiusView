package wiki.scene.radius;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import wiki.scene.radius.delegate.RadiusViewDelegate;

/**
 * @Author: AriesHoo on 2018/7/19 10:06
 * @E-Mail: AriesHoo@126.com
 * Function:用于需要圆角矩形框背景的RelativeLayout的情况,减少直接使用RelativeLayout时引入的shape资源文件
 * Description:
 */
public class RadiusRelativeLayout extends RelativeLayout {
    private RadiusViewDelegate delegate;

    public RadiusRelativeLayout(Context context) {
        this(context, null);
    }

    public RadiusRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        delegate = new RadiusViewDelegate(this, context, attrs);
    }

    /**
     * 获取代理类用于Java代码控制shape属性
     *
     * @return
     */
    public RadiusViewDelegate getDelegate() {
        return delegate;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (delegate != null && delegate.getWidthHeightEqualEnable() && getWidth() > 0 && getHeight() > 0) {
            int max = Math.max(getWidth(), getHeight());
            int measureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.EXACTLY);
            super.onMeasure(measureSpec, measureSpec);
            if (delegate != null) {
                delegate.init();
            }
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (delegate != null) {
            delegate.init();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (delegate != null) {
            if (delegate.getRadiusHalfHeightEnable()) {
                delegate.setRadius(getHeight() / 2);
            }
            delegate.init();
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (delegate != null) {
            delegate.setSelected(selected);
        }
    }
}