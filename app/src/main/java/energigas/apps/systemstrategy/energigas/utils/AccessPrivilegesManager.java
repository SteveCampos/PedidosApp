package energigas.apps.systemstrategy.energigas.utils;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import energigas.apps.systemstrategy.energigas.entities.Acceso;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.Privilegio;
import energigas.apps.systemstrategy.energigas.entities.Rol;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;

/**
 * Created by kelvi on 05/12/2016.
 */

public class AccessPrivilegesManager {
    private Class aClass = null;
    private List<View> viewList;
    private IntentListenerAccess listenerAccess;
    private String usuarioId;
    private List<Class> classes;
    private List<AccessFragment> fragmentList;
    private List<String> stringList;


    public AccessPrivilegesManager(Class aClass) {
        this.aClass = aClass;
    }

    public AccessPrivilegesManager setViews(View... views) {
        viewList = new ArrayList<>();
        for (View view : views) {
            viewList.add(view);
        }
        return this;
    }

    public AccessPrivilegesManager setPrivilegesIsEnable(String usuarioId) {
        this.usuarioId = usuarioId;
        List<View> viewList1 = new ArrayList<>();
        HashMap<Integer, View> viewHashMap = new HashMap<>();


        for (Privilegio privilegio : getPrivilegios(usuarioId)) {

            if (viewList != null) {
                for (View v : viewList) {

                    Log.d("MainActivity", "ID: " + String.valueOf(v.getTag()));
                    if (privilegio.getDescripcion().equals(String.valueOf(v.getTag()))) {
                        viewList1.add(v);
                    } else {
                        viewHashMap.put(v.getId(), v);
                    }
                }

            }


        }
        Log.d("FragmentInfo", "NULL : " + getPrivilegios(usuarioId).size());


        for (View view : new ArrayList<>(viewHashMap.values())) {
            view.setEnabled(false);
        }

        for (View view : viewList1) {
            view.setEnabled(true);
        }

        if (getPrivilegios(usuarioId).size() == 0) {

            if (viewList != null) {
                for (View v : viewList) {

                    v.setEnabled(false);
                }
            }


        }
        return this;
    }

    public AccessPrivilegesManager setClassIntent(Class... classIntent) {
        classes = new ArrayList<>();
        for (Class aClass : classIntent) {
            classes.add(aClass);
        }
        return this;
    }

    public AccessPrivilegesManager setClassDialog(String... classIntent) {
        stringList = new ArrayList<>();
        for (String aClass : classIntent) {
            stringList.add(aClass);
        }
        return this;
    }

    public AccessPrivilegesManager setListenerIntent(IntentListenerAccess listenerAccess) {
        this.listenerAccess = listenerAccess;
        return this;
    }

    public AccessPrivilegesManager isIntentEnable() {

        HashMap<String, String> booleanHashMap = new HashMap<>();
        HashMap<String, String> booleanHashMap2 = new HashMap<>();
        for (Privilegio privilegio : getPrivilegios(usuarioId)) {
            for (Class aClass : classes) {
                if (privilegio.getDescripcion().equals(aClass.getSimpleName())) {
                    booleanHashMap.put(aClass.getSimpleName(), aClass.getSimpleName());
                } else {
                    booleanHashMap2.put(aClass.getSimpleName(), aClass.getSimpleName());
                }
            }
        }

        HashMap<String, Boolean> privilegioHashMap = new HashMap<>();

        for (String privilegio : new ArrayList<>(booleanHashMap2.values())) {
            privilegioHashMap.put(privilegio, false);
        }

        for (String privilegio : new ArrayList<>(booleanHashMap.values())) {
            privilegioHashMap.put(privilegio, true);
        }

        if (getPrivilegios(usuarioId).size() == 0) {

            if (classes != null) {
                for (Class aClass : classes) {
                    privilegioHashMap.put(aClass.getSimpleName(), false);
                }
            }


        }


        listenerAccess.onIntentListenerAcces(privilegioHashMap);

        return this;
    }

    public AccessPrivilegesManager isDialogEnable() {

        HashMap<String, String> booleanHashMap = new HashMap<>();
        HashMap<String, String> booleanHashMap2 = new HashMap<>();
        for (Privilegio privilegio : getPrivilegios(usuarioId)) {
            for (String aClass : stringList) {
                if (privilegio.getDescripcion().equals(aClass)) {
                    booleanHashMap.put(aClass, aClass);
                } else {
                    booleanHashMap2.put(aClass, aClass);
                }
            }
        }

        HashMap<String, Boolean> privilegioHashMap = new HashMap<>();

        for (String privilegio : new ArrayList<>(booleanHashMap2.values())) {
            privilegioHashMap.put(privilegio, false);
        }

        for (String privilegio : new ArrayList<>(booleanHashMap.values())) {
            privilegioHashMap.put(privilegio, true);
        }

        if (getPrivilegios(usuarioId).size() == 0) {

            if (stringList != null) {
                for (String aClass : stringList) {
                    privilegioHashMap.put(aClass, false);
                }
            }


        }


        listenerAccess.onIntentListenerAcces(privilegioHashMap);

        return this;
    }


    private List<Privilegio> getPrivilegios(String usuarioId) {

        List<Privilegio> privilegioList = new ArrayList<>();

        for (Rol rol : Rol.getRol(usuarioId)) {
            Log.d("MainActivity", rol.getNombre());
            List<Acceso> accesosFor = Acceso.getAccesosFor(rol.getIdRol() + "", aClass.getSimpleName());

            for (Acceso acceso : accesosFor) {
                List<Privilegio> privilegios = Privilegio.getPrivilegios(acceso.getIdAcceso() + "");
                for (Privilegio privilegio : privilegios) {
                    privilegioList.add(privilegio);
                }
            }

        }

        return privilegioList;
    }


    public AccessPrivilegesManager setFragment(AccessFragment... fragments) {

        fragmentList = new ArrayList<>();
        for (AccessFragment fragment : fragments) {
            fragmentList.add(fragment);
        }

        return this;
    }

    public AccessPrivilegesManager isFragmentEnable() {

        List<AccessFragment> accessFragments = new ArrayList<>();
        for (Privilegio privilegio : getPrivilegios(usuarioId)) {

            for (AccessFragment fragment : fragmentList) {
                Log.d("MainActivity", "PRIV: " + privilegio.getDescripcion() + "- " + fragment.getFragment().getClass().getSimpleName());
                if (fragment.getFragment().getClass().getSimpleName().equals(privilegio.getDescripcion())) {
                    accessFragments.add(fragment);
                }

            }
        }

        listenerAccess.onFragmentAccess(accessFragments);


        return this;
    }

    public void verificarAccesosNavigationView(Menu menu) {
        List<Privilegio> privilegioList = getPrivilegiosRolNavigation(usuarioId);
        Log.d("MENUVAGIATION", "PRIVI: " + privilegioList.size());

        if (privilegioList.size() == 0) {
            for (int i = 0; i < menu.size(); i++) {
                MenuItem menuItem = menu.getItem(i);
                menuItem.setVisible(false);

            }
        }

        for (Privilegio privilegio : privilegioList) {

            if (viewList != null) {
                for (int i = 0; i < menu.size(); i++) {
                    MenuItem menuItem = menu.getItem(i);
                    Log.d("MENUVAGIATION", "NOMBRE: " + String.valueOf(menuItem.getTitle().toString() + " -- PRIVILEGIO: " + privilegio.getDescripcion()));
                    if (privilegio.getDescripcion().equals(menuItem.getTitle().toString())) {
                        menuItem.setVisible(true);
                    } else {

                        if (!menuItem.isVisible()) {
                            menuItem.setVisible(false);
                        }


                    }
                }

            }


        }

    }

    private List<Privilegio> getPrivilegiosRolNavigation(String usuarioId) {

        List<Privilegio> privilegioList = new ArrayList<>();

        for (Rol rol : Rol.getRol(usuarioId)) {
            Log.d("MainActivity", rol.getNombre());
            List<Acceso> accesosFor = Acceso.getAccesosFor(rol.getIdRol() + "", "MenuNavigationView");

            for (Acceso acceso : accesosFor) {
                List<Privilegio> privilegios = Privilegio.getPrivilegios(acceso.getIdAcceso() + "");
                for (Privilegio privilegio : privilegios) {
                    privilegioList.add(privilegio);
                }
            }

        }

        return privilegioList;
    }
}
