package com.example.mycafe;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class LoginDialogFragment extends DialogFragment {

    private EditText username;
    private EditText password;
    private DialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View DialogView = inflater.inflate(R.layout.dialog_signin,null);

        username = DialogView.findViewById(R.id.username);
        password = DialogView.findViewById(R.id.password);

        builder.setView(DialogView)
                .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {

                        String check_user = username.getText().toString();
                        String check_password = password.getText().toString();

                        if (check_user.equals("user")){
                            if (check_password.equals("user")){
                                Toast.makeText(getActivity(), "welcome: "+check_user, Toast.LENGTH_SHORT).show();
                                listener.applyTexts(check_user);
                            }
                            else{
                                Toast.makeText(getActivity(), "incorrect username or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getActivity(), "incorrect username or password", Toast.LENGTH_SHORT).show();
                        }
                     }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int id) {
                        LoginDialogFragment.this.getDialog().cancel();
                    }
                });


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public interface DialogListener{
        void applyTexts(String username);
}


}