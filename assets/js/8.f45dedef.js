(window.webpackJsonp=window.webpackJsonp||[]).push([[8],{357:function(a,e,t){"use strict";t.r(e);var i=t(25),s=Object(i.a)({},(function(){var a=this,e=a.$createElement,t=a._self._c||e;return t("ContentSlotsDistributor",{attrs:{"slot-key":a.$parent.slotKey}},[t("h1",{attrs:{id:"changelog"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#changelog"}},[a._v("#")]),a._v(" Changelog")]),a._v(" "),t("p",[t("img",{attrs:{src:"https://github.com/MuntashirAkon/AppManager/workflows/Debug%20Build/badge.svg",alt:"Debug Build"}}),a._v(" "),t("a",{attrs:{href:"https://github.com/MuntashirAkon/AppManager/releases/latest",target:"_blank",rel:"noopener noreferrer"}},[t("img",{attrs:{src:"https://img.shields.io/github/v/release/MuntashirAkon/AppManager",alt:"GitHub Release"}}),t("OutboundLink")],1),a._v(" "),t("a",{attrs:{href:"https://f-droid.org/packages/io.github.muntashirakon.AppManager",target:"_blank",rel:"noopener noreferrer"}},[t("img",{attrs:{src:"https://img.shields.io/f-droid/v/io.github.muntashirakon.AppManager",alt:"F-Droid"}}),t("OutboundLink")],1),a._v(" "),t("img",{attrs:{src:"https://img.shields.io/github/repo-size/MuntashirAkon/AppManager",alt:"GitHub Repo Size"}}),a._v(" "),t("img",{attrs:{src:"https://img.shields.io/github/commit-activity/w/MuntashirAkon/AppManager",alt:"GitHub Commit per Week"}})]),a._v(" "),t("p",[a._v("Currently supported versions are "),t("a",{attrs:{href:"#v2-5-13-348"}},[a._v("v2.5.13")]),a._v(", "),t("a",{attrs:{href:"#v2-5-12-341"}},[a._v("v2.5.12")]),a._v(", "),t("a",{attrs:{href:"#v2-5-11-333"}},[a._v("v2.5.11")]),a._v(" and "),t("a",{attrs:{href:"#v2-5-10-324"}},[a._v("v2.5.10")]),a._v(". Please update App Manager if you are using a version older than these.")]),a._v(" "),t("details",{staticClass:"custom-block details"},[t("summary",[a._v("Table of Contents")]),a._v(" "),t("p"),t("div",{staticClass:"table-of-contents"},[t("ul",[t("li",[t("a",{attrs:{href:"#v2-5-13-348"}},[a._v("v2.5.13 (348)")]),t("ul",[t("li",[t("a",{attrs:{href:"#bundled-app-split-apk"}},[a._v("Bundled App (Split APK)")])]),t("li",[t("a",{attrs:{href:"#direct-install-support"}},[a._v("Direct Install Support")])]),t("li",[t("a",{attrs:{href:"#remove-all-blocking-rules"}},[a._v("Remove All Blocking Rules")])]),t("li",[t("a",{attrs:{href:"#app-ops"}},[a._v("App Ops")])]),t("li",[t("a",{attrs:{href:"#enhanced-adb-support"}},[a._v("Enhanced ADB Support")])]),t("li",[t("a",{attrs:{href:"#filtering-in-main-page"}},[a._v("Filtering in Main Page")])]),t("li",[t("a",{attrs:{href:"#apk-backup-sharing"}},[a._v("Apk Backup/Sharing")])]),t("li",[t("a",{attrs:{href:"#batch-ops"}},[a._v("Batch Ops")])]),t("li",[t("a",{attrs:{href:"#translations"}},[a._v("Translations")])]),t("li",[t("a",{attrs:{href:"#app-data-backup"}},[a._v("App Data Backup")])])])]),t("li",[t("a",{attrs:{href:"#v2-5-12-341"}},[a._v("v2.5.12 (341)")])]),t("li",[t("a",{attrs:{href:"#v2-5-11-333"}},[a._v("v2.5.11 (333)")])]),t("li",[t("a",{attrs:{href:"#v2-5-10-324"}},[a._v("v2.5.10 (324)")])]),t("li",[t("a",{attrs:{href:"#v2-5-9-315"}},[a._v("v2.5.9 (315)")])]),t("li",[t("a",{attrs:{href:"#v2-5-8-289"}},[a._v("v2.5.8 (289)")])]),t("li",[t("a",{attrs:{href:"#v2-5-7-265"}},[a._v("v2.5.7 (265)")])]),t("li",[t("a",{attrs:{href:"#v2-5-6-233"}},[a._v("v2.5.6 (233)")])]),t("li",[t("a",{attrs:{href:"#v2-5-5-215"}},[a._v("v2.5.5 (215)")])])])]),t("p")]),a._v(" "),t("h2",{attrs:{id:"v2-5-13-348"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#v2-5-13-348"}},[a._v("#")]),a._v(" v2.5.13 (348)")]),a._v(" "),t("h3",{attrs:{id:"bundled-app-split-apk"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#bundled-app-split-apk"}},[a._v("#")]),a._v(" Bundled App (Split APK)")]),a._v(" "),t("p",[a._v("Bundled app formats such as "),t("strong",[a._v("apks")]),a._v(" and "),t("strong",[a._v("xapk")]),a._v(" are now supported. You can install these apps using the regular install buttons. For root and adb users, apps are installed using shell, and for non-root users, the platform default method is used.")]),a._v(" "),t("p",[t("strong",[a._v("Known Limitations:")])]),a._v(" "),t("ul",[t("li",[a._v("Currently "),t("em",[a._v("all")]),a._v(" splits apks are installed. But this behaviour is going to change in the next release. If you only need a few splits instead of all, extract the "),t("strong",[a._v("apks")]),a._v(" or "),t("strong",[a._v("xapk")]),a._v(" file, and then, create a new zip file with your desired split apks and replace the "),t("strong",[a._v("zip")]),a._v(" extension with "),t("strong",[a._v("apks")]),a._v(". Now, open it with AM.")]),a._v(" "),t("li",[a._v("There is no progress dialog to display the installation progress.")])]),a._v(" "),t("h3",{attrs:{id:"direct-install-support"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#direct-install-support"}},[a._v("#")]),a._v(" Direct Install Support")]),a._v(" "),t("p",[a._v("You can now install "),t("strong",[a._v("apk")]),a._v(", "),t("strong",[a._v("apks")]),a._v(" or "),t("strong",[a._v("xapk")]),a._v(" directly from your favourite browser or file manager. For apps that need updates, a "),t("strong",[a._v("What's New")]),a._v(" dialog is displayed showing the changes in the new version.")]),a._v(" "),t("p",[t("strong",[a._v("Known Limitations:")])]),a._v(" "),t("ul",[t("li",[a._v("Downgrade is not yet possible.")]),a._v(" "),t("li",[a._v("There is no progress dialog to display the installation progress. If you cannot interact with the current page, wait until the installation is finished.")])]),a._v(" "),t("h3",{attrs:{id:"remove-all-blocking-rules"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#remove-all-blocking-rules"}},[a._v("#")]),a._v(" Remove All Blocking Rules")]),a._v(" "),t("p",[a._v("In the Settings page, a new option is added which can be used to remove all blocking rules configured within App Manager.")]),a._v(" "),t("h3",{attrs:{id:"app-ops"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#app-ops"}},[a._v("#")]),a._v(" App Ops")]),a._v(" "),t("ul",[t("li",[a._v("App Ops are now generated using a technique similar to AppOpsX. This should decrease the loading time significantly in the App Ops tab.")]),a._v(" "),t("li",[a._v("In the App Ops tab, a menu item is added which can be used to list only active app ops without including the default app ops. This preferences is saved in the shared preferences.")])]),a._v(" "),t("p",[t("strong",[a._v("Kown Limitations:")]),a._v(" Often the App Ops tab may not be responsive. If that's the case, restart AM.")]),a._v(" "),t("h3",{attrs:{id:"enhanced-adb-support"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#enhanced-adb-support"}},[a._v("#")]),a._v(" Enhanced ADB Support")]),a._v(" "),t("p",[a._v("ADB shell commands are now executed using a technique similar to AppOpsX (This is the "),t("em",[a._v("free")]),a._v(" alternative of Shizuku.). This should dramatically increase the execution time.")]),a._v(" "),t("p",[t("strong",[a._v("Known Limitations:")]),a._v(" AM can often crash or become not responsive. If that's the case, restart AM.")]),a._v(" "),t("h3",{attrs:{id:"filtering-in-main-page"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#filtering-in-main-page"}},[a._v("#")]),a._v(" Filtering in Main Page")]),a._v(" "),t("p",[a._v("Add an option to filter apps that has at least one activity.")]),a._v(" "),t("h3",{attrs:{id:"apk-backup-sharing"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#apk-backup-sharing"}},[a._v("#")]),a._v(" Apk Backup/Sharing")]),a._v(" "),t("p",[a._v("Apk files are now saved as "),t("code",[a._v("app name_version.extension")]),a._v(" instead of "),t("code",[a._v("package.name.extension")]),a._v(".")]),a._v(" "),t("h3",{attrs:{id:"batch-ops"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#batch-ops"}},[a._v("#")]),a._v(" Batch Ops")]),a._v(" "),t("ul",[t("li",[a._v("Added a foreground service to run batch operations. The result of the operation is displayed in a notification. If an operation has failed for some packages, clicking on the notification will open a dialog box listing the failed packages. There is also a "),t("strong",[a._v("Try Again")]),a._v(" button on the bottom which can be used to perform the operation again for the failed packages.")]),a._v(" "),t("li",[a._v("Replaced Linux "),t("em",[a._v("kill")]),a._v(" with "),t("strong",[a._v("force-stop")]),a._v(".")])]),a._v(" "),t("h3",{attrs:{id:"translations"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#translations"}},[a._v("#")]),a._v(" Translations")]),a._v(" "),t("p",[a._v("Added German and Portuguese (Brazilian) translations.")]),a._v(" "),t("p",[t("strong",[a._v("Known Limitations:")]),a._v(" Not all translations are verified yet.")]),a._v(" "),t("h3",{attrs:{id:"app-data-backup"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#app-data-backup"}},[a._v("#")]),a._v(" App Data Backup")]),a._v(" "),t("p",[a._v("Install app only for the current user at the time of restoring backups. Support for split apks is also added.")]),a._v(" "),t("p",[t("em",[a._v("Data backup feature is now considered unstable. If you encounter any problem, please report to me without hesitation.")])]),a._v(" "),t("h2",{attrs:{id:"v2-5-12-341"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#v2-5-12-341"}},[a._v("#")]),a._v(" v2.5.12 (341)")]),a._v(" "),t("ul",[t("li",[t("span",[t("TagFeature")],1),a._v("  Added support for splitting data backups into 1GB files to circumvent the limitation of FAT32 file system")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added the ability to unblock trackers")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added an option to skip signature checks while restoring backups")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v(" "),t("a",{attrs:{href:"https://github.com/termux/termux-app",target:"_blank",rel:"noopener noreferrer"}},[a._v("Termux"),t("OutboundLink")],1),a._v(" support: "),t("code",[a._v("run-as")]),a._v(" debuggable app or run new session as app in the "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#app-info-tab"}},[a._v("App Info tab")])],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Display backup app info in the "),t("RouterLink",{attrs:{to:"/guide/main-page.html"}},[a._v("Main page")])],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Restoring source files (except apk files) disabled on unsupported architecture")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Display confirmation dialog before clearing app data")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Ability to import components disabled using IFW on MAT")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Include external media and obb directory for backups")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Allow importing existing rules by other apps or tools")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added an option to extract app icon in "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#app-info-tab"}},[a._v("App Info tab")])],1),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Display restore and delete backups only for apps with backup")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Display progress indicator while taking backups")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Display progress indicator while loading app ops")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed app not opening in the latest and the only supported Aurora Droid (v1.0.6)")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed crash on night mode change while browsing "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html"}},[a._v("App Details page")])],1),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed crash when trying to open external apk file")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed NullPointerException when an external data directory is null")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed toolbar in full screen dialog")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed case insensitive searching")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Optimized app theme")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Replaced AndroidShell with LibSuperuser")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Request external storage permission when saving apk files")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Workaround for AppBarLayout bug in Material Design")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Update external apk info on install/uninstall events")])]),a._v(" "),t("p",[a._v("To use Termux features, make sure you are using Termux v0.96 or later and "),t("code",[a._v("allow-external-apps=true")]),a._v(" is added in "),t("tt",[a._v("~/.termux/termux.properties")]),a._v(".")],1),a._v(" "),t("p",[a._v("Data backup feature is still considered experimental and please do not rely on it to manage your backups yet.")]),a._v(" "),t("h2",{attrs:{id:"v2-5-11-333"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#v2-5-11-333"}},[a._v("#")]),a._v(" v2.5.11 (333)")]),a._v(" "),t("ul",[t("li",[t("span",[t("TagFeature")],1),a._v("  Added experimental support for app data backup. Please test only on apps you don't need. (root only)")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added sharing split apk files as apks (can be installed via "),t("a",{attrs:{href:"https://github.com/Aefyr/SAI",target:"_blank",rel:"noopener noreferrer"}},[a._v("SAI"),t("OutboundLink")],1),a._v(").")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Implemented saving apk files in batch selection mode.")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added what's new for apk file that needs an update (when opening external apk files).")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added an option to apply 1-click ops to system apps (disabled by default).")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added installed app version info in the App Info tab. Clicking the "),t("em",[a._v("i")]),a._v(" icon opens the installed "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#app-info-tab"}},[a._v("App Info")]),a._v(" tab.")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  New on-demand permissions "),t("tt",[a._v("READ_EXTERNAL_STORAGE")]),a._v(" & "),t("tt",[a._v("WRITE_EXTERNAL_STORAGE")]),a._v(" for app backup support")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Display apps that are uninstalled but have backups in the main page")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added a disclaimer")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed selections being not cleared after the task is completed in the main page")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Convert various info in the configurations and features tab to text to improve readability")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fix crash in the "),t("RouterLink",{attrs:{to:"/guide/main-page.html"}},[a._v("Main page")]),a._v(" while filtering apps by search query")],1),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fix crash in the "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#app-info-tab"}},[a._v("App Info")]),a._v(" tab when existence of external data directory has false-positive result")],1)]),a._v(" "),t("p",[t("strong",[a._v("Note:")]),a._v(" Backup data are stored at "),t("tt",[a._v("/sdcard/AppManager")]),a._v(" and apk backups are stored at "),t("tt",[a._v("/sdcard/AppManager/apks")]),a._v(". Data backups are currently not working on Android Lollipop.")],1),a._v(" "),t("h2",{attrs:{id:"v2-5-10-324"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#v2-5-10-324"}},[a._v("#")]),a._v(" v2.5.10 (324)")]),a._v(" "),t("ul",[t("li",[t("span",[t("TagFeature")],1),a._v("  Added 1-click operations (as "),t("RouterLink",{attrs:{to:"/guide/one-click-ops-page.html"}},[a._v("1-Click Ops")]),a._v(" in the menu section in the "),t("RouterLink",{attrs:{to:"/guide/main-page.html"}},[a._v("Main page")]),a._v("): block (ads and) trackers, component blocking by signatures, app op blocking")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added support for external apk: You can now open apk files from your file manager. You can view app details, manifest or scan for trackers directly from there")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added persistent apps filtering option in the "),t("RouterLink",{attrs:{to:"/guide/main-page.html"}},[a._v("Main page")])],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Alternative manifest viewer for installed apks")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Display number of trackers as a tag in the "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#app-info-tab"}},[a._v("App Info")]),a._v(" tab")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added a select all option in the bottom bar in the "),t("RouterLink",{attrs:{to:"/guide/main-page.html"}},[a._v("Main page")]),a._v(" in selection mode")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added links to source code and community")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added support for installing/updating apk files in the "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#app-info-tab"}},[a._v("App Info")]),a._v(" tab (incomplete)")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added an option to import existing disabled components in the Import/Export settings (incomplete)")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added split apk information in "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#app-info-tab"}},[a._v("App Info")]),a._v(" tab")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added an option to open "),t("RouterLink",{attrs:{to:"/guide/main-page.html#termux"}},[a._v("Termux")]),a._v(" in the "),t("RouterLink",{attrs:{to:"/guide/main-page.html"}},[a._v("Main page")]),a._v(" (incomplete)")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Initial support for app banner")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed inconsistency of enable and disable in the App Info tab")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed issue with persistent app cache")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed scrolling issue on settings page")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed crashes when switching to the components tabs for non-root users")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed crash when trying to view summary while scanning is still in progress in the exodus page")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed crashes on devices that does not support data usage")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed crash when trying to view manifest of an split apk")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed wrong package installer name in the "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#app-info-tab"}},[a._v("App Info")]),a._v(" tab")],1),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed changelog formatting for old devices")])]),a._v(" "),t("h2",{attrs:{id:"v2-5-9-315"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#v2-5-9-315"}},[a._v("#")]),a._v(" v2.5.9 (315)")]),a._v(" "),t("ul",[t("li",[t("span",[t("TagFeature")],1),a._v("  Merged "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#app-info-tab"}},[a._v("App Info")]),a._v(" as a single tab in "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html"}},[a._v("App Details")])],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added option to reset all app ops")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added option to revoke all dangerous app ops/permissions")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Highlight trackers in the component tabs")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added option to save manifest and class dump")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added the ability to grant/revoke development permissions")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added sorting options for components, app ops and uses permissions tabs")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added sort by wifi usage in the "),t("RouterLink",{attrs:{to:"/guide/main-page.html#app-usage"}},[a._v("App Usage")]),a._v(" page")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added launch button in the "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#app-info-tab"}},[a._v("App Info")]),a._v(" tab")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added never ask option to usage status prompt")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added long click to select apps in the "),t("RouterLink",{attrs:{to:"/guide/main-page.html"}},[a._v("Main page")])],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added changelog within the app")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Click to select apps during selection mode")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Improved component blocker")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Improved manifest loading for large apps")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Improved tab loading performance")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed app ops checking and custom app ops for some devices")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Disabled activity opening for disabled activities")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Get real activity name for activities that use activity-alias")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed background colors")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed crashing when loading the services tab for non-root users")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed back button for class viewer which was not working")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Changed block icon's colour to accent colour")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Removed translation until the app is complete")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Made links in the credit section clickable")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed various memory leaks")])]),a._v(" "),t("h2",{attrs:{id:"v2-5-8-289"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#v2-5-8-289"}},[a._v("#")]),a._v(" v2.5.8 (289)")]),a._v(" "),t("ul",[t("li",[t("span",[t("TagFeature")],1),a._v("  Added "),t("RouterLink",{attrs:{to:"/guide/settings-page.html#import-export-blocking-rules"}},[a._v("import/export capabilities for blocking rules")])],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added ability to "),t("RouterLink",{attrs:{to:"/guide/settings-page.html#app-theme"}},[a._v("select themes")]),a._v(" (night/day)")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added mode, duration, accept time, reject time for app ops")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Highlight running services")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Highlight disabled components not disabled within App Manager")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added swipe to refresh in the "),t("RouterLink",{attrs:{to:"/guide/main-page.html#app-usage"}},[a._v("App Usage")]),a._v(" page")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added screen time percentage with indicator")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Separate instructions and about pages with fullscreen dialog for both")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Rounded overflow menu (still incomplete)")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed various device/SDK specific app ops issues")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Stability improvements of the entire apps")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Added "),t("tt",[a._v("ACCESS_NETWORK_STATE")]),a._v(" permission to support older operating systems")],1),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed deleting all IFW rules when selecting "),t("RouterLink",{attrs:{to:"/guide/settings-page.html#global-component-blocking"}},[a._v("Global Component Blocking")])],1),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed various search issues")])]),a._v(" "),t("h2",{attrs:{id:"v2-5-7-265"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#v2-5-7-265"}},[a._v("#")]),a._v(" v2.5.7 (265)")]),a._v(" "),t("ul",[t("li",[t("span",[t("TagFeature")],1),a._v("  Initial support for "),t("RouterLink",{attrs:{to:"/guide/adb-over-tcp.html"}},[a._v("ADB over TCP")]),a._v(" (port 5555) for non-root users")],1),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed importing rules from "),t("a",{attrs:{href:"https://github.com/tuyafeng/Watt",target:"_blank",rel:"noopener noreferrer"}},[a._v("Watt"),t("OutboundLink")],1),a._v(" and "),t("a",{attrs:{href:"https://github.com/lihenggui/blocker",target:"_blank",rel:"noopener noreferrer"}},[a._v("Blocker"),t("OutboundLink")],1)]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Display Aurora Droid in "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#app-info-tab"}},[a._v("App Info")]),a._v(" page as a first priority over F-Droid")],1),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Improved performance for component blocking")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed app op mode detection issue")])]),a._v(" "),t("p",[t("strong",[a._v("For root users:")]),a._v(" If you've skipped "),t("a",{attrs:{href:"#v2-5-6-233"}},[a._v("v2.5.6")]),a._v(", you may need to apply all rules globally by applying "),t("RouterLink",{attrs:{to:"/guide/settings-page.html#global-component-blocking"}},[a._v("Global Component Blocking")]),a._v(" in Settings in order for them to work.")],1),a._v(" "),t("h2",{attrs:{id:"v2-5-6-233"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#v2-5-6-233"}},[a._v("#")]),a._v(" v2.5.6 (233)")]),a._v(" "),t("ul",[t("li",[t("span",[t("TagFeature")],1),a._v(" "),t("RouterLink",{attrs:{to:"/guide/main-page.html#batch-operations"}},[a._v("Batch operations")]),a._v(" in the main page: clear app data, disable run in background, disable/kill/uninstall apps (click on the app icon to select)")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Full support of "),t("a",{attrs:{href:"https://github.com/lihenggui/blocker",target:"_blank",rel:"noopener noreferrer"}},[a._v("Blocker"),t("OutboundLink")],1),a._v("'s exported files which was broken due to a bug in Blocker")]),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Reimplementation of blocking activities, receivers, services and providers")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Removed ConstraintLayout dependency therefore a potential decrease in app size")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed duplicate app usage warning in the "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#app-info-tab"}},[a._v("App Info")]),a._v(" page")],1),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Fixed crash when an app icon is not found in "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html"}},[a._v("App Details")]),a._v(" page")],1)]),a._v(" "),t("p",[t("strong",[a._v("Note for root users:")]),a._v(" In order to ensure that the previous blocking rules are preserved with the new blocking implementation, this update reads from the previous rules consequently increasing the loading time in the "),t("RouterLink",{attrs:{to:"/guide/main-page.html"}},[a._v("Main page")]),a._v(". This feature will be removed in the next release but can still be simulated by applying "),t("RouterLink",{attrs:{to:"/guide/settings-page.html#global-component-blocking"}},[a._v("global component blocking")]),a._v(" in Settings.")],1),a._v(" "),t("h2",{attrs:{id:"v2-5-5-215"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#v2-5-5-215"}},[a._v("#")]),a._v(" v2.5.5 (215)")]),a._v(" "),t("ul",[t("li",[t("span",[t("TagFeature")],1),a._v("  Added "),t("RouterLink",{attrs:{to:"/guide/main-page.html#running-apps"}},[a._v("Running Apps/Process Viewer")]),a._v(" (requires root)")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added "),t("RouterLink",{attrs:{to:"/guide/main-page.html#app-usage"}},[a._v("Usage Details Viewer")])],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Added "),t("RouterLink",{attrs:{to:"/guide/main-page.html#apk-updater"}},[a._v("Apk Updater")]),a._v(" and "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html#actions-in-app-info-tab"}},[a._v("Aurora Store")]),a._v(" support")],1),a._v(" "),t("li",[t("span",[t("TagFeature")],1),a._v("  Save modified values of app ops and permissions to the disk (on progress)")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Uninstall support for non-root users")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Restructure app usage")]),a._v(" "),t("li",[t("span",[t("TagFix")],1),a._v(" Added more clarity as well as improve performance in the "),t("RouterLink",{attrs:{to:"/guide/app-details-page.html"}},[a._v("App Details")]),a._v(" page")],1)])])}),[],!1,null,null,null);e.default=s.exports}}]);