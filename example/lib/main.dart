import 'package:flutter/material.dart';
import 'package:pos_plugin_flutter/pos_plugin_flutter.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  @override
  void initState() {
    super.initState();
   posPluginFlutter.onPosListenerCalled.listen((event) {


       print(event.toJson());

      // print(event.runtimeType);
      // print(event);

    });
  }

  PosPluginFlutter posPluginFlutter = PosPluginFlutter();



  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            children: [
              TextButton(
                  onPressed: () {
                    posPluginFlutter.initPos("BLUETOOTH");
                  },
                  child: Text("init")),
              TextButton(
                  onPressed: () {
                    posPluginFlutter.connectBluetoothDevice("CC:C2:61:DB:11:94");
                  },
                  child: Text("connect")),
            ],
          ),
        ),
      ),
    );
  }
}
