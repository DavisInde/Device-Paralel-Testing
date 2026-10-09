# Run Guide: Parallel Real-Device Tests (Appium + Cucumber + TestNG)

## How a run works

1. `runDeviceFarm` asks the Appium device-farm plugin for connected devices and keeps only real, online, free ones.
2. It writes `build/resources/test/parallel_testing.xml` with one `<test>` per device.
3. `test` runs that suite in parallel, one thread per device.
4. Each thread keeps its own device and Appium driver (`DeviceContext`). The driver is created in the `@Before` hook and quit in `@After`.
5. Each device filters the scenarios by its own Cucumber tag expression.

## One-time setup

### Appium server

```
appium server -ka 800 --use-plugins=device-farm -pa /wd/hub \
  --plugin-device-farm-platform=both --allow-insecure=adb_shell
```

- `--plugin-device-farm-platform=both` is needed to list iOS and Android (macOS only).
- If the server runs without `-pa`, nothing to do. If it runs with `-pa /wd/hub`, export `APPIUM_BASE_PATH=wd/hub`.
- Check: `curl localhost:4723/device-farm/api/device` must show `"realDevice": true` for each phone.

### Environment variables (all optional, defaults are in `Config.kt`)

| Variable | Default | Meaning |
|---|---|---|
| `APPIUM_URL` | `http://localhost:4723/` | Appium server |
| `APPIUM_BASE_PATH` | empty | Must match `appium --base-path` |
| `IOS_BUNDLE_ID` | `com.alfamart.mylawson.staging` | Lawson iOS bundle id |
| `XCODE_ORG_ID` | `M7R6QZ3L57` | Apple Team ID |
| `WDA_BUNDLE_ID` | `com.alfamart.WebDriverAgentRunner` | `updatedWDABundleId`, any id allowed by the wildcard profile |

Android package and activity are in `Config.kt` (`appPackageId`, `appActivity`).

### iPhone

1. Device UDID registered in the Apple Developer portal.
2. Plug in and tap Trust.
3. Settings, Privacy & Security, Developer Mode on, then restart.
4. Settings, Developer, Enable UI Automation on.
5. Auto-Lock set to Never.
6. Install the Lawson app.
7. Check WDA signing once. Open `~/.appium/node_modules/appium-xcuitest-driver/node_modules/appium-webdriveragent/WebDriverAgent.xcodeproj`, select target `WebDriverAgentRunner`, enable automatic signing with your team, and run Product, Test. Then press Stop in Xcode. Appium starts its own WDA during tests, so leave Xcode's copy stopped.

### Android phone

1. Enable USB debugging, then confirm `adb devices` lists it as `device`.
2. Install the Lawson app.

## Tagging scenarios

Put tags above a scenario in the `.feature` file:

```gherkin
@login @smoke
Scenario: Verify user can login
```

Tag expressions use Cucumber syntax: `@login`, `@login and @smoke`, `@a or @b`, `@login and not @wip`.
A blank expression means all scenarios.

## Run commands

All commands use `./gradlew clean test` plus the options below.

### 1. Only iPhone, or only Android

```
./gradlew clean test -Pplatform=ios     -Ptags=@login
./gradlew clean test -Pplatform=android -Ptags=@login
```

With several real iPhones connected, all of them run in parallel with the same tag.

### 2. iPhone and Android together, same scenarios

```
./gradlew clean test -Ptags=@login
```

`-Pplatform` defaults to `both`. Every real device runs `@login` in parallel.

### 3. Each device runs different scenarios

By platform:

```
./gradlew clean test -PtagsIos=@checkout -PtagsAndroid=@login
```

By individual device (UDID from `adb devices`, Xcode Devices window, or the device-farm API). Separate pairs with `;`:

```
./gradlew clean test -PdeviceTags="00008020-000948A902EA002E=@checkout;R58M12ABCDE=@login"
```

Priority for each device: `-PdeviceTags` for its UDID, then `-PtagsIos` or `-PtagsAndroid`, then `-Ptags`. A device that matches none of them runs all scenarios, because the filter is blank. To avoid that, always set `-Ptags` as a default.

## Reading results

- Console shows `Real devices (...)` and `<platform>:<name> -> tags '<expr>'` for every device.
- Report: `build/reports/tests/test/index.html`.
- The generated suite is `build/resources/test/parallel_testing.xml`. Open it to see which device got which tag.

## Troubleshooting

| Symptom | Fix |
|---|---|
| `Real devices ():` is empty | Device not listed as `realDevice:true`, offline, or busy. Check the device-farm API. |
| `unknown command` on session start | `APPIUM_BASE_PATH` does not match the server's `--base-path`. |
| WDA signing or "bundle id not available" | Check Team ID, wildcard profile, and `WDA_BUNDLE_ID`. Look at the `showXcodeLog` output in the Appium log. |
| `UndefinedStepException` | A step in the feature has no matching step definition under `src/test/java/step`. |
| Zero scenarios ran | The tag expression matched nothing. |
| Session never closes or device stays busy | A crashed run left a session. Restart Appium. |
