package com.krishna.petmatch;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.LoadAdError;

public class MainActivity extends Activity {
    private WebView webView;
    private RewardedAd rewardedAd;

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, status -> {});

        AdView ad = findViewById(R.id.adView);
        ad.loadAd(new AdRequest.Builder().build());

        webView = findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://krishnasptrs-rgb.github.io/pet-adventure-match/");

        loadRewardedAd();

        Button watch = findViewById(R.id.btnWatchAd);
        watch.setOnClickListener(v -> {
            if (rewardedAd != null) {
                RewardedAd adToShow = rewardedAd;
                rewardedAd = null;
                adToShow.show(this, rewardItem -> {
                    // Reward granted after the user earns the reward.
                    // You can connect this to your game's coins/lives later.
                    Toast.makeText(this, "Reward earned! 🎉", Toast.LENGTH_SHORT).show();
                    loadRewardedAd();
                });
            } else {
                Toast.makeText(this, "Ad is loading. Try again in a moment.", Toast.LENGTH_SHORT).show();
                loadRewardedAd();
            }
        });
    }

    private void loadRewardedAd() {
        AdRequest request = new AdRequest.Builder().build();
        RewardedAd.load(this, getString(R.string.rewarded_ad_unit_id), request,
            new RewardedAdLoadCallback() {
                @Override public void onAdLoaded(RewardedAd ad) {
                    rewardedAd = ad;
                }
                @Override public void onAdFailedToLoad(LoadAdError error) {
                    rewardedAd = null;
                }
            });
    }

    @Override public void onBackPressed() {
        if (webView.canGoBack()) webView.goBack(); else super.onBackPressed();
    }
}
