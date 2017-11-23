# GetBeaconId
AltBeaconを使用してビーコンの情報を取得すアプリです。  
kotlinとビーコン周りの勉強です。  
RangeNotifier内(34行~39行)のログがまだ出せていません。(ビーコン検知はできているみたいだけど)  
誰か詳しい方、教えてください。  
twitterやGmailでもいいのでお願いします。  

3/8 追記  
RangeNotifier内のログを出すことができました。  
原因としてはMonitorだけを開始して、RangeNotifierを開始させていなかったので  
それは出るわけがありませんでした(´・ω・｀)  
