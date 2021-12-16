import 'dart:async';
import 'dart:convert';

import 'package:flutter/services.dart';
import 'package:pos_plugin_flutter/qpos_model.dart';

class PosPluginFlutter {
  final MethodChannel _methodChannel = MethodChannel('pos_plugin_flutter');

  final EventChannel _eventChannel =
      EventChannel("pos_plugin_flutter_event_channel");

  Stream<QPOSModel>? _onPosListenerCalled;

  // this getter returns a Stream to listen for events from the pos device
  Stream<QPOSModel> get onPosListenerCalled {
    return _onPosListenerCalled ??= _eventChannel
        .receiveBroadcastStream()
        .map((dynamic event) => _parsePosListenerCall(event));
  }

  // parse the pos response
  QPOSModel _parsePosListenerCall(String state) {
    QPOSModel qposModel = QPOSModel.fromJson(json.decode(state));
    return qposModel;
  }

  //  initiate pos with specified mode-> eg "BLUETOOTH"
  initPos(String mode) async {
    Map<String, String> params = Map<String, String>();
    params['mode'] = mode;
    _methodChannel.invokeMethod('initPos', params);
  }

  // connect to a nearby bluetooth device with a bluetooth address
  connectBluetoothDevice(String address) async {
    _methodChannel
        .invokeMethod('connectBluetoothDevice', {"bluetooth_addr": address});
  }

//  start trade for chip cards ( nfc tags are not supported on this method)
  Future<void> doTrade(Map map) async {
    await _methodChannel.invokeMethod('doTrade', map);
  }

  //  send the amount of the transaction in 12 charcter long string.. how much money in cents eg "000000001200" will be 12.00 bucks in specified currency
  void setAmount(Map<String, String> params) async {
    await _methodChannel.invokeMethod('setAmount', params);
  }

  //  to start emv process -> Start Chip Card Transaction
  void doEmvApp(String s) async {
    Map<String, String> params = Map<String, String>();
    params['EmvOption'] = s;
    await _methodChannel.invokeMethod('doEmvApp', params);
  }

  //  send the current time to the EMV kernel in string data type
  void sendTime(String s) async {
    Map<String, String> params = Map<String, String>();
    params['terminalTime'] = s;
    await _methodChannel.invokeMethod('sendTime', params);
  }

//  to cancel an ongoing trade / transaction
  void cancelTrade() async {
    await _methodChannel.invokeMethod('cancelTrade');
  }

//  send the pin entered by the card holder to the pos device to authenticate
  void sendPin(String s) async {
    Map<String, String> params = Map<String, String>();
    params['pinContent'] = s;
    await _methodChannel.invokeMethod('sendPin', params);
  }

// Parsing ICC data (card Chip data)
  Future anlysEmvIccData(String data) async {
    return await _methodChannel.invokeMethod('anlysEmvIccData', {"data": data});
  }

//  get specific chip info by tag
  Future<Map> getIccTag(String tag) async {
    Map process = await _methodChannel.invokeMethod('getIccTag', {"tag": tag});
    return process;
  }

// Send transaction results from the processor back to EMVSwipe
  void sendOnlineProcessResult(String str) async {
    Map<String, String> params = Map<String, String>();
    params['onlineProcessResult'] = str;
    await _methodChannel.invokeMethod('sendOnlineProcessResult', params);
  }

//  reset device status incase of unexpected error (1)
  Future<void> resetQPosStatus() async {
    await _methodChannel.invokeMethod('resetQPosStatus');
  }

//  reset device status incase of unexpected error (2)
  Future<void> resetPosStatus() async {
    await _methodChannel.invokeMethod('resetPosStatus');
  }

// to start operation on NFC cards -> specifically mifare cards
  Future<void> pollOnMifare() async {
    await _methodChannel.invokeMethod('pollOnMifare');
  }

//  verify mifare card key
  Future<void> verifyKeyAB(
    String keyType,
    String block,
    String keyValue,
  ) async {
    await _methodChannel.invokeMethod('verifyKeyAB',
        {"keyType": keyType, "block": block, "keyValue": keyValue});
  }

  // read a data from a specifc data block of mifare card
  Future<void> readMifare(String block) async {
    await _methodChannel.invokeMethod('readMifare', {"block": block});
  }

  // write  a data on a specifc data block of mifare card

  Future<void> writeMifare(String block, String data) async {
    await _methodChannel
        .invokeMethod('writeMifare', {"data": data, "block": block});
  }

//  finish operation in the mifare card
  Future<void> finishMifareCard() async {
    await _methodChannel.invokeMethod('finishMifareCard');
  }

// update the working key of pos device to the provided key
  Future updateWorkKey(Map map) async {
    await _methodChannel.invokeMethod('updateWorkKey', map);
  }

// calculate the pin block of the card -> accepts correct card pin and card number (16 or 19 digits)
  Future<String> calculatePinBlock(
      String key, String clearPan, String clearPIN) async {
    String pinBloc = await _methodChannel.invokeMethod('calculatePinBlock',
        {"key": key, "clearPan": clearPan, "clearPIN": clearPIN});
    return pinBloc;
  }

// retrieve the id of pos device
  Future getQposId() async {
    String pinBloc = await _methodChannel.invokeMethod('getQposId');
  }

// retrieve the pos device information (battery level, battery capacity, name, model....)
  Future getPosInfo() async {
    await _methodChannel.invokeMethod('getPosInfo');
  }

//  check if pos device is connected
  Future<bool?> isQposPresent() async {
    bool? isQposPresent = await _methodChannel.invokeMethod('isQposPresent');
    return isQposPresent;
  }

// disconnect a connected pos device through bluetooth
  Future disConnectBtPos() async {
    await _methodChannel.invokeMethod('disConnectBtPos');
  }

  // Future setShutDownTimeOnConnected(int shutDownTime) async {
  //   await _methodChannel.invokeMethod(
  //       'setShutDownTimeOnConnected', {"shutDownTime": shutDownTime});
  // }

  // Future getShutDownTimeOnConnected() async {
  //   await _methodChannel.invokeMethod('getShutDownTimeOnConnected');
  // }

  // Future openUart() async {
  //   await _methodChannel.invokeMethod('openUart');
  // }
}
