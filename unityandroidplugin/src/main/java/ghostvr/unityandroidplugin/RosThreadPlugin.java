package ghostvr.unityandroidplugin;

import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Divyanshu on 6/9/17.
 */

public class RosThreadPlugin implements Runnable {
    private ServerSocket serverSocket;
    private float[] dataArray;
    private float[] tempDataArray;
    private String index;

    private String LOG_TAG = getClass().toString();

    @Override
    public void run() {
        dataArray = new float[7];
        tempDataArray = new float[7];

        try {
            serverSocket = new ServerSocket(8888);

            getClientData();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getClientData() throws IOException {
        Socket clientSocket = serverSocket.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String message;

        while(true) {
            message = br.readLine();

            if (message.equals("stop")){
                clientSocket.close();
                break;

            } else {
                String words[] = message.split(",");
                index = words[0];

                try {
                    for (int i = 1; i < words.length; ++i){
                        tempDataArray[i-1] = Float.parseFloat(words[i]);
                    }

                    System.arraycopy(tempDataArray, 0, dataArray, 0, dataArray.length);

                } catch (Exception e){
                    Log.d(LOG_TAG, "Input Exception: " + e.toString());
                }

                if (DebugHelper.rosLogEnabled())
                    Log.d(LOG_TAG, "Input: " + message + "\nTime elapsed: " + SystemClock.elapsedRealtimeNanos());
            }
        }

        clientSocket.close();
    }

    public float[] getPointMatrix(){
        float[] pointArray = new float[]{dataArray[0], dataArray[1], dataArray[2]};

        if (DebugHelper.rosLogEnabled())
            Log.d(LOG_TAG, "Output: Index: " + index + "\nPoints: " + pointArray[0] + " " + pointArray[1] + " " + pointArray[2]);

        return pointArray;
    }

    public float[] getQuatMatrix(){
        float[] quatArray = new float[]{dataArray[3], dataArray[4], dataArray[5], dataArray[6]};

        if (DebugHelper.rosLogEnabled())
            Log.d(LOG_TAG, "Output: Index: " + index + "\nQuat: " + quatArray[0] + " " + quatArray[1] + " " + quatArray[2] + " " + quatArray[3]);

        return quatArray;
    }

}
