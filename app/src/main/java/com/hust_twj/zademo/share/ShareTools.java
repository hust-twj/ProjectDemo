package com.hust_twj.zademo.share;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


import com.hust_twj.zademo.R;
import com.mob.MobSDK;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import cn.sharesdk.wechat.utils.WechatClientNotExistException;


/**
 * Created by Wenjing.Tang
 * on 2019/1/31
 */
public class ShareTools {
    private Activity c;

    public ShareTools(Activity c) {
        MobSDK.init(c);
        this.c = c;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("Id", "1");
        hashMap.put("SortId", "1");
        hashMap.put("AppId", "wx24e3d54708f01456");
        hashMap.put("AppSecret", "9e2d485b17f7cd8825bb9b5286c590e8");
        hashMap.put("RedirectUrl", "http://www.sharesdk.cn");
        hashMap.put("ShareByAppClient", "true");
        hashMap.put("Enable", "true");
        ShareSDK.setPlatformDevInfo(Wechat.NAME, hashMap);


        HashMap<String, Object> qqHash = new HashMap<String, Object>();
        qqHash.put("Id", "2");
        qqHash.put("SortId", "2");
        qqHash.put("AppId", "1107003400");
        qqHash.put("AppSecret", "hEFs5rBxTlAUhjsI");
        qqHash.put("RedirectUrl", "http://www.sharesdk.cn");
        qqHash.put("ShareByAppClient", "true");
        qqHash.put("Enable", "true");
        ShareSDK.setPlatformDevInfo(QQ.NAME, qqHash);
    }

    public void showShare(final String shareTitle, final String shareText, final String shareImagePath, final String shareUrl, final String copyContent) {
        final OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(shareTitle);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(shareUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(shareText);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(shareImagePath);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shareUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(c.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(shareUrl);

//        // 去除注释，演示在九宫格设置自定义的图标
//        Bitmap logo = BitmapFactory.decodeResource(c.getResources(), R.drawable.copylink);
//        String label = c.getResources().getString(R.string.copy_link);
//        View.OnClickListener listener = new View.OnClickListener() {
//            public void onClick(View v) {
//                ClipboardManager clip = (ClipboardManager)c.getSystemService(Context.CLIPBOARD_SERVICE);
////                clip.getText(); // 粘贴
////                clip.setText(shareUrl); // 复制
//                clip.setText(copyContent); // 复制
//                Toast.makeText(c, c.getString(R.string.already_copy), Toast.LENGTH_SHORT).show();
//            }
//        };
//        oks.setCustomerLogo(logo, label, listener);

// 启动分享GUI
        oks.show(c);
    }

    public void showQQShare(final String shareTitle, final String shareText, final String shareImagePath, final String shareUrl) {
        QQ.ShareParams sp = new QQ.ShareParams();
        sp.setTitle(shareTitle);
        sp.setTitleUrl(shareUrl); // 标题的超链接
        sp.setText(shareText);
        sp.setImageUrl(shareImagePath);
        sp.setSite(c.getString(R.string.app_name));
        sp.setSiteUrl(shareUrl);

        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                showSuccessToast();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {
                showCancelToast();
            }
        }); // 设置分享事件回调
// 执行图文分享
        qq.share(sp);
    }

    private void showSuccessToast() {
        Toast.makeText(c, "分享成功", Toast.LENGTH_SHORT).show();
    }

    private void showCancelToast() {
        Toast.makeText(c, "分享取消", Toast.LENGTH_SHORT).show();
    }

    public void showWechatShare(final String shareTitle, final String shareText, final String shareImagePath, final String shareUrl) {
        Wechat.ShareParams sp = new Wechat.ShareParams();
        sp.setTitle(shareTitle);
        sp.setTitleUrl(shareUrl); // 标题的超链接
        sp.setText(shareText);
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setImageUrl(shareImagePath);
        sp.setUrl(shareUrl);

//        sp.setSite(c.getString(R.string.app_name));
//        sp.setSiteUrl(shareUrl);
//        sp.setShareType(Platform.SHARE_WEBPAGE);

        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                showSuccessToast();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                if(throwable instanceof WechatClientNotExistException ){
                    c.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(c, "请先下载安装微信客户端！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            @Override
            public void onCancel(Platform platform, int i) {
                showCancelToast();
            }
        }); // 设置分享事件回调
        // 执行图文分享
        wechat.share(sp);
    }

    public void showWechatMomentsShare(final String shareTitle, final String shareText, final String shareImagePath, final String shareUrl) {
        WechatMoments.ShareParams sp = new WechatMoments.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setTitle(shareTitle);
        sp.setText(shareText);
        sp.setImageUrl(shareImagePath);
        sp.setUrl(shareUrl);

        Platform wechatMomment = ShareSDK.getPlatform(WechatMoments.NAME);
        wechatMomment.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                showSuccessToast();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                if(throwable instanceof WechatClientNotExistException){
                    c.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(c, "请先下载安装微信客户端！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancel(Platform platform, int i) {
                showCancelToast();
            }
        }); // 设置分享事件回调
        // 执行图文分享
        wechatMomment.share(sp);
    }

    public void showQQZoneShare(final String shareTitle, final String shareText, final String shareImagePath, final String shareUrl) {
        QZone.ShareParams sp = new QZone.ShareParams();
        sp.setTitle(shareTitle);
        sp.setTitleUrl(shareUrl); // 标题的超链接
        sp.setText(shareText);
        sp.setImageUrl(shareImagePath);
        sp.setSite(c.getString(R.string.app_name));
        sp.setSiteUrl(shareUrl);

        Platform qzone = ShareSDK.getPlatform(QZone.NAME);
        qzone.setPlatformActionListener(new OnekeyShareThemeImpl() {
            @Override
            protected void showPlatformPage(Context context) {

            }

            @Override
            protected void showEditPage(Context context, Platform platform, Platform.ShareParams sp) {

            }
        }); // 设置分享事件回调
// 执行图文分享
        qzone.share(sp);
    }

    public void qqLogin(PlatformActionListener l) {
        //QQ
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
//				authorize(qzone);
        sqLogin(qq, 2, l);
    }

    private void sqLogin(Platform platform, final int type, PlatformActionListener l) {
        if (platform == null) {
            return;
        }

        if (platform.isAuthValid()) {
            platform.removeAccount(true);
        }

        platform.setPlatformActionListener(l);
        platform.SSOSetting(false);
        platform.authorize();
    }

    public void wechatLogin(PlatformActionListener l) {
        //微信
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
//				authorize(qzone);
        sqLogin(wechat, 2, l);
    }

}
