package com.hust_twj.zademo.toast.toast_type;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;

import java.util.LinkedList;

/**
 * Created by Wenjing.Tang
 * on 2019/1/25
 */
public class SystemTN extends Handler {

    final static int REMOVE = 2;
    private final LinkedList<SystemToast> toastQueue;

    private SystemTN() {
        toastQueue = new LinkedList<>();
    }

    public static SystemTN instance() {
        return SingletonHolder.mTn;
    }

    private static class SingletonHolder {
        private static final SystemTN mTn = new SystemTN();
    }

    /**
     * 新增Toast任务加入队列
     */
    protected void add(SystemToast toast) {
        if (toast == null) {
            return;
        }
        SystemToast mToast = toast.clone();
        if (mToast == null) {
            return;
        }
        notifyNewToastComeIn(mToast);
    }

    /**
     * 当前是否有toast在展示
     *
     * @return
     */
    private boolean isShowing() {
        return toastQueue.size() > 0;
    }

    private void notifyNewToastComeIn(@NonNull SystemToast mToast) {
        boolean isShowing = isShowing();
        //加入队列
        toastQueue.add(mToast);

        //如果有toast正在展示
        if (isShowing) {
            if (toastQueue.size() == 2) {
                //获取当前正在展示的toast
                SystemToast showing = toastQueue.peek();
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

    private void remove(SystemToast toast) {
        toastQueue.remove(toast);
        toast.cancelInternal();
        // 展示下一个Toast
        showNextToast();
    }

    protected void cancelAll() {
        removeMessages(REMOVE);
        if (!toastQueue.isEmpty()) {
            toastQueue.peek().cancelInternal();
        }
        toastQueue.clear();
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
        SystemToast toast = toastQueue.peek();
        if (null == toast) {
            toastQueue.poll();
            showNextToast();
        } else {
            if (toastQueue.size() > 1) {
                SystemToast next = toastQueue.get(1);
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

    private void sendRemoveMsgDelay(SystemToast toast) {
        removeMessages(REMOVE);
        Message message = obtainMessage(REMOVE);
        message.obj = toast;
        sendMessageDelayed(message, toast.getDuration());
    }

    private void sendRemoveMsg(SystemToast toast) {
        removeMessages(REMOVE);
        Message message = obtainMessage(REMOVE);
        message.obj = toast;
        sendMessage(message);
    }

    private void displayToast(@NonNull SystemToast toast) {
        toast.showInternal();
        //展示到时间后移除
        sendRemoveMsgDelay(toast);
    }

    @Override
    public void handleMessage(Message message) {
        if (message == null) return;
        switch (message.what) {
            case REMOVE:
                remove((SystemToast) message.obj);
                break;
            default:
                break;
        }
    }

}
