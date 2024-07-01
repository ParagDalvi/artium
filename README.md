Notification can be sent from FCM dashboard and FCM admin API
1. From the dashboard, the notification is of type "message"
   a. App in the background - service does not get a callback
   b. App in foreground - service gets a callback
   (This is by design)
   
2. From API, we can send notifications of type "message" and "data"
   a. App in the background - service gets callback
   b. App in foreground - service gets a callback
   (This is by design)

As we want to show call notifications even when the app is not running, we should be calling the notification API.
For now as I dont have a backend setup for this the below SS are when app is running, same code works when app is not running if a notification is sent from backend service and not from dashboard.


https://github.com/ParagDalvi/artium/assets/29736284/23a5aeac-f79a-4c21-a959-da823407874a


The above video is when the app is in the foreground, and cannot show background video as the backend setup with FCM admin is not available but works the same way.
