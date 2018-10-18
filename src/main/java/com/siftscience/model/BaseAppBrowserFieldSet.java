package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class BaseAppBrowserFieldSet<T extends BaseAppBrowserFieldSet<T>>
    extends EventsApiRequestFieldSet<T> {
    @Expose @SerializedName("$app") private App app;
    @Expose @SerializedName("$browser") private Browser browser;

    public App getApp() {
        return app;
    }

    public T setApp(App app) {
        this.app = app;
        return (T) this;
    }

    public Browser getBrowser() {
        return browser;
    }

    public T setBrowser(Browser browser) {
        this.browser = browser;
        return (T) this;
    }

}
