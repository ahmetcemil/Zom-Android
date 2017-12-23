package org.awesomeapp.messenger.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.awesomeapp.messenger.ImApp;
import org.awesomeapp.messenger.crypto.otr.OtrAndroidKeyManagerImpl;
import org.awesomeapp.messenger.ui.legacy.SignInHelper;
import org.awesomeapp.messenger.ui.onboarding.OnboardingAccount;
import org.awesomeapp.messenger.ui.onboarding.OnboardingActivity;
import org.awesomeapp.messenger.ui.onboarding.OnboardingManager;

import java.security.KeyPair;

import im.zom.messenger.R;

/**
 * on 22.12.2017.
 */

public class MusiadSignInActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musiad_sign_in);

    }

    public void onClickSignIn(View view){
        EditText eUserName = findViewById(R.id.editTextUserName);
        EditText ePassword = findViewById(R.id.editTextPassword);

        String userName = eUserName.getText().toString();
        String password = ePassword.getText().toString();

       /* if(userName.trim().length()>0 && password.trim().length()>0){
            if (mCurrentFindServerTask != null)
                mCurrentFindServerTask.cancel(true);

            mCurrentFindServerTask = new FindServerTask();
            mCurrentFindServerTask.execute(userName,password);
        }
   */ }

/*    private class FindServerTask extends AsyncTask<String, Void, OnboardingAccount> {
        @Override
        protected OnboardingAccount doInBackground(String... setupValues) {
            try {
                String server = null;
                String domain = null;
                String password = null;

                if (setupValues.length > 2)
                    domain = setupValues[2]; //user can specify the domain they want to be on for a new account

                if (setupValues.length > 3)
                    password = setupValues[3];

                if (setupValues.length > 4)
                    server = setupValues[4];

                if (domain == null) {
                    Pair<String,String> serverInfo = OnboardingManager.getServerInfo(OnboardingActivity.this,0);
                    domain = serverInfo.first;
                    server = serverInfo.second;
                }

                OtrAndroidKeyManagerImpl keyMan = OtrAndroidKeyManagerImpl.getInstance(OnboardingActivity.this);
                KeyPair keyPair = keyMan.generateLocalKeyPair();
                mFingerprint = keyMan.getFingerprint(keyPair.getPublic());

                String nickname = setupValues[0];
                String username = setupValues[1];

                OnboardingAccount result = null;

                while (result == null) {

                    result = OnboardingManager.registerAccount(OnboardingActivity.this, nickname, username, password, domain, server, 5222);

                    if (result == null) { //wait one second
                        try {Thread.sleep(1000);} catch (Exception e){}

                        //append key to username
                        username = setupValues[1] + '.' + mFingerprint.substring(mFingerprint.length()-8,mFingerprint.length()).toLowerCase();
                    }
                }

                String jid = result.username + '@' + result.domain;
                keyMan.storeKeyPair(jid,keyPair);

                return result;
            }
            catch (Exception e)
            {
                Log.e(ImApp.LOG_TAG, "auto onboarding fail", e);
                return null;
            }
        }

        @Override
        protected void onCancelled(OnboardingAccount onboardingAccount) {
            super.onCancelled(onboardingAccount);

            showSetupForm ();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            showSetupForm ();
        }

        @Override
        protected void onPostExecute(OnboardingAccount account) {

            View viewCreate = findViewById(R.id.flipViewCreateNew);
            viewCreate.findViewById(R.id.progressImage).setVisibility(View.GONE);

            if (account != null) {
                mUsername = account.username + '@' + account.domain;
                mNewAccount = account;


                viewCreate.findViewById(R.id.viewProgress).setVisibility(View.GONE);
                viewCreate.findViewById(R.id.viewSuccess).setVisibility(View.VISIBLE);

                ImApp mApp = (ImApp)getApplication();
                mApp.setDefaultAccount(account.providerId,account.accountId);

                SignInHelper signInHelper = new SignInHelper(OnboardingActivity.this, mHandler);
                signInHelper.activateAccount(account.providerId, account.accountId);
                signInHelper.signIn(account.password, account.providerId, account.accountId, true);

                mItemSkip.setVisible(true);
                mItemSkip.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        showInviteScreen();
                        return false;
                    }
                });
            }
            else
            {
                viewCreate.findViewById(R.id.viewProgress).setVisibility(View.GONE);
                viewCreate.findViewById(R.id.viewCreate).setVisibility(View.VISIBLE);
                viewCreate.findViewById(R.id.btnAdvanced).setVisibility(View.VISIBLE);

                StringBuffer sb = new StringBuffer();
                sb.append(getString(R.string.account_setup_error_server));
                TextView status = (TextView)viewCreate.findViewById(R.id.statusError);
                status.setText(sb.toString());


                //need to try again somehow
            }
        }
    }
*/
}
