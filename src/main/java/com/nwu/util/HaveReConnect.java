package com.nwu.util;

import com.nwu.lib.NetSDKLib;
import com.sun.jna.Pointer;

public class HaveReConnect implements NetSDKLib.fHaveReConnect {

    public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {


        throw new RuntimeException("ReConnect Device" + pchDVRIP + " Port " + nDVRPort);
    }
}
