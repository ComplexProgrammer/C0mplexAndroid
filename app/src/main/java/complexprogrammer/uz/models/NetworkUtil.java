package complexprogrammer.uz.models;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    public static String getConnectivityStatusString(Context context) {
        String status = "Internet mavjud emas";
//        String status = "No internet is available";
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = "Wi-Fi yoqilgan";
//                status = "Wifi enabled";
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = "Mobil internet yoqilgan";
//                status = "Mobile data enabled";
                return status;
            }
        } else {
            status = "Internet mavjud emas";
//            status = "No internet is available";
            return status;
        }
        return status;
    }
}
