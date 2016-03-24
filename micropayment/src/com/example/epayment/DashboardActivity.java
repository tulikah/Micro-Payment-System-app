
package com.example.epayment;

import java.security.SecureRandom;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.library.UserFunctions;

public class DashboardActivity extends Activity {
	UserFunctions userFunctions;
	Button btnLogout,btnKeyGen;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /**
         * Dashboard Screen for the application
         * */        
        // Check login status in database
        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
        	setContentView(R.layout.dashboard);
        	btnLogout = (Button) findViewById(R.id.btnLogout);
        	btnKeyGen = (Button) findViewById(R.id.btnKeyGen);
        	
        	btnLogout.setOnClickListener(new View.OnClickListener() {
    			
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				userFunctions.logoutUser(getApplicationContext());
    				Intent login = new Intent(getApplicationContext(), LoginActivity.class);
    	        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	        	startActivity(login);
    	        	// Closing dashboard screen
    	        	finish();
    			}
    		});
        	btnKeyGen.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
				String key=csRandomAlphaNumericString(32);
		            TextView txt=(TextView)findViewById(R.id.keyText);
		            //oToast.makeText(getApplicationContext(), key, Toast.LENGTH_LONG).show();
		            txt.setText("YOUR KEY IS :"+key);
					
				}
			});
        }else{
        	// user is not logged in show login screen
        	Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(login);
        	// Closing dashboard screen
        	finish();
        }
        
        
        
        
    }
    public static String csRandomAlphaNumericString(int numChars) {
    	char[] VALID_CHARACTERS="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();
    	    SecureRandom srand = new SecureRandom();
    	    Random rand = new Random();
    	    char[] buff = new char[numChars];

    	    for (int i = 0; i < numChars; ++i) {
    	      // reseed rand once you've used up all available entropy bits
    	      if ((i % 10) == 0) {
    	          rand.setSeed(srand.nextLong()); // 64 bits of random!
    	      }
    	      buff[i] = VALID_CHARACTERS[rand.nextInt(VALID_CHARACTERS.length)];
    	    }
    	    return new String(buff);
    	}
}
