package com.hust_twj.zademo.third_part.retrofit;

import androidx.annotation.NonNull;

/**
 * @author hust_twj
 * @date 2020/1/6
 */
public class GitUsers {

    public String login;
    public long id;
    public String node_id;
    public String avatar_url;
    public String gravatar_id;
    public String url;
    public String html_url;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String starred_url;
    public String subscriptions_url;
    public String organizations_url;
    public String repos_url;

    public String events_url;
    public String received_events_url;
    public String type;
    public boolean site_admin;

    @NonNull
    @Override
    public String toString() {
        return "login:" + login + "\n" +
                "id:" + id + "\n" +
                "node_id:" + node_id + "\n" +
                "avatar_url:" + avatar_url + "\n" +
                "gravatar_id:" + gravatar_id + "\n" +
                "url:" + url + "\n" +
                "html_url:" + html_url + "\n" +
                "followers_url:" + followers_url + "\n" +
                "following_url:" + following_url + "\n" +
                "subscriptions_url:" + subscriptions_url + "\n" +
                "repos_url:" + repos_url + "\n" +
                "organizations_url:" + organizations_url + "\n" +
                "received_events_url:" + received_events_url + "\n" +
                "events_url:" + events_url + "\n" +
                "----------------------------- \n";
    }
}
