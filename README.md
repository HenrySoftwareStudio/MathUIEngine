# MathUIEngine
##  
### Statues:
- [![Lint Code Base](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/QATool.yml/badge.svg)](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/QATool.yml)
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
