package com.example.mananwason.parkr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mananwason on 7/20/17.
 */

public class FragmentEmail extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText emailText;
    private EditText passwordText;
    private Button emailConfirm;
    private static final String TAG = "Fragment Email";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_email_login, container, false);
        emailText = (EditText) view.findViewById(R.id.email_text);
        passwordText = (EditText) view.findViewById(R.id.password_text);
        emailConfirm = (Button) view.findViewById(R.id.email_password_confirm);
        emailConfirm.setOnClickListener(this);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        mAuth = FirebaseAuth.getInstance();
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_password_confirm:
                Log.d(TAG, "CLICK");
                addUser();
                break;
        }
    }

    public void addUser() {
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");

                    mAuth.createUserWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString()).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            Log.w(TAG, "signInWithEmail", task.getException());

                            if (!task.isSuccessful()) {
                                Toast.makeText(getActivity(), R.string.auth_failed,
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        };

        mAuth.addAuthStateListener(mAuthListener);


    }
}
