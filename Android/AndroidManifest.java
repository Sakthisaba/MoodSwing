<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.imagetovolley">
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.INTERNET"/>


    <application
         android:usesCleartextTraffic="true"
        android:allowBackup="true"
        android:icon="@drawable/spotify"
        android:label="@string/app_name"
        android:roundIcon="@drawable/spotify"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".welcome">

        </activity>
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <meta-data
            android:name="preloaded_fonts"
            android:resource="@array/preloaded_fonts" />
    </application>

</manifest>
