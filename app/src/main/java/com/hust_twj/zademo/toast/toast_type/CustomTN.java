package com.hust_twj.zademo.toast.toast_type;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewManager;
import android.view.ViewParent;
import android.view.WindowManager;

import java.util.LinkedList;

/**
 * Created by Wenjing.Tang
 * on 2019/1/25
 */
public class CustomTN extends Handler {
    final static int REMOVE = 2;

    private final LinkedList<CustomToast> toastQueue;

    private CustomTN() {
        toastQueue = new LinkedList<>();
    }

    static CustomTN instance() {
        return SingletonHolder.mTn;
    }

    private static class SingletonHolder {
        private static final CustomTN mTn = new CustomTN();
    }

    /**
     * 新增Toast任务加入队列
     */
    public void add(CustomToast toast) {
        if (toast == null) {
            return;
        }
        final CustomToast mToast = toast.clone();
        if (mToast == null) {
            return;
        }
        notifyNewToastComeIn(mToast);
    }

    /**
     * 当前有toast在展示
     *
     * @return
     */
    private boolean isShowing() {
        return toastQueue.size() > 0;
    }

    private void notifyNewToastComeIn(@NonNull CustomToast mToast) {
        boolean isShowing = isShowing();
        //加入队列
        toastQueue.add(mToast);
        //如果有toast正在展示
        if (isShowing) {
            if (toastQueue.size() == 2) {
                //获取当前正在展示的toast
                CustomToast showing = toastQueue.peek();
                //允许新加入的toast终止当前的展示
                if (mToast.getPriority() >= showing.getPriority()) {
                    //立即终止当前正在展示toast,并开始展示下一个
                    sendRemoveMsg(showing);
                } else {
                    //do nothing ...
                    return;
                }
            } else {
                //do nothing ...
                return;
            }
        } else {
            showNextToast();
        }
    }

    private void remove(CustomToast toast) {
        toastQueue.remove(toast);
        removeInternal(toast);
    }

    void cancelAll() {
        removeMessages(REMOVE);
        if (!toastQueue.isEmpty()) {
            removeInternal(toastQueue.peek());
        }
        toastQueue.clear();
    }

    void cancelActivityToast(Activity activity) {
        if (activity == null) {
            return;
        }
        for (CustomToast t : toastQueue) {
            if (t instanceof ActivityToast && t.getContext() == activity) {
                remove(t);
            }
        }
    }

    private void removeInternal(CustomToast toast) {
        if (toast != null && toast.isShowing()) {
            // FIXME: 2018/11/26 存在问题：队列中多个Toast使用相同ContentView时可能造成混乱
            // FIXME: 不过，不同时展示多个Toast的话，也不会出现此问题.因为next.show()在last.removeView()动作之后
            WindowManager windowManager = toast.getWindwoManager();
            if (windowManager != null) {
                try {
                    windowManager.removeView(toast.getView());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            toast.isShowing = false;
        }
    }

    /**
     * 多个弹窗连续出现时：
     * 1.相同优先级时，会终止上一个，直接展示后一个；
     * 2.不同优先级时，如果后一个的优先级更高则会终止上一个，直接展示后一个。
     */
    private void showNextToast() {
        if (toastQueue.isEmpty()) {
            return;
        }
        CustomToast toast = toastQueue.peek();
        if (null == toast) {
            toastQueue.poll();
            showNextToast();
        } else {
            if (toastQueue.size() > 1) {
                CustomToast next = toastQueue.get(1);
                if (next.getPriority() >= toast.getPriority()) {
                    toastQueue.remove(toast);
                    showNextToast();
                } else {
                    displayToast(toast);
                }
            } else {
                displayToast(toast);
            }
        }
    }

    private void sendRemoveMsgDelay(CustomToast toast) {
        removeMessages(REMOVE);
        Message message = obtainMessage(REMOVE);
        message.obj = toast;
        sendMessageDelayed(message, toast.getDuration());
    }

    private void sendRemoveMsg(CustomToast toast) {
        removeMessages(REMOVE);
        Message message = obtainMessage(REMOVE);
        message.obj = toast;
        sendMessage(message);
    }

    private void displayToast(@NonNull CustomToast toast) {
        WindowManager windowManager = toast.getWindwoManager();
        if (windowManager != null) {
            View toastView = toast.getView();
            if (toastView != null) {
                //从父容器中移除contentView
                ViewParent parent = toastView.getParent();
                if (parent instanceof ViewManager) {
                    ((ViewManager) parent).removeView(toastView);
                }
                //再将contentView添加到WindowManager
                try {
                    windowManager.addView(toastView, toast.getWMParams());
                } catch (Exception e) {
                    e.printStackTrace();
                    if (e instanceof WindowManager.BadTokenException) {
                        //此处代码段不允许再次抛出WindowManager.BadTokenException异常，否则可能造成死循环
                        if (e.getMessage() != null && e.getMessage().contains("token null is not valid")) {
                            //尝试使用ActivityToast
                            if (toast.getContext() instanceof Activity && !(toast instanceof ActivityToast)) {
                                new ActivityToast(toast.getContext())
                                        .setView(toastView)
                                        .setDuration(toast.getDuration())
                                        .setGravity(toast.getGravity(), toast.getXOffset(), toast.getYOffset())
                                        .show();
                            }
                        }
                    }
                }
                toast.isShowing = true;
                //展示到时间后移除
                sendRemoveMsgDelay(toast);
            } else {
                //没有ContentView时直接移除
                toastQueue.remove(toast);
                //移除一个未在展示的Toast任务后，主动唤起下一个Toast任务
                showNextToast();
            }
        }
    }

    @Override
    public void handleMessage(Message message) {
        if (message == null) {
            return;
        }
        switch (message.what) {
            case REMOVE:
                //移除当前
                remove((CustomToast) message.obj);
                // 展示下一个Toast
                showNextToast();
                break;
            default:
                break;
        }
    }
}

