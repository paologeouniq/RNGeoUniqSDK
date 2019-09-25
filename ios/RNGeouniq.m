
#import "RNGeouniq.h"

@implementation RNGeouniq

- (dispatch_queue_t)methodQueue
{
	return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE(Geouniq)


RCT_EXPORT_METHOD(enable:(RCTResponseSenderBlock) successCallback)
{
	[[GeoUniq sharedInstance] enable];
	successCallback(@[]);
}

RCT_EXPORT_METHOD(disable:(RCTResponseSenderBlock) successCallback)
{
	[[GeoUniq sharedInstance] disable];
	successCallback(@[]);
}

RCT_EXPORT_METHOD(showConsentDialogAndSet:(RCTResponseErrorBlock)errorCallback successCallback:(RCTResponseSenderBlock)successCallback)
{
	[[GeoUniq sharedInstance] showConsentDialogAndSetWithCompletion:^(bool success) {
		successCallback(@[[NSNumber numberWithBool:success]]);
	}];
}

RCT_EXPORT_METHOD(setConsentStatus:(BOOL)status successCallback:(RCTResponseSenderBlock)successCallback)
{
	[[GeoUniq sharedInstance] setConsentStatusWithIsGranted: status];
	successCallback(@[]);
}

RCT_EXPORT_METHOD(getConsentStatus:(RCTResponseSenderBlock)successCallback)
{
	bool status = [[GeoUniq sharedInstance] getConsentStatus];
	successCallback(@[[NSNumber numberWithBool:status]]);
}

RCT_EXPORT_METHOD(isDeviceIdAvailable:(RCTResponseSenderBlock)successCallback)
{
	bool isAvailable = [[GeoUniq sharedInstance] getDeviceId] != nil;
	successCallback(@[[NSNumber numberWithBool:isAvailable]]);
}

RCT_EXPORT_METHOD(getDeviceId:(RCTResponseSenderBlock)successCallback)
{
	NSString* deviceID = [[GeoUniq sharedInstance] getDeviceId];
	if (deviceID != nil) {
		successCallback(@[deviceID]);
	} else {
		successCallback(@[]);
	}
}

RCT_EXPORT_METHOD(setCustomId:(NSString *)customId successCallback:(RCTResponseSenderBlock)successCallback)
{
	(void)[[GeoUniq sharedInstance] setCustomId:customId];
	successCallback(@[]);
}

@end
  
