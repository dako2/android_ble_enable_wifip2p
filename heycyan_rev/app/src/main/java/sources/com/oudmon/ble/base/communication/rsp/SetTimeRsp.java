package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class SetTimeRsp extends BaseRspCmd {
    public boolean bpSettingSupport;
    public int height;
    public boolean mEbookSupport;
    public int mMaxContacts;
    public int mMaxWatchFace;
    public boolean mMusicSupport;
    public boolean mNewSleepProtocol;
    public boolean mSupport4G;
    public boolean mSupportAlbum;
    public boolean mSupportAppMeasure;
    public boolean mSupportAvatar;
    public boolean mSupportBloodOxygen;
    public boolean mSupportBloodPressure;
    public boolean mSupportBloodSugar;
    public boolean mSupportContact;
    public boolean mSupportCustomWallpaper;
    public boolean mSupportECard;
    public boolean mSupportFeature;
    public boolean mSupportGPS;
    public boolean mSupportHrv;
    public boolean mSupportJieLiMusic;
    public boolean mSupportLocation;
    public boolean mSupportLyrics;
    public boolean mSupportManualBloodOxygen;
    public boolean mSupportManualHeart;
    public boolean mSupportMenstruation;
    public boolean mSupportNavPicture;
    public boolean mSupportOneKeyCheck;
    public boolean mSupportPlate;
    public boolean mSupportPressure;
    public boolean mSupportRecord;
    public boolean mSupportTemperature;
    public boolean mSupportWeChat;
    public boolean mSupportWeather;
    public boolean mYaWeiSupport;
    public boolean rtkMcu;
    public int width;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.mSupportTemperature = bArr[0] == 1;
        this.mSupportPlate = bArr[1] == 1;
        this.mSupportMenstruation = bArr[2] == 1;
        byte b = bArr[3];
        this.mSupportCustomWallpaper = (b & 1) != 0;
        this.mSupportBloodOxygen = (b & 2) != 0;
        this.mSupportBloodPressure = (b & 4) != 0;
        this.mSupportFeature = (b & 8) != 0;
        this.mSupportOneKeyCheck = (b & 16) != 0;
        this.mSupportWeather = (b & 32) != 0;
        this.mSupportWeChat = (b & 64) == 0;
        this.mSupportAvatar = (b & 128) != 0;
        this.width = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 4, 6));
        this.height = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 8));
        byte b2 = bArr[10];
        this.mSupportAppMeasure = (b2 & 32) != 0;
        this.mSupportManualBloodOxygen = (b2 & 64) != 0;
        this.mYaWeiSupport = (b2 & 128) != 0;
        this.mNewSleepProtocol = bArr[8] == 1;
        this.mMaxWatchFace = bArr[9];
        this.mSupportContact = (b2 & 1) != 0;
        this.mSupportLyrics = (b2 & 2) != 0;
        this.mSupportAlbum = (b2 & 4) != 0;
        this.mSupportGPS = (b2 & 8) != 0;
        this.mSupportJieLiMusic = (b2 & 16) != 0;
        byte b3 = bArr[11];
        this.mSupportManualHeart = (b3 & 1) != 0;
        this.mSupportECard = (b3 & 2) != 0;
        this.mSupportLocation = (b3 & 4) != 0;
        this.mMusicSupport = (b3 & 16) != 0;
        this.rtkMcu = (b3 & 32) != 0;
        this.mEbookSupport = (b3 & 64) != 0;
        this.mSupportBloodSugar = (b3 & 128) != 0;
        byte b4 = bArr[12];
        if (b4 == 0) {
            this.mMaxContacts = 20;
        } else {
            this.mMaxContacts = b4 * 10;
        }
        byte b5 = bArr[13];
        this.bpSettingSupport = (b5 & 2) != 0;
        this.mSupport4G = (b5 & 4) != 0;
        this.mSupportNavPicture = (b5 & 8) != 0;
        this.mSupportPressure = (b5 & 16) != 0;
        this.mSupportHrv = (b5 & 32) != 0;
        this.mSupportRecord = (b5 & 1) != 0;
        return false;
    }
}
