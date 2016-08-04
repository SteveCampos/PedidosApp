package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kike on 2/08/2016.
 */

public class Agent {
    private String mName;
    private String mEmail;
    private String mRuta;

    public Agent(String mName, String mEmail, String mRuta) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mRuta = mRuta;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmRuta() {
        return mRuta;
    }

    public void setmRuta(String mRuta) {
        this.mRuta = mRuta;
    }

    public static Agent getAgent(){

        Agent agentList = new Agent("Kike Rojas Palomino","kikerojash@gmail.com","Martes - Piura");
        return agentList;
    }


}
