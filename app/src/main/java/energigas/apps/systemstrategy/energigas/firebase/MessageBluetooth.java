package energigas.apps.systemstrategy.energigas.firebase;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Steve on 29/09/2016.
 */

public class MessageBluetooth {

    private String message;
    private String bytes;
    private String bytesEncoded;
    private String charset;
    private long time;
    private String type; //SENDED OR RECEIVED.
    private String meMAC;
    private String himMAC;

    public MessageBluetooth(String message, String bytes, String bytesEncoded, String charset, long time, String type, String meMAC, String himMAC) {
        this.message = message;
        this.bytes = bytes;
        this.bytesEncoded = bytesEncoded;
        this.charset = charset;
        this.time = time;
        this.type = type;
        this.meMAC = meMAC;
        this.himMAC = himMAC;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public String getBytesEncoded() {
        return bytesEncoded;
    }

    public void setBytesEncoded(String bytesEncoded) {
        this.bytesEncoded = bytesEncoded;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeMAC() {
        return meMAC;
    }

    public void setMeMAC(String meMAC) {
        this.meMAC = meMAC;
    }

    public String getHimMAC() {
        return himMAC;
    }

    public void setHimMAC(String himMAC) {
        this.himMAC = himMAC;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("message", message);
        result.put("bytes", bytes);
        result.put("bytesEncoded", bytesEncoded);
        result.put("charset", charset);
        result.put("time", time);
        result.put("type", type);
        result.put("meMAC", meMAC);
        result.put("himMAC", himMAC);
        return result;
    }
}
