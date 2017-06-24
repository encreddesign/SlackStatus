@echo off

set app=SlackStatus.jar
set app_title=SlackStatus
set arg_update="%1"
set arg_status="%2"

if %arg_update% == "" echo Cli error: -update argument required

if %arg_status% neq "" (

	if %arg_status% == "away" (
	
		echo Setting presence to away
		start "%app_title%" "%app%" -update away
		
	) else if %arg_status% == "auto" (
	
		echo Setting presence to auto
		start "%app_title%" "%app%" -update auto
		
	) else (
		echo Cli error: Status %arg_status% not recognized
	)
) else (

	start "%app_title%" "%app%" -update
	echo Slack Status updated
	
)