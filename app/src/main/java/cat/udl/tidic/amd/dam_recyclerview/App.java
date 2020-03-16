package cat.udl.tidic.amd.dam_recyclerview;

import android.app.Application;

import cat.udl.tidic.amd.dam_recyclerview.preferences.PreferencesProvider;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesProvider.init(this);
        PreferencesProvider.providePreferences().edit().putString("current_user","2").apply();
    }
}
