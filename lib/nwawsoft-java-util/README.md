# nwawsoft-java-util
A package with functions and data structures that extend the Java programming language.

Data structures include List, Stack, Queue, Tree, Graph and more.

Provides a ton of additional functions for native data types (mainly char and String).

This is the go-to library to include in any project if you want to start fresh with a lot of tools up your sleeves.

## Documentation
You can find the full JavaDoc [here](https://java-util.nwawsoft.com/docs/).

---
## Adding this library to your project
There are at least 5 ways to include this library in your project. We will breeze through them quickly.
### Method 1) As a ```git submodule```:
To add the library to your git project as a git submodule into your lib directory, assuming you are in a terminal in your project root, make the following call
```
git submodule add -b master https://github.com/nwawrzyniak/nwawsoft-java-util lib/nwawsoft-java-util
```
There is a catch though. Every user cloning a project with a git submodule will only see an empty directory where the submodule should be. To fix this and load ("update") the contents of this submodule, assuming you are in a terminal in your project root, use
```
git submodule update --init lib/nwawsoft-java-util
```
From time to time its also worth considering updating the library to a newer version. If you want to update this library to its latest commit, assuming you are in a terminal in your project root, use
```
git submodule update --remote lib/nwawsoft-java-util
git add lib/nwawsoft-java-util
git commit -m "Updated library nwawsoft-java-util to latest commit."
git push
```
### Method 2) Via ```Maven```, ```JitPack``` and ```pom.xml```:
Add the following two snippets somewhere between the ```<project>``` and ```</project>``` tag of your ```pom.xml```
```
  <repositories>
    <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
    </repository>
  </repositories>
```
```
  <dependencies>
    <dependency>
      <groupId>com.github.nwawrzyniak</groupId>
      <artifactId>nwawsoft-java-util</artifactId>
      <version>master-SNAPSHOT</version> <!-- or some other version if you want a specific one -->
    </dependency>
  </dependencies>
```
### Method 3) As a ```git subtree```:
To add the library to your git project as a git subtree into your lib directory, assuming you are in a terminal in your project root, make the following call
```
git subtree add --prefix lib/nwawsoft-java-util https://github.com/nwawrzyniak/nwawsoft-java-util.git master --squash
```
To update the library if it was installed this way use 
```
git subtree pull --prefix lib/nwawsoft-java-util https://github.com/nwawrzyniak/nwawsoft-java-util.git master --squash
```
### Method 4) Cloning this repository:
To add this library to your project into your lib directory by cloning this repository, assuming you are in a terminal in your project root, make the following calls
```
mkdir lib
cd lib/
git clone https://github.com/nwawrzyniak/nwawsoft-java-util.git
```
To update the library if it was installed this way use 
```
cd lib/nwawsoft-java-util
git pull
```
Note that this only makes sense if you are not using git for your project anyway. If you do, methods 1 and 3 may be the more integrated and/or cleaner options.
### Method 5) Downloading the source files manually (no easy updating):
[Dowload the source files as a .zip archive](https://github.com/nwawrzyniak/nwawsoft-java-util/archive/master.zip).

It is recommended to put the files in a new subdirectory inside of ```myProjectRoot/lib/```, preferably called ```nwawsoft-java-util``` to never mix up the contents of your lib directory.

To update the library if it was installed this way you have to redownload the current version via the same link and copy the new files over the old ones.

---
## Improving this library
Requests for functions that are general enough to be included in a standard library are welcome and should be posted as an [Issue](https://github.com/nwawrzyniak/nwawsoft-java-util/issues).

---
## Disclaimer
I do not take any warranty for the usage of this library, though I intend to maintain this library as well as possible and will respond to every feedback I receive. Currently the library is getting a complete overhaul.
