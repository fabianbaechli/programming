
public class Device {

    private String name;
    private String mac;

    public Device(String name, String mac){
        setName(name);
        setMac(mac);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setMac(String mac){
        this.mac = mac;
    }

    public String getMac(){
        return mac;
    }

    public static boolean isMacValid(String macAddress){
        if(getMacArray(macAddress) != null){
            return true;
        }
        else {
            return false;
        }
    }

    public static byte[] getMacArray (String macString){

        String[] macAddress = macString.split("\\:|\\-");
        byte[] macByte = new byte[6];

        //Invalid length
        if (macAddress.length != 6){
            macByte = null;
            return macByte;
        }

        try{
            for (int i = 0; i < 6; i++){
                macByte[i] = (byte) Integer.parseInt(macAddress[i], 16);
            }
            //If whole String is legit
            return macByte;
        }
        //Invalid digit
        catch (NumberFormatException e) {
            macByte = null;
            return macByte;
        }
    }
}
