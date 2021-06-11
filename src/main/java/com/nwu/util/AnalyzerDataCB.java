package com.nwu.util;

import com.nwu.lib.NetSDKLib;
import com.nwu.lib.NetSDKLib.*;
import com.nwu.lib.ToolKits;
import com.sun.jna.Pointer;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class AnalyzerDataCB implements fAnalyzerDataCallBack {

    private BufferedImage gateBufferedImage = null;

    private int index = 1;

    public int invoke(LLong lAnalyzerHandle,
                      int dwAlarmType,
                      Pointer pAlarmInfo,
                      Pointer pBuffer,
                      int dwBufSize,
                      Pointer dwUser,
                      int nSequence,
                      Pointer reserved) {

        System.out.println("test " + index++);

        if (lAnalyzerHandle.longValue() == 0 || pAlarmInfo == null) {
            return -1;
        }

        File path = new File("./GateSnapPicture/");
        if (!path.exists()) {
            path.mkdir();
        }

        // /< 门禁事件
        if (dwAlarmType == NetSDKLib.EVENT_IVS_ACCESS_CTL) {
            DEV_EVENT_ACCESS_CTL_INFO msg = new DEV_EVENT_ACCESS_CTL_INFO();
//            ToolKits.GetPointerData(pAlarmInfo, msg);

            // 保存图片，获取图片缓存
            String snapPicPath = path + "\\" + System.currentTimeMillis() + "GateSnapPicture.jpg";  // 保存图片地址
            byte[] buffer = pBuffer.getByteArray(0, dwBufSize);
            ByteArrayInputStream byteArrInputGlobal = new ByteArrayInputStream(buffer);

            try {
                gateBufferedImage = ImageIO.read(byteArrInputGlobal);

                if(gateBufferedImage != null) {
                    ImageIO.write(gateBufferedImage, "jpg", new File(snapPicPath));
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            // 图片以及门禁信息界面显示
//            EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();

            //EventQueue eventQueue = Toolkit.getDefaultToolkit();
            //if (eventQueue != null) {
            AccessEvent accessEvent = new AccessEvent(this,
                        gateBufferedImage,
                        msg);
            byte[] szCardName = accessEvent.getAccessInfo().getSzCardName();
            byte[] szCardNo = accessEvent.getAccessInfo().getSzCardNo();


            System.out.println("No: " + new String(szCardNo));

            // eventQueue.postEvent( accessEvent);
//          RestTemplate restTemplate = new RestTemplate();
//
//          restTemplate.getForObject("/asa/" + , String.class);

            //从数据中根据id查用户信息



            //}
        }

        return 0;
    }
}
