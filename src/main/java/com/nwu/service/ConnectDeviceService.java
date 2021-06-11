package com.nwu.service;

import com.nwu.lib.NetSDKLib;
import com.nwu.lib.NetSDKLib.*;
import com.nwu.lib.ToolKits;

import java.io.File;

import static com.nwu.lib.NetSDKLib.*;

//@Service
public class ConnectDeviceService {
    public static NetSDKLib netsdk = NetSDKLib.NETSDK_INSTANCE;
    public static NetSDKLib configsdk = NetSDKLib.CONFIG_INSTANCE;

    // 登录句柄
    public static LLong m_hLoginHandle = new NetSDKLib.LLong(0);
    private static boolean bInit = false;
    private static boolean bLogopen = false;

    public static boolean init(fDisConnect disConnect,
                               fHaveReConnect haveReConnect) {

        bInit = netsdk.CLIENT_Init(disConnect, null);

        if (!bInit) {
            System.out.println("Initialize SDK failed");
            return false;
        }

        // 打开日志，可选
        LOG_SET_PRINT_INFO setLog = new LOG_SET_PRINT_INFO();

        File path = new File("./sdklog/");
        if (!path.exists()) {
            path.mkdir();
        }

        String logPath = path.getAbsoluteFile().getParent() + "\\sdklog\\" + ToolKits.getDate()
                + ".log";
        setLog.nPrintStrategy = 0;
        setLog.bSetFilePath = 1;
        System.arraycopy(logPath.getBytes(), 0, setLog.szLogFilePath, 0,
                logPath.getBytes().length);

        System.out.println(logPath);
        setLog.bSetPrintStrategy = 1;
        bLogopen = netsdk.CLIENT_LogOpen(setLog);

        if (!bLogopen) {
            System.err.println("Failed to open NetSDK log");
        }
        // 设置断线重连回调接口，设置过断线重连成功回调函数后，当设备出现断线情况，SDK 内部会自动进行重连操作
        // 此操作为可选操作，但建议用户进行设置
        netsdk.CLIENT_SetAutoReconnect(haveReConnect, null);

        // 设置登录超时时间和尝试次数，可选
        int waitTime = 5000; //登录请求响应超时时间设置为 5S
        int tryTimes = 1; //登录时尝试建立链接 1 次
        netsdk.CLIENT_SetConnectTime(waitTime, tryTimes);

        // 设置更多网络参数，NET_PARAM 的 nWaittime，nConnectTryNum 成员与CLIENT_SetConnectTime
        // 接口设置的登录设备超时时间和尝试次数意义相同,可选
        NetSDKLib.NET_PARAM netParam = new NetSDKLib.NET_PARAM();
        netParam.nConnectTime = 10000; // 登录时尝试建立链接的超时时间
        netParam.nGetConnInfoTime = 3000; // 设置子连接的超时时间
        netsdk.CLIENT_SetNetworkParam(netParam);
        return true;
    }
    public static boolean login(String m_strIp, int m_nPort, String m_strUser, String m_strPassword) {
        //IntByReference nError = new IntByReference(0);

        // 入参
        NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY pstInParam = new NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY();
        pstInParam.nPort = m_nPort;
        pstInParam.szIP = m_strIp.getBytes();
        pstInParam.szPassword = m_strPassword.getBytes();
        pstInParam.szUserName = m_strUser.getBytes();

        // 出参
        NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY pstOutParam = new NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY();
        pstOutParam.stuDeviceInfo = new NetSDKLib.NET_DEVICEINFO_Ex();
        //m_hLoginHandle = netsdk.CLIENT_LoginEx2(m_strIp, m_nPort, m_strUser, m_strPassword, 0, null, m_stDeviceInfo, nError);
        m_hLoginHandle = netsdk.CLIENT_LoginWithHighLevelSecurity(pstInParam, pstOutParam);

        if (m_hLoginHandle.longValue() == 0) {
            System.err.printf("Login Device[%s] Port[%d]Failed.\n", m_strIp, m_nPort);
        } else {
            System.out.println("Login Success [ " + m_strIp + " ]");
        }

        return m_hLoginHandle.longValue() == 0 ? false : true;
    }


    /**
     * 订阅实时上传智能分析数据
     *
     * @return
     */
    public static LLong realLoadPic(int ChannelId, fAnalyzerDataCallBack m_AnalyzerDataCB) {
        /**
         * 说明：
         * 	通道数可以在有登录是返回的信息 m_stDeviceInfo.byChanNum 获取
         *  下列仅订阅了0通道的智能事件.
         */
        int bNeedPicture = 1; // 是否需要图片

        LLong m_hAttachHandle = netsdk.CLIENT_RealLoadPictureEx(m_hLoginHandle, ChannelId, EVENT_IVS_ALL,
                bNeedPicture, m_AnalyzerDataCB, null, null);

        if (m_hAttachHandle.longValue() != 0) {
            System.out.println("CLIENT_RealLoadPictureEx Success  ChannelId : " + ChannelId);
        } else {
            System.err.println("CLIENT_RealLoadPictureEx Failed!");
            return null;
        }

        return m_hAttachHandle;
    }


    // 清除环境
    public static void cleanup() {
        if (bLogopen) {
            netsdk.CLIENT_LogClose();
        }
        if (bInit) {
            netsdk.CLIENT_Cleanup();
        }
    }

}
