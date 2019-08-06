@echo OFF

echo Dropping and rebuilding database
sqlcmd -S tcp:127.0.0.1 -U sa -P PMadmin42 -i config/localdb/RebuildDatabase.sql