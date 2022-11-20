package com.example.mycafe;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mycafe.databinding.ActivityMainBinding;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HTTP;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginDialogFragment.DialogListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    boolean IsLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"C109118205@nkust.edu.tw"}); // recipients
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
                startActivity(emailIntent);

                Snackbar.make(view, "open your e-mail", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        appBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.nav_shop, R.id.nav_about)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(navigationView, navController);
        updateUI();

    }

    private void updateUI(){
        invalidateOptionsMenu();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(IsLoggedIn ? R.id.action_login : R.id.action_logout);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                Toast.makeText(this, "Login...", Toast.LENGTH_SHORT).show();
                signIn();
                break;

            case R.id.action_Search:
                Uri webpage = Uri.parse("https://www.google.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
                break;
            case R.id.action_logout:
                IsLoggedIn = false;
                updateUI();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    public void signIn() {
        DialogFragment newFragment = new LoginDialogFragment();
        newFragment.show(getSupportFragmentManager(), "Signin");
    }

    @Override
    public void applyTexts(String username) {
        IsLoggedIn = true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private int cake = 25 ;
    private int burger = 15 ;
    private int coffee = 10 ;

    public int total_money = 0;

    public void btn_onclick(View view) {
        switch(view.getId()) {
            case R.id.b1:
                addspinner("cake",cake);
                break;
            case R.id.b2:
                addspinner("burger",burger);
                break;
            case R.id.b3:
                addspinner("coffee",coffee);
                break;
            case R.id.b4:
                add_money(total_money);
                break;
            case R.id.b5:
                total_money = 0;
                EditText editmoney = (EditText) findViewById(R.id.balance);
                editmoney.setText(String.valueOf(total_money));
                break;
            }
        }
        public int add_money(int default_money){
        AlertDialog.Builder mAdd = new AlertDialog.Builder(MainActivity.this);
        final EditText editText = new EditText(MainActivity.this);
        mAdd.setView(editText);
        EditText editmoney = (EditText) findViewById(R.id.balance);

        mAdd.setTitle("輸入金額");
        mAdd.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int total_add = Integer.parseInt(editText.getText().toString());
                total_money = default_money + total_add;
                editmoney.setText(String.valueOf(total_money));
                Toast.makeText(MainActivity.this,"成功加值: "+total_add,Toast.LENGTH_SHORT).show();
            }
        });
        mAdd.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mAdd.create().show();
        return total_money;

        }
    public void addspinner(String value,int price) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_spinner,null);
        mBuilder.setTitle("Buy");
        EditText total_money = (EditText) findViewById(R.id.balance);

        Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.cost));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!mSpinner.getSelectedItem().toString().equalsIgnoreCase("選擇購買數量")){
//                    Toast.makeText(MainActivity.this,mSpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                    int total = price * Integer.parseInt(mSpinner.getSelectedItem().toString());
                    int reduce = Integer.parseInt(total_money.getText().toString()) -total ;
                    int bonus = total-Integer.parseInt(total_money.getText().toString());
                    if (reduce < 0){
                        Toast.makeText(MainActivity.this,"餘額不足，請加值" + bonus+ "元" ,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this,"購買 "+value +" "+ mSpinner.getSelectedItem().toString()+"個"+" 金額共: "+total,Toast.LENGTH_SHORT).show();
                        total_money.setText(String.valueOf(reduce));
                    }
                }
            }
        });
        mBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }


}