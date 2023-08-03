package tjsaver.tifor

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle incoming FCM messages here
        // You can process the notification payload or data payload
    }

    override fun onNewToken(token: String) {
        // Handle token refresh here
        // You can send the new token to your server or perform any other actions
    }
}
