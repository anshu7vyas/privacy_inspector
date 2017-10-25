# privacy_protection

The Privacy Protection tool enables the users to test-monitor-analyze the behavior of all installed apps on Android and recognizes possible privacy loopholes. Privacy Protection tool uses Aspect Oriented Programming to weave custom aspects into Android platform. The primary objective is to enhance Android platform, by implementing a new tool to monitor and log whenever apps make use of privacy-sensitive API. This way our injected aspects blend well with the platform and work together to alert users about privacy leaks.

#### To use this tool, you'll need to instrument the AOSP build using [Instrumentation_tool](https://github.com/poojakanchan/instrumentation_tool)

## Intial Setup
>Please refer to the project wiki, or click the links below for detailed description on getting started:

1. [Getting started with Android 2.3.7 Gingerbread Build][2.3.7]
2. [Procedure for instrumenting the source code and building custom Android emulator][emulator]

## Mock-up data wrangling
1. [Inserting mock-up geocoordinates in the emulator using telnet][telnet]

[home]: https://github.com/av-7/privacy_protection/wiki
[2.3.7]: https://github.com/av-7/privacy_protection/wiki/Getting-started-with-Android-2.3.7-Gingerbread-Build
[emulator]: https://github.com/av-7/privacy_protection/wiki/Procedure-for-instrumenting-the-source-code-and-building-custom-Android-emulator
[telnet]: https://github.com/av-7/privacy_protection/wiki/Inserting-mock-up-geo-coordinates-in-the-emulator-using-telnet

## Understanding the source code:

The source code can be found in ```instrumentation_tool/src```
