# Setting up quick starting for STAC

This section will show the process for Windows users. Linux users will already know how to set their PATH.

## Method 1 (recommended): Add STAC to the PATH variable

### 1) (Optional) Move the STAC directory

Move your STAC folder to a fitting place. The "Downloads" folder is _not_ a fitting place.

In my example I will create a directory "Portable_Software" in ```C:/```.

You could just as well use anything like the ```C:/Program Files``` or ```C:/ProgramData``` directories or any user directory like ```C:/Users/yourname/Software```.

### 2) (Optional) Create a "Shortcuts" directory

It is a generally cleaner to store shortcuts to frequently used programs in a common directory rather than scattered all over the system. Having such a directory removes all further pain from adding other programs to your PATH variable.

Create a directory called "Shortcuts" or something similar you like (maybe even "bin", though we will use it only for links to applications instead of the real binary applications themselves) in a well-accessible location like your system root (e.g. ```C:/Shortcuts```).

Create a shortcut to ```stac.jar```, make sure its name is ```stac.lnk``` and put it into your new directory.

### 3) Open the Environment Variables editor

1) Press ```WIN+Pause``` to open the System Properties Dialog.

2) Select "Advanced system settings" from the left menu.

3) Select "Environment Variables..."

### 4) Choosing the correct PATH variable

If you selected a user directory (anything _within_ ```C:/Users/yourname```) follow step 4a.

If you selected a system directory (anything _not_ within ```C:/Users/yourname```) follow step 4b.

#### 4a) Choosing the user-PATH variable

From the **upper** block  ("User variables for") select "Path" or "PATH" and click the **upper** "Edit..." button.

#### 4b) Choosing the system-PATH variable

From the **lower** block ("System variables") select "Path" or "PATH" and click the **lower** "Edit..." button.

### 5) Add the correct directory

If you followed the optional steps 1 and 2 (recommended), add the directory you created in step 2.

If you only followed the optional step 1 but not 2, add the directory you created in step 1.

If you only followed step 3 and forward, add whatever directory ```stac.jar``` is currently located in.

### 6) Done.

You can now start STAC from the "Run" dialog (WIN+R) or from the terminal by calling ```stac```.

## Method 2 (the "casual" way)

### 1) (Optional) Move the STAC directory

Move your STAC folder to a fitting place. The "Downloads" folder is _not_ a fitting place.

In my example I will create a directory "Portable_Software" in ```C:/```.

You could just as well use anything like the ```C:/Program Files``` or ```C:/ProgramData``` directories or any user directory like ```C:/Users/yourname/Software```.

### 2) Create a shortcut to the ```.jar``` file.

Right-click ```stac.jar``` and select "Create shortcut".

Move the shortcut to any place you like, e.g. your Desktop.
