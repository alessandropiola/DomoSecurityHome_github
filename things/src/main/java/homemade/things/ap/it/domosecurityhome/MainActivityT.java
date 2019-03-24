package homemade.things.ap.it.domosecurityhome;

import android.app.Activity;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 * <p>
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.cdom/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class MainActivityT extends Activity {

    home1 canvas1;
    private Rect rectTapp1 = new Rect(100, 250, 1660, 289);
    private Rect rectLuceTenda = new Rect(65, 65, 130, 130);
    private Rect rectTapp2 = new Rect(218, 95, 263, 170);
    private Rect rectTapp3 = new Rect(451, 103, 507, 163);
    private Rect rectPiano = new Rect(35, 419, 282, 444);
    private Rect rectClear = new Rect(320, 314, 420, 360);
    TextView txtDispo;
    private int intDispo = 0;

    // GPIO Pin Name
    private Gpio mLedGpio;
    //autooff
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            String pinName = "BCM21";
            mLedGpio = PeripheralManager.getInstance().openGpio(pinName);
            mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);
            mLedGpio.setValue(false);
            Log.i("displ", "Start blinking LED GPIO pin");
            // Post a Runnable that continuously switch the state of the GPIO, blinking the
            // corresponding LED
            mHandler.post(mBlinkRunnable);

        } catch (IOException e) {
            Log.e("displ", "Error on PeripheralIO API", e);
        }

        canvas1 = findViewById(R.id.home);

        canvas1.intMolt = 1f;



        Button btnApri = findViewById(R.id.apri);
        Button btnChiudi = findViewById(R.id.chiudi);
        Button btnApriT1 = findViewById(R.id.apriT1);
        Button btnChiudiT1 = findViewById(R.id.chiudiT1);
        Button btnApriT2 = findViewById(R.id.apriT2);
        Button btnChiudiT2 = findViewById(R.id.chiudiT2);
        Button pedro = findViewById(R.id.pedro);
        Button luce2p = findViewById(R.id.btnLuce2p);

        Button btnStop = findViewById(R.id.stop);
        Button btnFloor = findViewById(R.id.floor);
        txtDispo = findViewById((R.id.setDisp));

        btnFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("XY", "piano!");
                Log.i("XY", "piano!"+canvas1.intPiano);
                txtDispo.setText("---");
                intDispo = 0;
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        if (canvas1.intPiano==2) {
                            canvas1.intPiano=1;
                            canvas1.intMolt = 1f;
                        } else if (canvas1.intPiano==1) {
                            canvas1.intPiano=2;
                            canvas1.intMolt = 0.7f;
                        }

                        canvas1.invalidate();

                    }
                });
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetTaskDone().execute("http://192.168.1.201/cm?cmnd=Power1%20Off","","");
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=Power1%20Off","","http://192.168.1.202/cm?cmnd=Power2%20Off");
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=Power3%20Off","","http://192.168.1.202/cm?cmnd=Power4%20Off");
                new GetTaskDone().execute("http://192.168.1.203/cm?cmnd=Power1%20Off","","http://192.168.1.203/cm?cmnd=Power2%20Off");

                new GetTaskDone().execute("http://192.168.1.204/cm?cmnd=Power1%20Off","","http://192.168.1.204/cm?cmnd=Power2%20Off");
                new GetTaskDone().execute("http://192.168.1.205/cm?cmnd=Power1%20Off","","http://192.168.1.205/cm?cmnd=Power2%20Off");
                new GetTaskDone().execute("http://192.168.1.206/cm?cmnd=Power1%20Off","","http://192.168.1.206/cm?cmnd=Power2%20Off");
                new GetTaskDone().execute("http://192.168.1.207/cm?cmnd=Power1%20Off","","http://192.168.1.207/cm?cmnd=Power2%20Off");
            }
        });

        btnApri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intDispo == 1 && canvas1.intPiano == 1){
                    new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime3%20128","","http://192.168.1.202/cm?cmnd=Power2%20On");
                }
                if (intDispo == 2 && canvas1.intPiano == 1){
                    new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime4%20128","","http://192.168.1.202/cm?cmnd=Power4%20On");
                }
                if (intDispo == 3 && canvas1.intPiano == 1){
                    new GetTaskDone().execute("http://192.168.1.203/cm?cmnd=PulseTime1%20100","","http://192.168.1.203/cm?cmnd=Power1%20On");
                }
                if (intDispo == 4 && canvas1.intPiano == 1){
                    new GetTaskDone().execute("http://192.168.1.201/cm?cmnd=Power1%20On","","");
                }
                if (intDispo == 0){
                    Toast.makeText(getApplicationContext(), "Seleziona prima il\n     dispositivo",
                            Toast.LENGTH_LONG).show();

                }


            }


        });

        btnChiudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 if (intDispo == 1){
                    new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime1%20128","","http://192.168.1.202/cm?cmnd=Power1%20On");
                }
                if (intDispo == 2){
                    new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime3%20128","","http://192.168.1.202/cm?cmnd=Power3%20On");
                }
                if (intDispo == 3){
                    new GetTaskDone().execute("http://192.168.1.203/cm?cmnd=PulseTime2%20100","","http://192.168.1.203/cm?cmnd=Power2%20On");
                }
                if (intDispo == 4){
                    new GetTaskDone().execute("http://192.168.1.201/cm?cmnd=Power%20Off","","");
                }
                if (intDispo == 0){
                    Toast.makeText(getApplicationContext(), "Fai click sul dispositivo",
                            Toast.LENGTH_LONG).show();

                }
            }
        });
        btnApriT1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime2%20128","","http://192.168.1.202/cm?cmnd=Power2%20On");
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime4%20128","","http://192.168.1.202/cm?cmnd=Power4%20On");
                new GetTaskDone().execute("http://192.168.1.203/cm?cmnd=PulseTime1%20100","","http://192.168.1.203/cm?cmnd=Power1%20On");
                return false;
            }
        });
        btnChiudiT1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime2%20128","","http://192.168.1.202/cm?cmnd=Power1%20On");
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime2%20128","","http://192.168.1.202/cm?cmnd=Power3%20On");
                new GetTaskDone().execute("http://192.168.1.203/cm?cmnd=PulseTime2%20128","","http://192.168.1.203/cm?cmnd=Power2%20On");
                return false;
            }
        });
        btnApriT2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                new GetTaskDone().execute("http://192.168.1.204/cm?cmnd=PulseTime1%20120","","http://192.168.1.204/cm?cmnd=Power1%20On");
                new GetTaskDone().execute("http://192.168.1.205/cm?cmnd=PulseTime1%20160","","http://192.168.1.205/cm?cmnd=Power1%20On");
                new GetTaskDone().execute("http://192.168.1.206/cm?cmnd=PulseTime1%20160","","http://192.168.1.206/cm?cmnd=Power1%20On");
                new GetTaskDone().execute("http://192.168.1.207/cm?cmnd=PulseTime1%20160","","http://192.168.1.207/cm?cmnd=Power1%20On");
                return false;
            }
        });


        btnChiudiT2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                new GetTaskDone().execute("http://192.168.1.204/cm?cmnd=PulseTime2%20120","","http://192.168.1.204/cm?cmnd=Power2%20On");
                new GetTaskDone().execute("http://192.168.1.205/cm?cmnd=PulseTime2%20160","","http://192.168.1.205/cm?cmnd=Power2%20On");
                new GetTaskDone().execute("http://192.168.1.206/cm?cmnd=PulseTime2%20160","","http://192.168.1.206/cm?cmnd=Power2%20On");
                new GetTaskDone().execute("http://192.168.1.207/cm?cmnd=PulseTime2%20160","","http://192.168.1.207/cm?cmnd=Power2%20On");
                return false;
            }
        });
        luce2p.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                new GetTaskDone().execute("http://192.168.1.204/cm?cmnd=PulseTime1%2030","","http://192.168.1.204/cm?cmnd=Power1%20On");
                new GetTaskDone().execute("http://192.168.1.205/cm?cmnd=PulseTime1%2030","","http://192.168.1.205/cm?cmnd=Power1%20On");
                new GetTaskDone().execute("http://192.168.1.206/cm?cmnd=PulseTime1%2030","","http://192.168.1.206/cm?cmnd=Power1%20On");
                new GetTaskDone().execute("http://192.168.1.207/cm?cmnd=PulseTime1%2030","","http://192.168.1.207/cm?cmnd=Power1%20On");
                return false;
            }
        });

        pedro.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime1%20128","","http://192.168.1.202/cm?cmnd=Power1%20On");
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime3%20128","","http://192.168.1.202/cm?cmnd=Power3%20On");
                new GetTaskDone().execute("http://192.168.1.203/cm?cmnd=PulseTime2%20128","","http://192.168.1.203/cm?cmnd=Power2%20On");
                new GetTaskDone().execute("http://192.168.1.204/cm?cmnd=PulseTime2%20160","","http://192.168.1.204/cm?cmnd=Power2%20On");
                new GetTaskDone().execute("http://192.168.1.205/cm?cmnd=PulseTime2%20160","","http://192.168.1.205/cm?cmnd=Power2%20On");
                new GetTaskDone().execute("http://192.168.1.206/cm?cmnd=PulseTime2%20160","","http://192.168.1.206/cm?cmnd=Power2%20On");
                new GetTaskDone().execute("http://192.168.1.207/cm?cmnd=PulseTime2%20160","","http://192.168.1.207/cm?cmnd=Power2%20On");
                mHandler.post(mpedroRunnable);
                return false;
            }
        });



    }

    protected class GetTaskDone extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Invio comando al dispositivo",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params){
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
                Log.i("HTTP",result);
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return params[2];
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();
            new GetTaskDone2().execute(result);

        }

    }

    protected class GetTaskDone2 extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(getApplicationContext(), "Invio comando al dispositivo2",Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params){
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
                Log.i("HTTP",result);
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Toast.makeText(getApplicationContext(), result,                    Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int)event.getX();
        int y = (int)event.getY();
        mHandler.post(mBlinkRunnable);

        if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.i("XY","x:"+x +" y:"+y);
            if (rectTapp1.contains(x,y)) {
                Log.i("XY","BOOM1!");
                txtDispo.setText("Tapparella1");
                intDispo = 1;
            }
            if (rectTapp2.contains(x,y)) {
                Log.i("XY", "BOOM2!");
                txtDispo.setText("Tapparella2");
                intDispo = 2;
            }
            if (rectTapp3.contains(x,y)) {
                Log.i("XY", "boom3!");
                txtDispo.setText("TapparellaBagno");
                intDispo = 3;
            }
            if (rectLuceTenda.contains(x,y)) {
                Log.i("XY", "BOOM2!");
                txtDispo.setText("LuceTenda");
                intDispo = 4;
            }

        }



        return false;
    }
//-----------------auto off schermo-------------------------
//-----------------auto off schermo-------------------------

    private Runnable mBlinkRunnable = new Runnable() {
        @Override
        public void run() {
            // Exit Runnable if the GPIO is already closed
            if (mLedGpio == null) {
                return;
            }
            try {
                // Toggle the GPIO state
                mLedGpio.setValue(true);
                Log.i("displ", "State set to on");
                mHandler.postDelayed(mBlinkRunnableoff, 1000*60*5);

            } catch (IOException e) {
                Log.e("displ", "Error on PeripheralIO API", e);
            }
        }
    };

    private Runnable mBlinkRunnableoff = new Runnable() {
        @Override
        public void run() {
            // Exit Runnable if the GPIO is already closed
            if (mLedGpio == null) {
                return;
            }
            try {
                // Toggle the GPIO state
                mLedGpio.setValue(false);
                Log.i("displ", "State set to off");
                txtDispo.setText("---");
                intDispo = 0;
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime1%20128","","");
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime3%20128","","");
                new GetTaskDone().execute("http://192.168.1.203/cm?cmnd=PulseTime2%20128","","");
                new GetTaskDone().execute("http://192.168.1.204/cm?cmnd=PulseTime2%20128","","");
                new GetTaskDone().execute("http://192.168.1.205/cm?cmnd=PulseTime2%20128","","");
                new GetTaskDone().execute("http://192.168.1.206/cm?cmnd=PulseTime2%20128","","");
                new GetTaskDone().execute("http://192.168.1.207/cm?cmnd=PulseTime2%20128","","");


                // Reschedule the same runnable in {#INTERVAL_BETWEEN_BLINKS_MS} milliseconds

            } catch (IOException e) {
                Log.e("displ", "Error on PeripheralIO API", e);
            }
        }
    };
    //-------------------------------------------------------

    //-----------------auto pedro-------------------------
    //-----------------auto pedro-------------------------

    private Runnable mpedroRunnable = new Runnable() {
        @Override
        public void run() {
            // Exit Runnable if the GPIO is already closed
            if (mLedGpio == null) {
                return;
            }
            try {
                // Toggle the GPIO state
                mLedGpio.setValue(true);
                Log.i("displ", "pedro on");
                mHandler.postDelayed(mPedroRunnableoff, 1000*35);

            } catch (IOException e) {

            }
        }
    };

    private Runnable mPedroRunnableoff = new Runnable() {
        @Override
        public void run() {
            // Exit Runnable if the GPIO is already closed
            if (mLedGpio == null) {
                return;
            }
            try {
                // Toggle the GPIO state
                mLedGpio.setValue(false);
                Log.i("displ", "pedro off");
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime2%2070","","http://192.168.1.202/cm?cmnd=Power2%20On");
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=PulseTime4%2070","","http://192.168.1.202/cm?cmnd=Power4%20On");



                // Reschedule the same runnable in {#INTERVAL_BETWEEN_BLINKS_MS} milliseconds

            } catch (IOException e) {

            }
        }
    };
    //-------------------------------------------------------

}
