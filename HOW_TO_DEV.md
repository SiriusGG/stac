# How to build STAC from source

STACs development _was_ started as a one-man-project in 2020, however the code has been open source since day 1. The STAC team encourages everyone to contribute to the project, be it with great ideas or bug reports at the [Issues](https://github.com/JGC-Sirius/siris-trick-attempt-counter/issues) tab or via [Pull Request](https://github.com/JGC-Sirius/siris-trick-attempt-counter/pulls).

To compile STAC (Siri's Trick Attempt Counter) from source or make changs to the source code, you should follow these steps:

0) Install [git](https://git-scm.com/downloads).

1) Clone the repository, e.g. with ```git clone https://github.com/JGC-Sirius/siris-trick-attempt-counter.git```.

2) Load all dependencies with ```git submodule update --init```.

3) Open the project directory in any IDE. I recommend using [IntelliJ IDEA Community Edition](https://www.jetbrains.com/de-de/idea/download/), since you only have to load the .iml file and you are ready to go.

4) If not all dependencies were found, make sure all directories named ```java``` are marked as sources roots and the libraries in the ```lib``` directory are in the classpath.

## Libraries used:
- [jnativehook](https://github.com/kwhat/jnativehook)
- [nwawsoft-java-util](https://java-util.nwawsoft.com/)
