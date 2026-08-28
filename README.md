# Pet Adventure Match - Free WebView APK project

Loads:
https://krishnasptrs-rgb.github.io/pet-adventure-match/

This project includes a Google Mobile Ads SDK banner using Google's TEST App ID and TEST Banner ID.

Before publishing/using real ads:
1. Create/register your app in AdMob.
2. Replace `admob_app_id` and `banner_ad_unit_id` in `app/src/main/res/values/strings.xml`.
3. Keep test IDs while developing and testing.
4. Build through GitHub Actions: Actions -> Build APK -> Run workflow -> download the artifact.

The project uses Google's current Android Mobile Ads SDK dependency 25.4.0.


## Rewarded "Watch Ad" button
A `Watch Ad & Get Reward` button is included using Google's TEST Rewarded Ad Unit ID.
The Java callback currently displays a "Reward earned" message. Connect that callback
to your game's JavaScript/HTML reward logic if you want the game to add coins/lives.
