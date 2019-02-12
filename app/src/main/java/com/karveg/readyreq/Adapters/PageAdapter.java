package com.karveg.readyreq.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Fragments.ActoFragment;
import com.karveg.readyreq.Fragments.ActorDataFragment;
import com.karveg.readyreq.Fragments.AuthFragment;
import com.karveg.readyreq.Fragments.DatEspFragment;
import com.karveg.readyreq.Fragments.GenericFragment;
import com.karveg.readyreq.Fragments.ObjecDataFragment;
import com.karveg.readyreq.Fragments.ObjecFragment;
import com.karveg.readyreq.Fragments.ReqFunFragment;
import com.karveg.readyreq.Fragments.ReqInfoFragment;
import com.karveg.readyreq.Fragments.ReqNFunFragment;
import com.karveg.readyreq.Fragments.RequFragment;
import com.karveg.readyreq.Fragments.SecExcFragment;
import com.karveg.readyreq.Fragments.SecNorFragment;
import com.karveg.readyreq.Fragments.SourFragment;
import com.karveg.readyreq.Models.Actor;
import com.karveg.readyreq.Models.Objective;
import com.karveg.readyreq.Models.ReqFun;
import com.karveg.readyreq.Models.ReqInfo;
import com.karveg.readyreq.Models.ReqNFun;

public class PageAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;
    private int mode;
    private Actor actor;
    private Objective objective;
    private ReqFun reqfun;
    private ReqNFun reqnfun;
    private ReqInfo reqinfo;


    public PageAdapter(FragmentManager fm, int numberOfTabs, Actor actor, int mode) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.actor = actor;
        this.mode = mode;
    }

    public PageAdapter(FragmentManager fm, int numberOfTabs, Objective objective, int mode) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.objective = objective;
        this.mode = mode;
    }

    public PageAdapter(FragmentManager fm, int numberOfTabs, ReqFun reqfun, int mode) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.reqfun = reqfun;
        this.mode = mode;
    }

    public PageAdapter(FragmentManager fm, int numberOfTabs, ReqNFun reqnfun, int mode) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.reqnfun = reqnfun;
        this.mode = mode;
    }

    public PageAdapter(FragmentManager fm, int numberOfTabs, ReqInfo reqinfo, int mode) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.reqinfo = reqinfo;
        this.mode = mode;
    }

    @Override
    public Fragment getItem(int position) {

        if (mode == MyApplication.ACTORES)
            return selectFragActor(position);
        else if (mode == MyApplication.OBJETIVOS)
            return selectFragObjec(position);
        else if (mode == MyApplication.REQ_NO_FUN)
            return selectFragReqNFun(position);
        else if (mode == MyApplication.REQ_INFO)
            return selectFragReqInfo(position);
        else if (mode == MyApplication.REQ_FUNC)
            return selectFragReqFun(position);
        else
            return null;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    private Fragment selectFragActor(int position) {
        switch (position) {
            case 0:
                return new ActorDataFragment(actor);
            case 1:
                return new AuthFragment(MyApplication.ACTORES, actor);
            case 2:
                return new SourFragment(MyApplication.ACTORES, actor);
            default:
                return null;
        }
    }

    private Fragment selectFragObjec(int position) {
        switch (position) {
            case 0:
                return new ObjecDataFragment(objective);
            case 1:
                return new AuthFragment(MyApplication.OBJETIVOS, objective);
            case 2:
                return new SourFragment(MyApplication.OBJETIVOS, objective);
            case 3:
                return new ObjecFragment(MyApplication.OBJETIVOS, objective);
            default:
                return null;
        }
    }

    private Fragment selectFragReqFun(int position) {
        switch (position) {
            case 0:
                return new ReqFunFragment(reqfun);
            case 1:
                return new AuthFragment(MyApplication.REQ_FUNC, reqfun);
            case 2:
                return new SourFragment(MyApplication.REQ_FUNC, reqfun);
            case 3:
                return new ObjecFragment(MyApplication.REQ_FUNC, reqfun);
            case 4:
                return new RequFragment(MyApplication.REQ_FUNC, reqfun);
            case 5:
                return new ActoFragment(MyApplication.REQ_FUNC, reqfun);
            case 6:
                return new SecNorFragment(MyApplication.REQ_FUNC, reqfun);
            case 7:
                return new SecExcFragment(MyApplication.REQ_FUNC, reqfun);
            default:
                return null;
        }
    }

    private Fragment selectFragReqNFun(int position) {
        switch (position) {
            case 0:
                return new ReqNFunFragment(reqnfun);
            case 1:
                return new AuthFragment(MyApplication.REQ_NO_FUN, reqnfun);
            case 2:
                return new SourFragment(MyApplication.REQ_NO_FUN, reqnfun);
            case 3:
                return new ObjecFragment(MyApplication.REQ_NO_FUN, reqnfun);
            case 4:
                return new RequFragment(MyApplication.REQ_NO_FUN, reqnfun);
            default:
                return null;
        }
    }

    private Fragment selectFragReqInfo(int position) {
        switch (position) {
            case 0:
                return new ReqInfoFragment(reqinfo);
            case 1:
                return new AuthFragment(MyApplication.REQ_INFO, reqinfo);
            case 2:
                return new SourFragment(MyApplication.REQ_INFO, reqinfo);
            case 3:
                return new ObjecFragment(MyApplication.REQ_INFO, reqinfo);
            case 4:
                return new RequFragment(MyApplication.REQ_INFO, reqinfo);
            case 5:
                return new DatEspFragment(MyApplication.REQ_INFO, reqinfo);
            default:
                return null;
        }
    }
}