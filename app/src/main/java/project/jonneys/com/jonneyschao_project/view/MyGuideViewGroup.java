package project.jonneys.com.jonneyschao_project.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Scroller;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 * 自定义的可以垂直方向滑动的ViewPager
 *  @author jonneys
 */
public class MyGuideViewGroup extends ViewGroup {

        // 用于滑动的类
        private Scroller scroller;
        // 用来跟踪触摸速度的类
        private VelocityTracker velocityTracker;
        // 当前的屏幕视图
        private int curScreen;
        // 默认的显示视图
        private int defaultScreen = 0;
        // 无事件的状态
        private static final int TOUCH_STATE_REST = 0;
        // 处于拖动的状态
        private static final int TOUCH_STATE_SCROLLING = 1;
        // 可以切换页面的最小滑动的速度
        private static final int SNAP_VELOCITY = 500;
        // 所处的状态
        private int touchState = TOUCH_STATE_REST;
        private float lastMotionY;

        private OnVerticalPageChangeListener verticalPageChangeListener;

        public MyGuideViewGroup(Context context) {
            this(context, null);
        }

        public MyGuideViewGroup(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        /**
         * 在构造器中进行一些初始化
         */
        public MyGuideViewGroup(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            scroller = new Scroller(context);
            curScreen = defaultScreen;
        }

        public interface OnVerticalPageChangeListener {
            public void onVerticalPageSelected(int position);
        }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(verticalPageChangeListener!=null){
            verticalPageChangeListener.onVerticalPageSelected(0);
        }
    }

    /**
         * 提供一个页面改变的监听器
         */
        public void setOnVerticalPageChangeListener(OnVerticalPageChangeListener onVerticalPageChangeListener) {
            this.verticalPageChangeListener = onVerticalPageChangeListener;
        }

        /**
         * 为ViewPager设置页面视图，在外部调用，让其默认显示第一个View
         * @param viewList 页面视图集合
         */
        public void setViewList(List<View> viewList) {
            if (getChildCount() > 0) {
                this.removeAllViews() ;
            }
            curScreen = defaultScreen;
            for (int i = 0; i < viewList.size(); i++) {
                this.addView(viewList.get(i));
            }
            scrollTo(0, 0);
        }


    /**
     * 重写此方法用来计算高度和宽度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 其中包含setMeasuredDimension方法，它是一个很关键的函数，它对View的成员变量mMeasuredWidth和mMeasuredHeight变量赋值
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 得到多少页(子View)并设置他们的宽和高
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
         * 重写此方法为子View进行布局，此布局默认将第一个子View显示在界面上，其他三个依次在该子View下方
         */
        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            int childheiht = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View childView = getChildAt(i);
                if (childView.getVisibility() != View.GONE) {
                    int childWidth = childView.getMeasuredWidth();
                    childView.layout(0, childheiht, childWidth,
                            childView.getMeasuredHeight() + childheiht);
                    childheiht += childView.getMeasuredHeight();
                }
            }
        }


        /**
         * 根据目前的位置滚动到下一个视图位置，注意此方法被调用的位置，因为里面使用了getHeight();
         */
        public void snapToDestination() {
            // 这个高度是View可见部分的高度
            int screenHeight = getHeight();
            // 根据View的高度以及滑动的值来判断是哪个View
            int destScreen = (getScrollY() + screenHeight/2) / screenHeight;
            snapToScreen(destScreen);
        }

        public void snapToScreen(int whichScreen) {
            whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
            if (getScrollY() != (whichScreen * getHeight())) {

                final int delta = whichScreen * getHeight() - getScrollY();
                //startX(0), startY(getScrollY)为开始滚动的位置，dx(0),dy(delta)为滚动的偏移量，Math.abs(delta)为完成滚动的时间
                scroller.startScroll(0, getScrollY(), 0, delta,
                        Math.abs(delta));
                curScreen = whichScreen;
                invalidate(); // 重新布局
                if (verticalPageChangeListener != null){
                    verticalPageChangeListener.onVerticalPageSelected(whichScreen);
                }
            }
        }

    /**
     * 该方法用于外部调用,设置定位到哪一页
     * @param whichScreen
     */
        public void setToScreen(int whichScreen) {
            whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
            scroller.startScroll(0,(whichScreen-1)*getHeight(),0,getHeight(),1500);
            curScreen = whichScreen;
            invalidate();
        }

        /**
         * 获取当前页面
         * @return 当前页面值
         */
        public int getCurScreen() {
            return curScreen;
        }

        /**
         * 获取当前视图
         * @return 当前视图
         */
        public View getCurrentView() {
            return getChildAt(getCurScreen());
        }

        /**
         * 根据位置获取指定页面的视图
         * @param position 页面位置
         * @return 指定页面的视图
         */
        public View getView(int position) {
            return getChildAt(position);
        }

        // 在父容器重绘自己的孩子时，它会调用孩子的computScroll方法
        @Override
        public void computeScroll() {
            // startScroll()函数只是对它的一些成员变量做一些设置，这个设置的唯一效果就是导致scroller.computeScrollOffset()返回true
            if (scroller.computeScrollOffset()) {
                scrollTo(scroller.getCurrX(), scroller.getCurrY());
                postInvalidate();
            }
        }

        @SuppressLint("ClickableViewAccessibility")
        public boolean onTouchEvent(MotionEvent event) {
            if (velocityTracker == null) {
                // 使用obtain方法得到VelocityTracker的一个对象
                velocityTracker = VelocityTracker.obtain();
            }
            // 将当前的触摸事件传递给VelocityTracker对象
            velocityTracker.addMovement(event);
            // 得到触摸事件的类型
            final int action = event.getAction();
            final float y = event.getY();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    if (!scroller.isFinished()) {
                        scroller.abortAnimation();
                    }
                    lastMotionY = y;
                    System.out.println("onTouchEvent--->down");
                    break;

                case MotionEvent.ACTION_MOVE:
                    int deltay = (int) (lastMotionY - y);
                    lastMotionY = y;
                    if ((curScreen == getChildCount() - 1 && deltay > 0)
                            || (curScreen == 0 && deltay < 0)) {
                    }else{
                        scrollBy(0, deltay);
                    }
                    System.out.println("onTouchEvent--->move");
                    break;

                case MotionEvent.ACTION_UP:
                    final VelocityTracker velocityTracker = this.velocityTracker;
                    // 计算当前的速度
                    velocityTracker.computeCurrentVelocity(1000);
                    // 获得Y轴方向当前的速度
                    int velocityY = (int) velocityTracker.getYVelocity();

                    if (velocityY > SNAP_VELOCITY && curScreen > 0) {
                        snapToScreen(curScreen - 1);
                    } else if (velocityY < -SNAP_VELOCITY && curScreen < getChildCount() - 1) {
                        snapToScreen(curScreen + 1);
                    } else {
                        snapToDestination();
                    }

                    if (this.velocityTracker != null) {
                        this.velocityTracker.recycle();
                        this.velocityTracker = null;
                    }
                    touchState = TOUCH_STATE_REST;
                    break;
                case MotionEvent.ACTION_CANCEL:
                    touchState = TOUCH_STATE_REST;
                    break;
            }
            return true;
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            final int action = ev.getAction();

            switch (action) {
                case MotionEvent.ACTION_MOVE:
                    break;

                case MotionEvent.ACTION_DOWN:
                    touchState = scroller.isFinished() ? TOUCH_STATE_REST
                            : TOUCH_STATE_SCROLLING;
                    break;

                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchState = TOUCH_STATE_REST;
                    break;
            }
            return touchState != TOUCH_STATE_REST;
        }


}
