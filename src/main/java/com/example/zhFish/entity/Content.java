package com.example.zhFish.entity;

/**
 * @author yucan
 * @date 2024/5/24 15:30
 */
public class Content {
    private String title;

    private String url;

    private String content;

    public Content(String title, String url, String content) {
        this.title = title;
        this.url = url;
        this.content = content;
    }

    @Override
    public String toString() {
        return title + "\t" + content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
