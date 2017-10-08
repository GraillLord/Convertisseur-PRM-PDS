@echo off

:CheckOS
::Check OS architecture
IF EXIST "%PROGRAMFILES(X86)%" (GOTO 64BIT) ELSE (GOTO 32BIT)

:64BIT
::Check Java Version
echo 64
PATH %PATH%;%JAVA_HOME%\bin\
for /f tokens^=2-5^ delims^=.-_^" %%j in ('java -fullversion 2^>^&1') do set "jver=%%j%%k%%l%%m"
if %jver% LEQ 18000 start "" "%~dp0\x64\bin\v1_6.jar"
if %jver% GEQ 18000 start "" "%~dp0\x64\bin\v1_8.jar"
exit \b

:32BIT
::Check Java Version
echo 32
PATH %PATH%;%JAVA_HOME%\bin\
for /f tokens^=2-5^ delims^=.-_^" %%j in ('java -fullversion 2^>^&1') do set "jver=%%j%%k%%l%%m"
if %jver% LEQ 18000 start "" "%~dp0\x86\bin\v1_6.jar"
if %jver% GEQ 18000 start "" "%~dp0\x86\bin\v1_8.jar"
exit \b