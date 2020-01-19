import com.fazecast.jSerialComm.*;

import java.io.InputStream;
import java.util.Scanner;

public class PlantStatSet
{
    //attributes
    private int tVoc;
    private int soilMoist;
    private int light;
    private int rain;
    private int CO2;

    //Constructor
    public PlantStatSet()
    {
        this.tVoc = 0;
        this.soilMoist = 0;
        this.light = 0;
        this.rain = 0;
        this.CO2 = 0;
    }

    //Access functions
    public int gettVoc()
    {
        return tVoc;
    }

    public int getSoilMoist()
    {
        return soilMoist;
    }

    public int getLight()
    {
        return light;
    }

    public int getRain()
    {
        return rain;
    }

    public int getCO2()
    {
        return CO2;
    }

    public void readPort()
    {
        //this function reads a specified com port, parses the output, and stores it in a PlantStatSet object
        SerialPort port;
        port = SerialPort.getCommPort("COM2");

        port.openPort();

        boolean done = false;

        try
        {
            while((light * soilMoist * CO2 * rain * tVoc) == 0)
            {
                while (port.bytesAvailable() == 0)
                {
                    Thread.sleep(200);
                }

                InputStream inStream = port.getInputStream();

                Scanner s = new Scanner(inStream).useDelimiter("\\A");

                String inLine = s.hasNext() ? s.next() : "";

                String[] vals = inLine.split(", ");

                System.out.println(inLine);

                if(vals.length == 5)
                {
                    light = Integer.parseInt(vals[0]);
                    soilMoist = Integer.parseInt(vals[1]);
                    CO2 = Integer.parseInt(vals[2]);
                    tVoc = Integer.parseInt(vals[3]);

                    //rain seems to have \r\n on the end of it so we need to strip them before parseInt
                    String rainStr = vals[4].trim();

                    rain = Integer.parseInt(rainStr);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        port.closePort();
    }

    @Override
    public String toString()
    {
        //prints formatted data

        String lightStr = "Light: " + light;
        String humidStr = ", Humid: " + soilMoist;
        String co2Str = ", CO2: " + CO2;
        String tVocStr = ", tVoc: " + tVoc;
        String rainStr = ", Rain: " + rain;

        return lightStr + humidStr + co2Str + tVocStr + rainStr;
    }
}
