#import "PosPluginFlutterPlugin.h"
#if __has_include(<pos_plugin_flutter/pos_plugin_flutter-Swift.h>)
#import <pos_plugin_flutter/pos_plugin_flutter-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "pos_plugin_flutter-Swift.h"
#endif

@implementation PosPluginFlutterPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftPosPluginFlutterPlugin registerWithRegistrar:registrar];
}
@end
