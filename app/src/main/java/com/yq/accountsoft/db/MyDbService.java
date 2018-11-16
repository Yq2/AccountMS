package com.yq.accountsoft.db;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import com.google.android.gms.identity.intents.AddressConstants;
import java.util.ArrayList;
import java.util.List;

public class MyDbService extends IntentService {

    public static final String ACTION_FOO = "com.yq.accountsoft.db.action.FOO";
    public static final String ACTION_BAZ = "com.yq.accountsoft.db.action.BAZ";
    public static final String EXTRA_PARAM1 = "com.yq.accountsoft.db.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.yq.accountsoft.db.extra.PARAM2";
    private final Binder binder=new LocalBinder();
    public MyDbService() {
        super("MyDbService");
    }
    public class LocalBinder extends Binder{
        MyDbService getSerVice(){
            return MyDbService.this;
        }
    }

    
    public void onCreate() {
        super.onCreate();
        String target="http://localhost:8080/AccountMS/DbServlet";
       // HttpClient httpClient=new DefaultHttpClient();//需要导入第三方jar包
        //HttpPost httpPost=new HttpPost(target);
       // List<NameValuePair> param=new ArrayList<NameValuePair>();
       // param.add(new BasicNameValuePair("param","post"));
        //param.add(new BasicNameValuePair("nickname",nickname.getText().toString()));
       // param.add(new BasicNameValuePair("content",content.getText().toString()));





    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public MyDbService(String name) {
        super(name);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
        //这里处理客户端传过来的数据

        Bundle bundle=intent.getExtras();
        bundle.getString("dao");
        bundle.getString("");
        bundle.getString("");
        bundle.getString("");
        bundle.getString("");







    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
