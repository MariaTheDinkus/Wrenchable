# Wrenchable
A lightweight, JiJ-able wrench library which aims to make wrenches compatible between mods.

To include it in your project, add this to your build.gradle:
```groovy
maven {
	name = "Wrenchable"
	url  "https://dl.bintray.com/zundrel/wrenchable"
}
dependencies {
    modApi 'com.zundrel:wrenchable:LATEST'
    include 'com.github.Zundrel:wrenchable:LATEST'
}
```
Where `LATEST` is the latest tag under the releases tab.
