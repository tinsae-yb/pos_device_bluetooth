package com.example.pos_plugin_flutter

import com.dspread.xpos.QPOSService
import io.flutter.plugin.common.MethodChannel
import java.util.*

object Methods {

    fun connectBluetoothDevice(bluetoothAddress: String?, pos: QPOSService) {
        pos.connectBluetoothDevice(true, 20, bluetoothAddress)
    }

    fun getPosSdkVersion(result: MethodChannel.Result) {
        val sdkVersion = QPOSService.getSdkVersion()
        TRACE.d(sdkVersion)
        result.success(sdkVersion)
    }

    fun doTrade(i1: Int, pos: QPOSService) {
        pos.doTrade(i1)
    }

    fun setCardTradeMode(cardTradeMode: String?, pos: QPOSService) {
        pos.setCardTradeMode(QPOSService.CardTradeMode.valueOf(cardTradeMode!!))
    }

    fun setFormatId(formatId: String?, pos: QPOSService) {


        pos.formatId = formatId
    }

    fun setShutDownTimeOnConnected(shutDownTime: Int?, pos: QPOSService?): Unit {


        if (shutDownTime != null) pos?.setShutDownTimeOnConnected(shutDownTime);
    }

    fun getShutDownTimeOnConnected(pos: QPOSService?): Unit {
        pos?.getShutDownTimeOnConnected();
    }

    fun setDoTradeMode(doTradeMode: String?, pos: QPOSService) {
        pos.setDoTradeMode(QPOSService.DoTradeMode.valueOf(doTradeMode!!))
    }

    fun doEmvApp(emvOption: String?, pos: QPOSService) {
        pos.doEmvApp(QPOSService.EmvOption.valueOf(emvOption!!))
    }

    fun sendTime(terminalTime: String?, pos: QPOSService) {
        pos.sendTime(terminalTime)
    }

    fun cancelTrade(pos: QPOSService) {
        pos.cancelTrade()
    }

    fun sendPin(pinContent: String?, pos: QPOSService) {
        pos.sendPin(pinContent)
    }

    fun anlysEmvIccData(data: String?, pos: QPOSService): Hashtable<String, String> {
        return pos.anlysEmvIccData(data)

    }

    fun tag(tag: String?, pos: QPOSService): Hashtable<String, String> {
        return pos.getICCTag(0, 1, tag)
    }

    fun sendOnlineProcessResult(onlineProcessResult: String?, pos: QPOSService) {
        pos.sendOnlineProcessResult(onlineProcessResult)
    }

    fun resetQPosStatus(pos: QPOSService) {
        pos.resetQPosStatus()
    }

    fun resetPosStatus(pos: QPOSService) {
        pos.resetPosStatus()
    }

    fun pollOnMifare(pos: QPOSService) {
        pos.pollOnMifareCard(20)

    }

    fun verifyMifare(keyType: String?, block: String?, keyValue: String?, pos: QPOSService) {
        pos.authenticateMifareCard(QPOSService.MifareCardType.CLASSIC, keyType, block, keyValue, 10)

    }

    fun readMifare(block: String?, pos: QPOSService) {
        pos.readMifareCard(QPOSService.MifareCardType.CLASSIC, block, 20)

    }

    fun writeMifare(block: String?, data: String?, pos: QPOSService) {
        TRACE.d(data)
        TRACE.d(block)
        pos.writeMifareCard(QPOSService.MifareCardType.CLASSIC, block, data, 20)
    }

    fun finishMifareCard(pos: QPOSService) {
        pos.finishMifareCard(10)

    }

    fun updateWorkKey(sessionKey: String?, kcv: String?, pos: QPOSService) {
        pos.updateWorkKey(
            sessionKey, kcv,//PIN KEY
            sessionKey, kcv,//Track KEY
            sessionKey, kcv,//MAC KEY
            0, 5
        )

    }

    fun setAmount(
        amount: String?,
        cashbackAmount: String?,
        currencyCode: String?,
        transactionType: String?,
        pos: QPOSService
    ) {
        pos.setAmount(
            amount,
            cashbackAmount,
            currencyCode,
            QPOSService.TransactionType.valueOf(transactionType!!)
        )

    }

    fun getQposId(pos: QPOSService?) {

        pos?.getQposId()

    }

    fun getPosInfo(pos: QPOSService?) {

        pos?.getQposInfo()

    }

    fun isQposPresent(pos: QPOSService?): Boolean? {

        return pos?.isQposPresent

    }

    fun disConnectBtPos(pos: QPOSService?) {

        pos?.disConnectBtPos()

    }



}