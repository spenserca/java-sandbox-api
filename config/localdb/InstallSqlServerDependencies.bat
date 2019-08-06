@echo OFF

echo Installing SQL Server 2017 - Developer Edition
choco install sql-server-2017 -y --params="'/SAPWD:PMadmin42 /SECURITYMODE:SQL /TCPENABLED:1'"

echo Installing SQL server command line tools
choco install sqlserver-cmdlineutils -y
