# [HenrySoftwareStudio](https://henrysoftwarestudio.github.io/)/[MathUIEngine](https://github.com/HenrySoftwareStudio/MathUIEngine)<br> NOTE: DO NOT MESS WITH MY ACTIONS, **ESPECIALLY `AUTO RELEASE`**
## Statues
- [![Codacy Badge](https://app.codacy.com/project/badge/Grade/0fc8cfdbe3dc4b7e8af30bb560185487)](https://www.codacy.com/gh/HenrySoftwareStudio/MathUIEngine/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=HenrySoftwareStudio/MathUIEngine&amp;utm_campaign=Badge_Grade)
- [![Auto Release](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/autoRelease.yml/badge.svg)](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/autoRelease.yml)
- [![GitGuardian scan](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/GitGuardian%20Scan.yml/badge.svg)](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/GitGuardian%20Scan.yml)
- [![CodeFactor](https://www.codefactor.io/repository/github/henrysoftwarestudio/mathuiengine/badge)](https://www.codefactor.io/repository/github/henrysoftwarestudio/mathuiengine)
- [![OSSAR](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/ossar-analysis.yml/badge.svg)](https://github.com/HenrySoftwareStudio/MathUIEngine/actions/workflows/ossar-analysis.yml)

## To Leave Your Mark
> Fork this repo (or not)\
> **THEN LOOK AT THE [CODE OF CONDUCT](https://github.com/HenrySoftwareStudio/MathUIEngine/blob/main/CODE_OF_CONDUCT.md) FIRST**\
> **THEN LOOK AT THE [CONTRIBUTING](https://github.com/HenrySoftwareStudio/MathUIEngine/blob/main/CONTRIBUTING.md) NEXT**\
> Read the Task list\
> Do what ever you want with it at your own fork, but do remember to make codacy happy\
> Create a PR\
> And you are set, Code owner will be checking over the PR asap

## Tasks
### Status Names

| Status Name | Definition |
| --- | --- |
| Delayed | Planned earlier but then move down on priority level |
| Working On | Currently working on this task |
| Proposed | Planned, not yet started |
| Completed | Task is finished, no more work will be done unless reopened by setting its status back to proposed |

### Task List

| Status | Name |
| --- | --- |
| Delayed | Implement Multilingual Support via Reading XML File Containing UI Text |
| Completed? by @HenrySoftwareStudio | Configure and set up setting behaviors in UI |
| Completed by @HenrySoftwareStudio at 12/30/2021 | Implement Stored Setting Values |

## Note To Managers and Others Who Has Edit Rights
**When you finish reviewing a PR, do NOT delete the originating branch**\
This is do for convenient reverts in case it is needed.

## To Use this Program
I don't care what you do with it as long as it is under the [license terms](https://github.com/HenrySoftwareStudio/MathUIEngine/blob/main/LICENSE.txt).

## Make a Program Usable for this Program
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
