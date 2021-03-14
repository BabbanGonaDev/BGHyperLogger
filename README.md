# BGHyperLogger
![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/BabbanGonaDev/BGHyperLogger?include_prereleases)
![GitHub contributors](https://img.shields.io/github/contributors/BabbanGonaDev/BGHyperLogger)
![GitHub last commit](https://img.shields.io/github/last-commit/BabbanGonaDev/BGHyperLogger)
![GitHub top language](https://img.shields.io/github/languages/top/BabbanGonaDev/BGHyperLogger)

An Android application analytics reporting module built for use in [Babbangona Farmer Services](www.babbangona.com) mobile applications.


Application analytics tracking is done using two distinct methods/logics.
- Local logging and uploading of analytics records to a web server.
- Logging and tracking of user interactions using [Mixpanel](https://mixpanel.com/)


## Download:
-------------------

**Step 1:** Add the JitPack repository to your build file
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

**Step 2:** Add the dependency
```groovy
dependencies {
    implementation 'com.github.BabbanGonaDev:BGHyperLogger:<insert latest version>'
}
```


## How to use:
-------------------
To use the BGHyperlogger mobile app analytics library, firstly initialize the library in your application's homepage by calling: 

```java
LogRecords logger = new LogRecords(this, "T-10000000000000AA");
//where T-10000000000000AA is your user's Staff ID
```


Then also initialize Mixpanel by calling:

```java
logger.startMixPanelClass(this);
```

General app logs can be tracked using 
```java
logger.captureGeneralLogs()
```
While specific Audit logs can be tracked using 
``` java
logger.captureAuditLogs()
```

User interaction behaviour can also be tracked in two ways.
1. Using general messages:
```java
logger.mixPanelTracker("User opened camera", this);
```

2. Using JSONObjects to capture additional information:
```java
JSONObject obj = new JSONObject();
try {
    obj.put("loginId", "fnsdnfjasdq");
    obj.put("paymentId", "iufsdfdfqew");
    obj.put("profileType", "Premium");
    obj.put("artistName", "Adele");
    obj.put("songId", "12234");
} catch (JSONException e) {
    e.printStackTrace();
}
logger.mixPanelTracker("User purchased a new song", obj, this);
```

## Example
---------------------------
For more understanding and examples, a sample implementation can be found [here](https://github.com/BabbanGonaDev/BGHyperLogger/tree/master/sample)

## Maintenance
--------------------------
This library project is built and maintained by:

 &copy; Babbangona Enterprise Systems Engineering 2021. All Rights Reserved