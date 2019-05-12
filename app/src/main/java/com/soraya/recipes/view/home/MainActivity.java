
package com.soraya.recipes.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.soraya.foodsapp.R;

public class MainActivity extends AppCompatActivity implements MainView {
	
	private FirebaseAuth mAuth;
	private EditText email,password;
	private Button signin, signup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mAuth = FirebaseAuth.getInstance();
		ButterKnife.bind(this);
		
		signin = (Button) findViewById(R.id.signin_btn);
		signup = (Button) findViewById(R.id.signup_btn);
		email = (EditText) findViewById(R.id.email);
		password = (EditText) findViewById(R.id.password);
		
		if (mAuth.getCurrentUser() != null) {
			finish();
			startActivity(new Intent(getApplicationContext(), HomeActivity.class));
		}
		
		signin.setOnClickListener((view -> {
			String getemail = email.getText().toString();
			String getpassword = password.getText().toString();
			callSignIn(getemail, getpassword);
		}));
		
		signup.setOnClickListener(view -> {
			String getemail = email.getText().toString();
			String getpassword = password.getText().toString();
			callSignUp(getemail, getpassword);
		});
		
	}
	
	@Override
	public void callSignIn(String email, String password) {
		mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
			Log.d("d", "Sign in is successful");
			
			if(!task.isSuccessful()) {
				Log.d("d", "Sing in With Email Failed:", task.getException());
				Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
			} else {
				Intent i = new Intent(MainActivity.this, HomeActivity.class);
				finish();
				startActivity(i);
			}
		});
	}
	
	@Override
	public void callSignUp(String email, String password) {
		mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if (task.isSuccessful()) {// Sign in success, update UI with the signed-in user's information
	
					Toast.makeText(MainActivity.this, "SignUp Successful.", Toast.LENGTH_SHORT).show();
					Log.d("d", "createUserWithEmail:success");
					FirebaseUser user = mAuth.getCurrentUser();
					userProfile(user); } else {
					// If sign in fails, display a message to the user.
					Toast.makeText(MainActivity.this, "SignUp Failed.", Toast.LENGTH_SHORT).show();
					Log.w("d", "createUserWithEmail:failure", task.getException());
	                        }
	                    }
	                });
	}
	
	@Override
	public void userProfile(FirebaseUser user) {
		if (user != null) {
			UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(email.getText().toString()).build();user.updateProfile(profileUpdates).addOnCompleteListener(task -> { if (task.isSuccessful()) { Log.d("d", "User profile updated"); }
			});
		}
	}
	
}
