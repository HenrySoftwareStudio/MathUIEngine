# [HenrySoftwareStudio](https://github.com/HenrySoftwareStudio)/[MathUIEngine](https://github.com/HenrySoftwareStudio/MathUIEngine)
##  NOTE: DO NOT MESS WITH MY ACTIONS, *ESPECIALLY AUTO RELEASE*
### Statues:
- [![Codacy Badge](https://app.codacy.com/project/badge/Grade/0fc8cfdbe3dc4b7e8af30bb560185487)](https://www.codacy.com/gh/HenrySoftwareStudio/MathUIEngine/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=HenrySoftwareStudio/MathUIEngine&amp;utm_campaign=Badge_Grade)
- [![Lint Code Base-AutoScan](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/QATool-AutoScan.yml/badge.svg)](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/QATool-AutoScan.yml)
- [![Lint Code Base](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/QATool-FullScan.yml/badge.svg)](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/QATool-FullScan.yml)
- [![Auto Release](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/autoRelease.yml/badge.svg)](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/autoRelease.yml)
- [![GitGuardian scan](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/GitGuardian%20Scan.yml/badge.svg)](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/GitGuardian%20Scan.yml)
### Usage:
Using xml formating.\
Format as below, [values] must be kept as is while args can be changed to suit needs.\
Note that the block of values is where values are fed into you program, values count property will tell the engine how many values fields to initiate, first value will be the first in the value block and second will follow.\
On UI, the **first value** will be **on top** and the **last** will be **on bottom**.\
full answer will be used as is in program, partial answer will have `"The answer is: "` append to the front.\
`<function name="insert your name here" launchArgs="{arg1, arg2, arg3, [values]}" tooltip="tooltip message" valuesCount="how many values you need", answerType="partial/full (one of the two)">`\
Whole file should look like:\
`<insert you xml version here>`\
`<functions>`\
`<function ...>`\
`<funciton ...>`\
`<function ...>`\
`</functions>`
