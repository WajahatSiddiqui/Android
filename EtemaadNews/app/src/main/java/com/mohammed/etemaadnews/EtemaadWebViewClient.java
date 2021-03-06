package com.mohammed.etemaadnews;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by mohammed on 29/3/15.
 */
public class EtemaadWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (!url.contains("etemaad")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            view.getContext().startActivity(intent);
            return true;
        } else {
            view.loadUrl(url);
        }
        return false;
    }
}
