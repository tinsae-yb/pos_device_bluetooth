package com.example.pos_plugin_flutter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import androidx.annotation.NonNull
import com.dspread.xpos.QPOSService
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** PosPluginFlutterPlugin */
class PosPluginFlutterPlugin : FlutterPlugin, MethodCallHandler, EventChannel.StreamHandler {

    private lateinit var channel: MethodChannel
    private lateinit var eventChannel: EventChannel
    private var pos: QPOSService? = null
    private var listener: ListenerImplementation? = null
    private var context: Context? = null

    var model: QPOSService.CommunicationMode? = null


    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        context = flutterPluginBinding.applicationContext
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "pos_plugin_flutter")
        eventChannel =
            EventChannel(flutterPluginBinding.binaryMessenger, "pos_plugin_flutter_event_channel")
        channel.setMethodCallHandler(this)
        eventChannel.setStreamHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "getPlatformVersion" -> {
                result.success("Android ${android.os.Build.VERSION.RELEASE}")
            }
            "initPos" -> {

                val mode = call.argument<String>("mode")
                    ?: return result.error("communication", "communication mode required", "")

                initPos(mode)

            }

            "getPosSdkVersion" -> {
                TRACE.d("getPosSdkVersion")
                if (pos == null) result.error(
                    "init",
                    "pos not initiated",
                    "pos has to be initiated before any operation"
                )
                else {
                    Methods.getPosSdkVersion(result)
                }
            }

            "doTrade" -> {
                TRACE.d("doTrade")

                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }
                val keyIndex = call.argument<String>("keyIndex")
                val i = keyIndex?.toInt()
                val cardTradeMode = call.argument<String>("cardTradeMode")
                if (!TextUtils.isEmpty(cardTradeMode)) {
                    Methods.setCardTradeMode(cardTradeMode, pos!!)
                }
                val formatId = call.argument<String>("formatId")
                if (!TextUtils.isEmpty(formatId)) {
                    Methods.setFormatId(formatId, pos!!)
                }
                val doTradeMode = call.argument<String>("doTradeMode")
                if (!TextUtils.isEmpty(doTradeMode)) {
                    Methods.setDoTradeMode(cardTradeMode, pos!!)
                }

                Methods.doTrade(30, pos!!)
            }

            "connectBluetoothDevice" -> {
                if (pos == null) result.error(
                    "init",
                    "pos not initiated",
                    "pos has to be initiated before any operation"
                )
                else {
                    val addr = call.argument<String>("bluetooth_addr")
                        ?: return result.error("bluetooth", "bluetooth address mode required", "")

                    Methods.connectBluetoothDevice(addr, pos!!)
                }

            }

            "doEmvApp" -> {
                TRACE.d("doEmvApp")

                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }
                val emvOption = call.argument<String>("EmvOption")
                Methods.doEmvApp(emvOption, pos!!)

            }
            "sendTime" -> {
                TRACE.d("sendTime")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }
                val terminalTime = call.argument<String>("terminalTime")
                Methods.sendTime(terminalTime, pos!!)
            }
            "setAmount" -> {
                TRACE.d("setAmount")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }


                val amount = call.argument<String>("amount")
                val cashbackAmount = call.argument<String>("cashbackAmount")
                val currencyCode = call.argument<String>("currencyCode")
                val transactionType = call.argument<String>("transactionType")
                Methods.setAmount(amount, cashbackAmount, currencyCode, transactionType, pos!!)

            }
            "cancelTrade" -> {
                TRACE.d("cancelTrade")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }
                Methods.cancelTrade(pos!!)
            }
            "sendPin" -> {
                TRACE.d("sendPin")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }
                val pinContent = call.argument<String>("pinContent")
                Methods.sendPin(pinContent, pos!!)
            }
            "anlysEmvIccData" -> {
                TRACE.d("anlysEmvIccData")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }
                val data = call.argument<String>("data")
                result.success(Methods.anlysEmvIccData(data, pos!!))
            }
            "getIccTag" -> {
                TRACE.d("getIccTag")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }
                val tag = call.argument<String>("tag")
                result.success(Methods.tag(tag, pos!!))
            }

            "sendOnlineProcessResult" -> {
                TRACE.d("sendOnlineProcessResult")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }
                val onlineProcessResult = call.argument<String>("onlineProcessResult")
                Methods.sendOnlineProcessResult(onlineProcessResult, pos!!)
            }
            "resetQPosStatus" -> {
                TRACE.d("resetQPosStatus")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }
                Methods.resetQPosStatus(pos!!)
            }
            "resetPosStatus" -> {
                TRACE.d("resetPosStatus")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }
                Methods.resetPosStatus(pos!!)
            }
            "pollOnMifare" -> {
                TRACE.d("pollOnMifare")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }
                Methods.pollOnMifare(pos!!)
            }
            "verifyKeyAB" -> {
                TRACE.d("verifyKeyAB")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }

                val keyType = call.argument<String>("keyType")
                val block = call.argument<String>("block")
                val keyValue = call.argument<String>("keyValue")
                Methods.verifyMifare(keyType, block, keyValue, pos!!)
            }
            "readMifare" -> {
                TRACE.d("readMifare")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }

                val block = call.argument<String>("block")

                Methods.readMifare(block, pos!!)
            }
            "writeMifare" -> {
                TRACE.d("writeMifare")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }

                val block = call.argument<String>("block")
                val data = call.argument<String>("data")

                Methods.writeMifare(block, data, pos!!)
            }
            "finishMifareCard" -> {
                TRACE.d("finishMifareCard")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }



                Methods.finishMifareCard(pos!!)
            }
            "updateWorkKey" -> {
                TRACE.d("updateWorkKey")
                if (pos == null) {
                    result.error(
                        "init",
                        "pos not initiated",
                        "pos has to be initiated before any operation"
                    )
                    return
                }


                val sessionKey = call.argument<String>("sessionKey")

                val kcv = call.argument<String>("kcv")

                Methods.updateWorkKey(sessionKey, kcv, pos!!)
            }
            "calculatePinBlock" -> {
                TRACE.d("calculatePinBlock")

                val td = TripleDES(call.argument<Any>("key") as String?, 4)

                try {
                    result.success(
                        td.encrypt(
                            call.argument<Any>("clearPan") as String?,
                            call.argument<Any>("clearPIN") as String?
                        )
                    )
                } catch (e: Exception) {
                    result.error("0", e.message, e.message)
                }
            }
            "getQposId" -> {
                TRACE.d("getQposId")

                try {
                    Methods.getQposId(pos)
                    result.success("")
                } catch (e: Exception) {
                    result.error("0", e.message, e.message)
                }
            }
            "getPosInfo" -> {
                TRACE.d("getPosInfo")

                try {
                    Methods.getPosInfo(pos)
                    result.success("")
                } catch (e: Exception) {
                    result.error("0", e.message, e.message)
                }
            }
            "isQposPresent" -> {
                TRACE.d("isQposPresent")

                try {

                    result.success(Methods.isQposPresent(pos))
                } catch (e: Exception) {
                    result.error("0", e.message, e.message)
                }
            }
            "disConnectBtPos" -> {
                TRACE.d("disConnectBtPos")

                try {

                    result.success(Methods.disConnectBtPos(pos))
                } catch (e: Exception) {
                    result.error("0", e.message, e.message)
                }
            }
            "getShutDownTimeOnConnected" -> {
                TRACE.d("getShutDownTimeOnConnected")

                try {
                    Methods.getShutDownTimeOnConnected(pos)
                    result.success("success")
                } catch (e: Exception) {
                    result.error("0", e.message, e.message)
                }
            }
            "setShutDownTimeOnConnected" -> {
                TRACE.d("setShutDownTimeOnConnected")
                try {
                    Methods.setShutDownTimeOnConnected(call.argument<Int>("shutDownTime"), pos)
                    result.success("success")
                } catch (e: Exception) {
                    result.error("0", e.message, e.message)
                }
            }
            else -> {
                result.notImplemented()
            }
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
        eventChannel.setStreamHandler(null)
        context = null
    }


    override fun onCancel(arguments: Any?) {
        listener?.event?.endOfStream()
        listener = null
    }


    private fun initPos(mode: String) {

        model = QPOSService.CommunicationMode.valueOf(mode);

        pos = QPOSService.getInstance(model)


        if (mode == "UART") {
            pos?.setD20Trade(true)

        }

        pos?.setConext(context)
//        val handler = Looper.myLooper()?.let { Handler(it) }
//        pos!!.initListener(handler, listener)
    }

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {

        Log.d("TAG", "onListen: *****************************" + "called")
        if (events == null || pos == null) return
        listener = ListenerImplementation(events)
        val handler = Looper.myLooper()?.let { Handler(it) }
        pos?.initListener(handler, listener)
        if (model == QPOSService.CommunicationMode.UART) {
            pos?.setDeviceAddress("/dev/ttyS1")

            pos?.openUart()
        }


    }


}
