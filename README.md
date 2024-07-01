Notification can be send from FCM dashboard and FCM admin API
1. From dashboard, notification is of type "message"
   a. App in background - service does not get a callback
   b. App in forground - service gets callback
   (This is by design)
   
2. From API, we can send notifications of type "message" and "data"
   a. App in background - service gets callback
   b. App in forground - service gets callback
   (This is by design)

As we want to show call notification even when app is not running, we should be calling the notification API.
For now as I dont have backend setup for this the below SS are when app is running, same code works when app is not running if notification is sent from backed service and not from dashboard
