package com.nwu.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import com.nwu.lib.NetSDKLib.*;

public class AccessEvent extends Event {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
//    public static final int EVENT_ID = AWTEvent.RESERVED_ID_MAX + 1;
    public static final int EVENT_ID = Event.ACTION_EVENT;

    private BufferedImage gateBufferedImage = null;
    private DEV_EVENT_ACCESS_CTL_INFO msg = null;

    public AccessEvent(Object target,
                       BufferedImage gateBufferedImage,
                       DEV_EVENT_ACCESS_CTL_INFO msg) {
        super(null, EVENT_ID, null);
        this.gateBufferedImage = gateBufferedImage;
        this.msg = msg;
    }

    public BufferedImage getGateBufferedImage() {
        return gateBufferedImage;
    }

    public DEV_EVENT_ACCESS_CTL_INFO getAccessInfo() {
        return msg;
    }
}
