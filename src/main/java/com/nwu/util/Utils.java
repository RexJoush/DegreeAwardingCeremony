package com.nwu.util;

import com.nwu.lib.NetSDKLib;
import com.sun.jna.Pointer;


public class Utils {

    // 设备断线回调: 通过 CLIENT_Init 设置该回调函数，当设备出现断线时，SDK会调用该函数
    public static class DisConnect implements NetSDKLib.fDisConnect {
        public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {
            // 断线提示
            throw new RuntimeException("Device " + pchDVRIP + " Port " + nDVRPort + " DisConnect!\\n\"");
        }
    }

    // 网络连接恢复，设备重连成功回调
    // 通过 CLIENT_SetAutoReconnect 设置该回调函数，当已断线的设备重连成功时，SDK会调用该函数
    private static class HaveReConnect implements NetSDKLib.fHaveReConnect {
        public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {

            throw new RuntimeException("ReConnect Device " + pchDVRIP + " Port " + nDVRPort);
        }
    }


}
