package com.example.pos_plugin_flutter

import android.bluetooth.BluetoothDevice
import com.dspread.xpos.CQPOSService
import com.dspread.xpos.QPOSService
import io.flutter.plugin.common.EventChannel
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap


class ListenerImplementation(var event: EventChannel.EventSink) : CQPOSService() {




    private val Delimiter = "||"


    override fun onGetDeviceTestResult(p0: Boolean) {



        TODO("Not yet implemented")
    }

    override fun onQposRequestPinResult(p0: MutableList<String>?, p1: Int) {

        TRACE.d("onQposRequestPinResult():")

        val parameters = StringBuffer()
        val append: StringBuffer = parameters.append(Delimiter).append(p1)

        event.success(JSONObject(mapOf("method" to "onQposRequestPinResult", "parameters" to parameters.toString())).toString())


    }

    override fun onReturnD20SleepTimeResult(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onQposRequestPinStartResult(p0: MutableList<String>?) {
        TODO("Not yet implemented")
    }

    override fun onQposPinMapSyncResult(p0: Boolean, p1: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onRequestWaitingUser() {

        TRACE.d("onRequestWaitingUser()")
        val parameters : StringBuffer = StringBuffer()

        event.success(JSONObject(mapOf("method" to "onRequestWaitingUser", "parameters" to parameters.toString())).toString())


    }

    override fun onReturnSyncVersionInfo(p0: QPOSService.FirmwareStatus?, p1: String?, p2: QPOSService.QposStatus?) {
        TODO("Not yet implemented")
    }

    override fun onReturnSpLogResult(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onReturnRsaResult(p0: String?) {
        TRACE.d("onReturnRSAResult(String arg0):$p0")
        val parameters = StringBuffer()
        parameters.append(p0)
        event.success(JSONObject(mapOf("method" to "onReturnRSAResult", "parameters" to parameters.toString())).toString())

    }

    override fun onQposInitModeResult(p0: Boolean) {
        event.success(JSONObject(mapOf("method" to "onQposInitModeResult", "parameters" to p0.toString())).toString())

        TODO("Not yet implemented")
    }

    override fun onD20StatusResult(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onQposTestSelfCommandResult(p0: Boolean, p1: String?) {
        TODO("Not yet implemented")
    }

    override fun onQposTestCommandResult(p0: Boolean, p1: String?) {
        TODO("Not yet implemented")
    }

    override fun onQposIdResult(p0: Hashtable<String, String>?) {
        TRACE.w("onQposIdResult():" + p0.toString())
        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0?.get("posId"))
        event.success(JSONObject(mapOf("method" to "onQposIdResult", "parameters" to parameters.toString())).toString())
    }

    override fun onQposKsnResult(p0: Hashtable<String, String>?) {

        TRACE.w("onQposKsnResult():" + p0.toString())
        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onQposKsnResult", "parameters" to parameters.toString())).toString())


    }

    override fun onQposIsCardExist(p0: Boolean) {


        TRACE.w("onQposIsCardExist():" + p0.toString())


        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onQposIsCardExist", "parameters" to parameters.toString())).toString())


    }

    override fun onRequestDeviceScanFinished() {

        TRACE.d("onRequestDeviceScanFinished()")
        event.success(JSONObject(mapOf("method" to "onRequestDeviceScanFinished", "parameters" to " ")).toString())

    }

    override fun onQposInfoResult(p0: Hashtable<String, String>?) {
        TRACE.d("onQposInfoResult" + p0.toString())
        val parameters : StringBuffer = StringBuffer()

        if(p0!=null){
        parameters .append(    JSONObject(p0.toMap()).toString())
        }


        event.success(JSONObject(mapOf("method" to "onQposInfoResult", "parameters" to parameters.toString())).toString())
    }

    override fun onQposTestResult(p0: Hashtable<String, String>?) {
        TODO("Not yet implemented")
    }

    override fun onQposCertificateInfoResult(p0: MutableList<String>?) {
        TODO("Not yet implemented")
    }

    override fun onQposGenerateSessionKeysResult(p0: Hashtable<String, String>?) {
        TRACE.d("onQposGenerateSessionKeysResult(Hashtable<String, String> arg0):" + p0.toString())

        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onQposGenerateSessionKeysResult", "parameters" to parameters.toString())).toString())


    }

    override fun onQposDoSetRsaPublicKey(p0: Boolean) {

        TRACE.d("onQposDoSetRsaPublicKey(boolean arg0):$p0")
        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())

        event.success(JSONObject(mapOf("method" to "onQposDoSetRsaPublicKey", "parameters" to parameters.toString())).toString())

    }

    override fun onSearchMifareCardResult(p0: Hashtable<String, String>?) {
        TRACE.d("onSearchMifareCardResult(Hashtable<String, String> arg0):" + p0.toString())

        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())

        event.success(JSONObject(mapOf("method" to "onSearchMifareCardResult", "parameters" to parameters.toString())).toString())

    }

    override fun onBatchReadMifareCardResult(p0: String?, p1: Hashtable<String, MutableList<String>>?) {
        TRACE.d("onBatchReadMifareCardResult(boolean arg0):" + p0 + p1.toString())


        val parameters = StringBuffer()
        parameters.append(p0 + Delimiter + p1.toString())
       event.success(JSONObject(mapOf("method" to "onBatchReadMifareCardResult", "parameters" to parameters.toString())).toString())
    }

    override fun onBatchWriteMifareCardResult(p0: String?, p1: Hashtable<String, MutableList<String>>?) {

        TRACE.d("onBatchWriteMifareCardResult(boolean arg0):" + p0 + p1.toString())


        val parameters = StringBuffer()
        parameters.append(p0 + Delimiter + p1.toString())
        event.success(JSONObject(mapOf("method" to "onBatchWriteMifareCardResult", "parameters" to parameters.toString())).toString())


    }

    override fun onDoTradeResult(p0: QPOSService.DoTradeResult?, p1: Hashtable<String, String>?) {
        TRACE.d("(DoTradeResult result, Hashtable<String, String> decodeData) " + p0.toString() + TRACE.NEW_LINE + "decodeData:" + p1)

        val parameters = StringBuffer()
        if (p1 != null) parameters.append(p0.toString() + Delimiter + p1.toString()) else parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onDoTradeResult", "parameters" to parameters.toString())).toString())


    }

    override fun onFinishMifareCardResult(p0: Boolean) {


        TRACE.d("onFinishMifareCardResult(boolean arg0):$p0")

        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onFinishMifareCardResult", "parameters" to parameters)).toString())

    }

    override fun onVerifyMifareCardResult(p0: Boolean) {
        TRACE.d("onVerifyMifareCardResult(boolean arg0):$p0")

        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onVerifyMifareCardResult", "parameters" to parameters)).toString())

    }

    override fun onReadMifareCardResult(p0: Hashtable<String, String>?) {
        TRACE.d("onReadMifareCardResult:$p0")

        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReadMifareCardResult", "parameters" to parameters.toString())).toString())

    }

    override fun onWriteMifareCardResult(p0: Boolean) {
        TRACE.d("onWriteMifareCardResult:$p0")

        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onWriteMifareCardResult", "parameters" to parameters.toString())).toString())

    }

    override fun onOperateMifareCardResult(p0: Hashtable<String, String>?) {
        TRACE.d("onOperateMifareCardResult:$p0")

        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onOperateMifareCardResult", "parameters" to parameters.toString())).toString())

    }

    override fun getMifareCardVersion(p0: Hashtable<String, String>?) {
        TRACE.d("getMifareCardVersion:$p0")

        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "getMifareCardVersion", "parameters" to parameters.toString())).toString())

    }

    override fun getMifareReadData(p0: Hashtable<String, String>?) {
        TRACE.d("getMifareReadData:$p0")
        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "getMifareReadData", "parameters" to parameters.toString())).toString())

    }

    override fun getMifareFastReadData(p0: Hashtable<String, String>?) {
        TRACE.d("getMifareFastReadData:$p0")
        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "getMifareFastReadData", "parameters" to parameters)).toString())

    }

    override fun writeMifareULData(p0: String?) {
        TRACE.d("writeMifareULData:$p0")
        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "writeMifareULData", "parameters" to parameters.toString())).toString())

    }

    override fun verifyMifareULData(p0: Hashtable<String, String>?) {
        TRACE.d("verifyMifareULData:$p0")

        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "verifyMifareULData", "parameters" to parameters.toString())).toString())

    }

    override fun transferMifareData(p0: String?) {
        TRACE.d("transferMifareData:$p0")
        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "transferMifareData", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestSetAmount() {
        TRACE.d("onRequestSetAmount:")
        event.success(JSONObject(mapOf("method" to "onRequestSetAmount", "parameters" to " ")).toString())

    }

    override fun onRequestSelectEmvApp(p0: ArrayList<String>?) {
        TRACE.d("onRequestSelectEmvApp():" + p0.toString())
        val parameters = StringBuffer()
        if (p0 != null) {
            for (i in p0) {
                parameters.append(i)
            }
        }

        event.success(JSONObject(mapOf("method" to "onRequestSelectEmvApp", "parameters" to parameters.toString())).toString())


    }

    override fun onRequestIsServerConnected() {
        TRACE.d("onRequestIsServerConnected:")
        event.success(JSONObject(mapOf("method" to "onRequestIsServerConnected", "parameters" to " ")).toString())

    }

    override fun onRequestFinalConfirm() {
        TRACE.d("onRequestFinalConfirm:")
        event.success(JSONObject(mapOf("method" to "onRequestFinalConfirm", "parameters" to " ")).toString())

    }

    override fun onRequestOnlineProcess(p0: String?) {
        TRACE.d("onRequestOnlineProcess:")
        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onRequestOnlineProcess", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestTime() {
        TRACE.d("onRequestTime:")
        event.success(JSONObject(mapOf("method" to "onRequestTime", "parameters" to " ")).toString())

    }

    override fun onRequestTransactionResult(p0: QPOSService.TransactionResult?) {
        TRACE.d("onRequestTransactionResult:")
        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onRequestTransactionResult", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestTransactionLog(p0: String?) {
        TRACE.d("onRequestTransactionLog:")
        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onRequestTransactionLog", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestBatchData(p0: String?) {
        TRACE.d("onRequestBatchData:")
        val parameters : StringBuffer = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onRequestBatchData", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestQposConnected() {
        TRACE.d("onRequestQposConnected()")
        val map: HashMap<String, String> = HashMap()
        map["method"] = "onRequestQposConnected"
        val parameters = StringBuffer()
        map["parameters"] = parameters.toString()

        event.success(JSONObject(map.toMap()).toString())
    }

    override fun onRequestQposDisconnected() {
        TRACE.d("onRequestQposDisconnected:")
        event.success(JSONObject(mapOf("method" to "onRequestQposDisconnected", "parameters" to " ")).toString())

    }

    override fun onRequestNoQposDetected() {
        TRACE.d("onRequestNoQposDetected:")
        event.success(JSONObject(mapOf("method" to "onRequestNoQposDetected", "parameters" to " ")).toString())

    }

    override fun onRequestNoQposDetectedUnbond() {
        TRACE.d("onRequestNoQposDetectedUnbond:")
        event.success(JSONObject(mapOf("method" to "onRequestNoQposDetectedUnbond", "parameters" to " ")).toString())

    }

    override fun onError(p0: QPOSService.Error?) {
        TRACE.d("onError:$p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onError", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestDisplay(p0: QPOSService.Display?) {
        TRACE.d("onRequestDisplay:$p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onRequestDisplay", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnReversalData(p0: String?) {
        TRACE.d("onReturnReversalData:$p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnReversalData", "parameters" to parameters.toString())).toString())


    }

    override fun onReturnGetPinInputResult(p0: Int) {
        TRACE.d("onReturnGetPinInputResult:$p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnGetPinInputResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnGetKeyBoardInputResult(p0: String?) {
         }

    override fun onReturnGetPinResult(p0: Hashtable<String, String>?) {
        TRACE.d("onReturnGetPinResult:$p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnGetPinResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnPowerOnIccResult(p0: Boolean, p1: String?, p2: String?, p3: Int) {

        // TODO Auto-generated method stub
        TRACE.d("onReturnPowerOnIccResult(boolean arg0, String arg1, String arg2, int arg3) :" + p0 + TRACE.NEW_LINE.toString() + p1 + TRACE.NEW_LINE.toString() + p2 + TRACE.NEW_LINE + p3)

        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1 + Delimiter + p2 + Delimiter + p3.toString())
        event.success(JSONObject(mapOf("method" to "onReturnPowerOnIccResult", "parameters" to parameters.toString())).toString())


    }

    override fun onReturnPowerOffIccResult(p0: Boolean) {
        TRACE.d("onReturnPowerOffIccResult:$p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnPowerOffIccResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnApduResult(p0: Boolean, p1: String?, p2: Int) {

        TRACE.d("onReturnApduResult(boolean arg0, String arg1, int arg2):" + p0 + TRACE.NEW_LINE.toString() + p1 + TRACE.NEW_LINE + p2)


        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1 + Delimiter + 2.toString())
        event.success(JSONObject(mapOf("method" to "onReturnApduResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnPowerOnFelicaResult(p0: QPOSService.FelicaStatusCode?) {
        TRACE.d("onReturnPowerOnFelicaResult:$p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnPowerOnFelicaResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnPowerOffFelicaResult(p0: QPOSService.FelicaStatusCode?) {
        TRACE.d("onReturnPowerOffFelicaResult:$p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnPowerOffFelicaResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnSendApduFelicaResult(p0: QPOSService.FelicaStatusCode?, p1: String?, p2: String?) {
        TODO("Not yet implemented")
    }

    override fun onReturnSetSleepTimeResult(p0: Boolean) {
        TRACE.d("onReturnSetSleepTimeResult:$p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnSetSleepTimeResult", "parameters" to parameters.toString())).toString())

    }

    override fun onGetCardNoResult(p0: String?) {
        TRACE.d("onGetCardNoResult:$p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onGetCardNoResult", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestSignatureResult(p0: ByteArray?) {
        TRACE.d("onRequestSignatureResult(byte[] arg0):" + p0.toString())

        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onRequestSignatureResult", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestCalculateMac(p0: String?) {
        TRACE.d("onRequestCalculateMac" + p0.toString())

        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onRequestCalculateMac", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestUpdateWorkKeyResult(p0: QPOSService.UpdateInformationResult?) {
        TRACE.d("onRequestUpdateWorkKeyResult" + p0.toString())

        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onRequestUpdateWorkKeyResult", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestSendTR31KeyResult(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onReturnCustomConfigResult(p0: Boolean, p1: String?) {
        TRACE.d("onReturnCustomConfigResult(boolean isSuccess, String result):" + p0 + TRACE.NEW_LINE.toString() + p1)

        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1)

        event.success(JSONObject(mapOf("method" to "onReturnCustomConfigResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnDoInputCustomStr(p0: Boolean, p1: String?, p2: String?) {
        TODO("Not yet implemented")
    }

    override fun onRetuenGetTR31Token(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onRequestSetPin() {
        TRACE.d("onRequestSetPin")

        event.success(JSONObject(mapOf("method" to "onRequestSetPin", "parameters" to " ")).toString())

    }

    override fun onReturnSetMasterKeyResult(p0: Boolean) {
        TRACE.d("onReturnSetMasterKeyResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnSetMasterKeyResult", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestUpdateKey(p0: String?) {
        TRACE.d("onRequestUpdateKey: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onRequestUpdateKey", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnUpdateIPEKResult(p0: Boolean) {
        TRACE.d("onReturnUpdateIPEKResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnUpdateIPEKResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnRSAResult(p0: String?) {
        TRACE.d("onReturnRSAResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnRSAResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnUpdateEMVResult(p0: Boolean) {
        TRACE.d("onReturnUpdateEMVResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnUpdateEMVResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnGetQuickEmvResult(p0: Boolean) {
        TRACE.d("onReturnGetQuickEmvResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnGetQuickEmvResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnGetEMVListResult(p0: String?) {
        TRACE.d("onReturnGetEMVListResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnGetEMVListResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnGetCustomEMVListResult(p0: MutableMap<String, String>?) {

    }

    override fun onReturnUpdateEMVRIDResult(p0: Boolean) {
        TRACE.d("onReturnUpdateEMVRIDResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnUpdateEMVRIDResult", "parameters" to parameters.toString())).toString())

    }

    override fun onDeviceFound(p0: BluetoothDevice?) {
        TRACE.d("onDeviceFound: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onDeviceFound", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnBatchSendAPDUResult(p0: LinkedHashMap<Int, String>?) {
        TRACE.d("onReturnBatchSendAPDUResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnBatchSendAPDUResult", "parameters" to parameters.toString())).toString())

    }

    override fun onBluetoothBonding() {
        TRACE.d("onBluetoothBonding")
        event.success(JSONObject(mapOf("method" to "onBluetoothBonding", "parameters" to " ")).toString())

    }

    override fun onBluetoothBonded() {
        TRACE.d("onBluetoothBonded")
        event.success(JSONObject(mapOf("method" to "onBluetoothBonded", "parameters" to " ")).toString())

    }

    override fun onWaitingforData(p0: String?) {
        TRACE.d("onWaitingforData: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onWaitingforData", "parameters" to parameters.toString())).toString())

    }

    override fun onBluetoothBondFailed() {
        TRACE.d("onBluetoothBondFailed")
        event.success(JSONObject(mapOf("method" to "onBluetoothBondFailed", "parameters" to  " ")).toString())

    }

    override fun onBluetoothBondTimeout() {
        TRACE.d("onBluetoothBondTimeout")
        event.success(JSONObject(mapOf("method" to "onBluetoothBondTimeout", "parameters" to " ")).toString())

    }

    override fun onReturniccCashBack(p0: Hashtable<String, String>?) {
        TRACE.d("onReturniccCashBack: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturniccCashBack", "parameters" to parameters.toString())).toString())

    }

    override fun onLcdShowCustomDisplay(p0: Boolean) {
        TRACE.d("onLcdShowCustomDisplay: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onLcdShowCustomDisplay", "parameters" to parameters.toString())).toString())

    }

    override fun onUpdatePosFirmwareResult(p0: QPOSService.UpdateInformationResult?) {
        TRACE.d("onUpdatePosFirmwareResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onUpdatePosFirmwareResult", "parameters" to parameters.toString())).toString())

    }

    override fun onBluetoothBoardStateResult(p0: Boolean) {
        TRACE.d("onBluetoothBoardStateResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onBluetoothBoardStateResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnDownloadRsaPublicKey(p0: HashMap<String, String>?) {
        TRACE.d("onReturnDownloadRsaPublicKey: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnDownloadRsaPublicKey", "parameters" to parameters.toString())).toString())

    }

    override fun onGetPosComm(p0: Int, p1: String?, p2: String?) {
        TRACE.d("onGetPosComm(int mod, String amount, String posid):" + p1 + TRACE.NEW_LINE.toString() + p1 + TRACE.NEW_LINE.toString() + p2)


        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1 + Delimiter + p2)
        event.success(JSONObject(mapOf("method" to "onGetPosComm", "parameters" to parameters.toString())).toString())

    }

    override fun onUpdateMasterKeyResult(p0: Boolean, p1: Hashtable<String, String>?) {
        TRACE.d("onUpdateMasterKeyResult:" + p1 + TRACE.NEW_LINE.toString() + p1)


        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1)
        event.success(JSONObject(mapOf("method" to "onUpdateMasterKeyResult", "parameters" to parameters.toString())).toString())

    }

    override fun onPinKey_TDES_Result(p0: String?) {
        TRACE.d("onPinKey_TDES_Result: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onPinKey_TDES_Result", "parameters" to parameters.toString())).toString())

    }

    override fun onEmvICCExceptionData(p0: String?) {
        TRACE.d("onEmvICCExceptionData: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onEmvICCExceptionData", "parameters" to parameters.toString())).toString())

    }

    override fun onSetParamsResult(p0: Boolean, p1: Hashtable<String, Any>?) {
        TRACE.d("onSetParamsResult:" + p1 + TRACE.NEW_LINE.toString() + p1)


        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1)
        event.success(JSONObject(mapOf("method" to "onSetParamsResult", "parameters" to parameters.toString())).toString())

    }

    override fun onSetVendorIDResult(p0: Boolean, p1: Hashtable<String, Any>?) {
        TODO("Not yet implemented")
    }

    override fun onGetInputAmountResult(p0: Boolean, p1: String?) {
        TRACE.d("onGetInputAmountResult:" + p1 + TRACE.NEW_LINE.toString() + p1)


        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1)
        event.success(JSONObject(mapOf("method" to "onGetInputAmountResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnNFCApduResult(p0: Boolean, p1: String?, p2: Int) {
        TRACE.d("onReturnNFCApduResult:" + p1 + TRACE.NEW_LINE.toString() + p1 + TRACE.NEW_LINE.toString() + p2)


        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1 + Delimiter + p2)
        event.success(JSONObject(mapOf("method" to "onReturnNFCApduResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnMPUCardInfo(p0: Hashtable<String, String>?) {
        TRACE.d("onReturnMPUCardInfo: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnMPUCardInfo", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnPowerOnNFCResult(p0: Boolean, p1: String?, p2: String?, p3: Int) {
        TRACE.d("onReturnPowerOnNFCResult:" + p1 + TRACE.NEW_LINE.toString() + p1 + TRACE.NEW_LINE.toString() + p2 + TRACE.NEW_LINE.toString() + p3)


        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1 + Delimiter + p2 + Delimiter + p3)
        event.success(JSONObject(mapOf("method" to "onReturnPowerOnNFCResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnPowerOffNFCResult(p0: Boolean) {
        TRACE.d("onReturnPowerOffNFCResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnPowerOffNFCResult", "parameters" to parameters.toString())).toString())

    }

    override fun onCbcMacResult(p0: String?) {
        TRACE.d("onCbcMacResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onCbcMacResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReadBusinessCardResult(p0: Boolean, p1: String?) {
        TRACE.d("onReadBusinessCardResult:" + p1 + TRACE.NEW_LINE.toString() + p1)


        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1)
        event.success(JSONObject(mapOf("method" to "onReadBusinessCardResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReadGasCardResult(p0: Boolean, p1: String?) {
        TRACE.d("onReadGasCardResult:" + p1 + TRACE.NEW_LINE.toString() + p1)


        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1)
        event.success(JSONObject(mapOf("method" to "onReadGasCardResult", "parameters" to parameters.toString())).toString())

    }

    override fun onWriteBusinessCardResult(p0: Boolean) {
        TRACE.d("onWriteBusinessCardResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onWriteBusinessCardResult", "parameters" to parameters.toString())).toString())

    }

    override fun onWriteGasCardResult(p0: Boolean) {
        TRACE.d("onWriteGasCardResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onWriteGasCardResult", "parameters" to parameters.toString())).toString())

    }

    override fun onConfirmAmountResult(p0: Boolean) {
        TRACE.d("onConfirmAmountResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onConfirmAmountResult", "parameters" to parameters.toString())).toString())

    }

    override fun onSetManagementKey(p0: Boolean) {
        TRACE.d("onSetManagementKey: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onSetManagementKey", "parameters" to parameters.toString())).toString())

    }

    override fun onSetSleepModeTime(p0: Boolean) {
        TRACE.d("onSetSleepModeTime: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onSetSleepModeTime", "parameters" to parameters.toString())).toString())

    }

    override fun onGetSleepModeTime(p0: String?) {
        TRACE.d("onGetSleepModeTime: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onGetSleepModeTime", "parameters" to parameters.toString())).toString())

    }

    override fun onGetShutDownTime(p0: String?) {
        TRACE.d("onGetShutDownTime: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onGetShutDownTime", "parameters" to parameters.toString())).toString())

    }

    override fun onEncryptData(p0: Hashtable<String, String>?) {
        TRACE.d("onEncryptData: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onEncryptData", "parameters" to parameters.toString())).toString())

    }

    override fun onAddKey(p0: Boolean) {
        TRACE.d("onAddKey: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onAddKey", "parameters" to parameters.toString())).toString())

    }

    override fun onSetBuzzerResult(p0: Boolean) {
        TRACE.d("onSetBuzzerResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onSetBuzzerResult", "parameters" to parameters.toString())).toString())

    }

    override fun onSetBuzzerTimeResult(p0: Boolean) {
        TRACE.d("onSetBuzzerTimeResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onSetBuzzerTimeResult", "parameters" to parameters.toString())).toString())

    }

    override fun onSetBuzzerStatusResult(p0: Boolean) {
        TRACE.d("onSetBuzzerStatusResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onSetBuzzerStatusResult", "parameters" to parameters.toString())).toString())

    }

    override fun onGetBuzzerStatusResult(p0: String?) {
        TRACE.d("onGetBuzzerStatusResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onGetBuzzerStatusResult", "parameters" to parameters.toString())).toString())

    }

    override fun onQposDoTradeLog(p0: Boolean) {
        TRACE.d("onQposDoTradeLog: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onQposDoTradeLog", "parameters" to parameters.toString())).toString())

    }

    override fun onQposDoGetTradeLogNum(p0: String?) {
        TRACE.d("onQposDoGetTradeLogNum: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onQposDoGetTradeLogNum", "parameters" to parameters.toString())).toString())

    }

    override fun onQposDoGetTradeLog(p0: String?, p1: String?) {
        TRACE.d("onQposDoGetTradeLog(String arg0, String arg1):" + p0 + TRACE.NEW_LINE + p1)

        val parameters = StringBuffer()
        parameters.append(p0 + Delimiter + p1)
        event.success(JSONObject(mapOf("method" to "onQposDoGetTradeLog", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestDevice() {
        TRACE.d("onRequestDevice: ")
        val parameters = StringBuffer()

        event.success(JSONObject(mapOf("method" to "onRequestDevice", "parameters" to parameters.toString())).toString())

    }

    override fun onGetKeyCheckValue(p0: MutableList<String>?) {
        TRACE.d("onGetKeyCheckValue: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onGetKeyCheckValue", "parameters" to parameters.toString())).toString())

    }

    override fun onGetDevicePubKey(p0: String?) {
        TRACE.d("onGetDevicePubKey: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onGetDevicePubKey", "parameters" to parameters.toString())).toString())

    }

    override fun onSetPosBluConfig(p0: Boolean) {
        TRACE.d("onSetPosBluConfig: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onSetPosBluConfig", "parameters" to parameters.toString())).toString())

    }

    override fun onTradeCancelled() {
        TRACE.d("onTradeCancelled: ")
        val parameters = StringBuffer()
        event.success(JSONObject(mapOf("method" to "onTradeCancelled", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnSetAESResult(p0: Boolean, p1: String?) {
        TRACE.d("onReturnSetAESResult$p0$p1")
        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1)
        event.success(JSONObject(mapOf("method" to "onReturnSetAESResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnAESTransmissonKeyResult(p0: Boolean, p1: String?) {
        TRACE.d("onReturnAESTransmissonKeyResult$p0$p1")
        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1)
        event.success(JSONObject(mapOf("method" to "onReturnAESTransmissonKeyResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnSignature(p0: Boolean, p1: String?) {
        TRACE.d("onReturnSignature$p0$p1")
        val parameters = StringBuffer()
        parameters.append(p0.toString() + Delimiter + p1)
        event.success(JSONObject(mapOf("method" to "onReturnSignature", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnConverEncryptedBlockFormat(p0: String?) {
        TRACE.d("onReturnConverEncryptedBlockFormat: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnConverEncryptedBlockFormat", "parameters" to parameters.toString())).toString())

    }

    override fun onQposIsCardExistInOnlineProcess(p0: Boolean) {
        TRACE.d("onQposIsCardExistInOnlineProcess: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onQposIsCardExistInOnlineProcess", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnSetConnectedShutDownTimeResult(p0: Boolean) {
        TRACE.d("onReturnSetConnectedShutDownTimeResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnSetConnectedShutDownTimeResult", "parameters" to parameters.toString())).toString())

    }

    override fun onReturnGetConnectedShutDownTimeResult(p0: String?) {
        TRACE.d("onReturnGetConnectedShutDownTimeResult: $p0")
        val parameters = StringBuffer()
        parameters.append(p0.toString())
        event.success(JSONObject(mapOf("method" to "onReturnGetConnectedShutDownTimeResult", "parameters" to parameters.toString())).toString())

    }

    override fun onRequestNFCBatchData(p0: QPOSService.TransactionResult?, p1: String?) {
        TODO("Not yet implemented")
    }



    override fun onRequestGenerateTransportKey(p0: Hashtable<*, *>?) {
        TODO("Not yet implemented")
    }

    override fun onReturnAnalyseDigEnvelop(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onReturnDisplayQRCodeResult(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onReturnDeviceCSRResult(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onReturnStoreCertificatesResult(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onReturnSignatureAndCertificatesResult(p0: String?, p1: String?, p2: String?) {
        TODO("Not yet implemented")
    }

    override fun onReturnDeviceSigningCertResult(p0: String?, p1: String?) {
        TODO("Not yet implemented")
    }
}
