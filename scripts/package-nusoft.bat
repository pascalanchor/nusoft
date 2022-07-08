@echo off

SETLOCAL EnableDelayedExpansion
SET PRJ=avh.nusoft.api

REM MODEL
SET MODULE=%PRJ%.model
ECHO "---> BUILDING %MODULE%"
ECHO "	---> PACKAGING %MODULE%..."
call mvn -f ..\code\%MODULE% package -DskipTests
ECHO.
ECHO "	---> INSTALLING %MODULE%..."
call mvn install:install-file -Dfile=..\code\%MODULE%\target\%MODULE%-0.0.1-SNAPSHOT.jar -DgroupId=%PRJ% -DartifactId=%MODULE% -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar

REM API MODEL
SET MODULE=%PRJ%.services.model
ECHO "---> BUILDING %MODULE%"
ECHO "	---> PACKAGING %MODULE%..."
call mvn -f ..\code\%MODULE% package -DskipTests
ECHO.
ECHO "	---> INSTALLING %MODULE%..."
call mvn install:install-file -Dfile=..\code\%MODULE%\target\%MODULE%-0.0.1-SNAPSHOT.jar -DgroupId=%PRJ% -DartifactId=%MODULE% -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar

REM SERVICES IMPLEMENTATION
SET MODULE=%PRJ%.services.impl
ECHO "---> BUILDING %MODULE%"
ECHO "	---> PACKAGING %MODULE%..."
call mvn -f ..\code\%MODULE% package -DskipTests
ECHO.
ECHO "	---> INSTALLING %MODULE%..."
call mvn install:install-file -Dfile=..\code\%MODULE%\target\%MODULE%-0.0.1-SNAPSHOT.jar -DgroupId=%PRJ% -DartifactId=%MODULE% -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar

REM SECURITY
SET MODULE=%PRJ%.security
ECHO "---> BUILDING %MODULE%"
ECHO "	---> PACKAGING %MODULE%..."
call mvn -f ..\code\%MODULE% package -DskipTests
ECHO.
ECHO "	---> INSTALLING %MODULE%..."
call mvn install:install-file -Dfile=..\code\%MODULE%\target\%MODULE%-0.0.1-SNAPSHOT.jar -DgroupId=%PRJ% -DartifactId=%MODULE% -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar

REM SERVICES
SET MODULE=%PRJ%.services
ECHO "---> BUILDING %MODULE%"
ECHO "	---> PACKAGING %MODULE%..."
call mvn -f ..\code\%MODULE% package -DskipTests
ECHO.
ECHO "	---> INSTALLING %MODULE%..."
call mvn install:install-file -Dfile=..\code\%MODULE%\target\%MODULE%-0.0.1-SNAPSHOT.jar -DgroupId=%PRJ% -DartifactId=%MODULE% -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar

REM SERVICES
SET MODULE=%PRJ%
ECHO "---> BUILDING %MODULE%"
ECHO "	---> PACKAGING %MODULE%..."
call mvn -f ..\code\%MODULE% package -DskipTests
