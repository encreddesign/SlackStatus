@echo off

set AppPath=SlackStatus.jar

if exist %AppPath% start "SlackStatus" "%AppPath%" -update