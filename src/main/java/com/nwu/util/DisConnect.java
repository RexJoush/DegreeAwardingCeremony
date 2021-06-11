package com.nwu.util;

import com.nwu.lib.NetSDKLib;
import com.sun.jna.Pointer;


public class DisConnect implements NetSDKLib.fDisConnect {
    public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {
        throw new RuntimeException("Device" + pchDVRIP + " Port " + nDVRPort + " DisConnect!\n");
    }
}
