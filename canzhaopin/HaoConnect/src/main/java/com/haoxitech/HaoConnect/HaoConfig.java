package com.haoxitech.HaoConnect;

/**
 * Created by wangtao on 15/12/21.
 */
public class HaoConfig {

    private static String Clientversion = "1.0";

    public static String getClientInfo() {
        return "android";
    }

    public static void setClientVersion(String clientVersion) {
        Clientversion = clientVersion;
    }

    public static String getClientVersion() {
        return Clientversion;
    }

    public static String getSecretHax() {
        return "secret=api10000care12622481632a";
    }

//    public static String getApiHost() {
//        return (HaoConnect.getString("url").equals("normal") || HaoConnect.getString("url").equals("") || HaoConnect.getString("url") == null) ? "api.canzhaopin.com" : "api-canzhaopin.haoxitech.com";
//    }

    public static String getApiHost() {
        return (HaoConnect.getString("url").equals("normal") || HaoConnect.getString("url").equals("") || HaoConnect.getString("url") == null) ? "api-10000care.haoxitech.com" : "api-10000care.haoxitech.com";
    }
}
