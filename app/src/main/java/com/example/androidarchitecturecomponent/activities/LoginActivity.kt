package com.example.androidarchitecturecomponent.activities

import android.content.Intent
import android.util.Log
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class LoginActivity : BaseActivity(com.example.androidarchitecturecomponent.R.layout.activity_login) {
    //const
    val TAG:String = "LoginActivity"
    val RC_SIGN_IN:Int = 1
    //vars
    var googleSignInClient: GoogleSignInClient? = null

    override fun initializeViews() {
        // Set the dimensions of the sign-in button.
        val signInButton: SignInButton = findViewById(com.example.androidarchitecturecomponent.R.id.google_sign_in)
        signInButton.setSize(SignInButton.SIZE_STANDARD)
        val signOutButton: Button = findViewById(com.example.androidarchitecturecomponent.R.id.btn_sign_out)
        signOutButton.setOnClickListener{signOut()}
        signInButton.setOnClickListener{ signIn()}

        googleSetUp()
    }

    private fun googleSetUp(): Unit {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso)

    }
    private fun signOut(){
        googleSignInClient!!.signOut()
            .addOnCompleteListener(this) {
                ShowToast("you are signOut...", this@LoginActivity)
            }
    }

    private fun signIn():Unit{
        val signInIntent: Intent = googleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode === RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>){
        try{
            val account:GoogleSignInAccount = task.getResult(ApiException::class.java)!!
               val mainActivityIntent = Intent(this@LoginActivity, MainActivity::class.java)
                mainActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                mainActivityIntent.putExtra("user_email", account.email)
                startActivity(mainActivityIntent)

        }catch (e: ApiException){
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }


    fun isLogin(): Boolean{
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if(account != null){
            return true
        }
        return false
    }

    override fun onStart() {
        super.onStart()
        if(isLogin()) {
            ShowToast("You are already logged in",this@LoginActivity)
            //carry outmainActivity
        }
    }

    override fun onResume() {
        super.onResume()
        if(isLogin()) {
            ShowToast("You are already logged in",this@LoginActivity)
            //carry outmainActivity
        }
    }
}