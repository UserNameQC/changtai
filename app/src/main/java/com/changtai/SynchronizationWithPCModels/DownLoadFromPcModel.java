package com.changtai.SynchronizationWithPCModels;

import com.changtai.sqlModel.CardReeplacementModel;
import com.changtai.sqlModel.DeviceModel;
import com.changtai.sqlModel.PriceModel;
import com.changtai.sqlModel.PurchaseRecordModel;
import com.changtai.sqlModel.UserModel;

import java.util.List;

public class DownLoadFromPcModel {
    public List<DeviceModel> Device;
    public List<UserModel> User;
    public List<PriceModel> Price;
    public List<PurchaseRecordModel> PurchaseRecord;
    public List<CardReeplacementModel> CardReplacement;

    public List<DeviceModel> getDevice() {
        return Device;
    }

    public void setDevice(List<DeviceModel> device) {
        Device = device;
    }

    public List<UserModel> getUser() {
        return User;
    }

    public void setUser(List<UserModel> user) {
        User = user;
    }

    public List<PriceModel> getPrice() {
        return Price;
    }

    public void setPrice(List<PriceModel> price) {
        Price = price;
    }

    public List<PurchaseRecordModel> getPurchaseRecord() {
        return PurchaseRecord;
    }

    public void setPurchaseRecord(List<PurchaseRecordModel> purchaseRecord) {
        PurchaseRecord = purchaseRecord;
    }

    public List<CardReeplacementModel> getCardReplacement() {
        return CardReplacement;
    }

    public void setCardReplacement(List<CardReeplacementModel> cardReplacement) {
        CardReplacement = cardReplacement;
    }
}
