package com.haerul.foodsapp.view.home;

import com.google.firebase.auth.FirebaseUser;

public interface MainView {
	void callSignIn(String email, String password);
	void callSignUp(String email, String password);
	void userProfile(FirebaseUser user);
}
