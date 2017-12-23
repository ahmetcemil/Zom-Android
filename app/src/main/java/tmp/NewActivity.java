package tmp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;

import org.awesomeapp.messenger.ImApp;
import org.awesomeapp.messenger.plugin.xmpp.XmppConnection;
import org.awesomeapp.messenger.provider.Imps;
import org.awesomeapp.messenger.ui.legacy.ProviderDef;
import org.awesomeapp.messenger.ui.legacy.ThemeableActivity;
import org.awesomeapp.messenger.ui.onboarding.OnboardingManager;
import org.json.JSONException;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;

import im.zom.messenger.R;

import static org.awesomeapp.messenger.ImApp.PROVIDER_PROJECTION;

/**
 * on 20.12.2017.
 */

public class NewActivity extends ThemeableActivity {
    EditText eUserName,ePassword,eDomain,eServer,ePort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._new_layout);


        eUserName = (EditText) findViewById(R.id.editTextUserName);
        ePassword = (EditText) findViewById(R.id.editTextPassword);
        eDomain   = (EditText) findViewById(R.id.editTextDomain);
        eServer = (EditText) findViewById(R.id.editTextServer);
        ePort = (EditText) findViewById(R.id.editTextPort);
    }

    public void onClickTestConnect (View view){

        registerAccount();


     /*  //ImApp.insertOrUpdateAccount()
        try {
            ImApp.createConnection(1,1);
        } catch (RemoteException e) {
            e.printStackTrace();
        } */

    }


    private void getProviders(){
        HashMap mProviders = new HashMap<Long, ProviderDef>();

        ContentResolver cr = getContentResolver();

        String selectionArgs[] = new String[1];
        selectionArgs[0] = ImApp.IMPS_CATEGORY;

        Cursor c = cr.query(Imps.Provider.CONTENT_URI, PROVIDER_PROJECTION, Imps.Provider.CATEGORY
                        + "=?", selectionArgs,
                null);
        if (c == null) {
            return;
        }

        try {
            while (c.moveToNext()) {
                long id = c.getLong(0);
                String providerName = c.getString(1);
                String fullName = c.getString(2);
                String signUpUrl = c.getString(3);

                if (mProviders == null) // mProviders has been reset
                    break;
                mProviders.put(id, new ProviderDef(id, providerName, fullName, signUpUrl));
            }
        } finally {
            c.close();
        }
    }

    private void registerAccount(){
        final String userName = eUserName.getText().toString();
        final String password = ePassword.getText().toString();
        final String domain = eDomain.getText().toString();
        final String server = eServer.getText().toString().length()>0 ? eServer.getText().toString() : null;
        final int port = Integer.valueOf(ePort.getText().toString());

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    OnboardingManager.registerAccount(getApplicationContext(),userName,userName,password,domain,server,port);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

}
