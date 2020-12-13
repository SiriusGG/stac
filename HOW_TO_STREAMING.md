# Integrating STAC into your streaming software

For this section we will use OBS Studio, though for most broadcasting or streaming softwares the process is the same.

1) Open the Trick Visualization Frame by loading a .sacf file or by selecting "Create New Trick File".

2) Add the Visualization source in OBS Studio. In OBS Studio, within the "Sources" tab:
- select "+"
- "Window Capture"
- "Create new"
- name it something like "STAC"
- for "Window" select "Visualization"
- set "Window Match Priority" to "Window title must match" (optional, but recommended)
- hit "OK"

3) Set the Scale Filtering of the STAC source to **anything except "Disable"**. **This is important!** In OBS Studio:
- right-click the STAC source
- select "Scale Filtering" -> and e.g. "Bilinear"

4) Add a Color Key to make the white background of the frame transparent. In OBS Studio:
- right-click the STAC source
- select "Filters"
- click "+"
- select "Color Key"
- set "Key Color Type" to "Custom Color"
- select ```#ffffff``` (pure white) from the "Select color" menu

5) (OPTIONAL) Invert the text color from black to white. In OBS Studio:
- right-click the STAC source
- select "Filters"
- click "+"
- select "Color Correction"
- turn "Contrast" down
