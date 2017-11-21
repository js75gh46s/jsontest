@echo off
setlocal

goto processArgs
:help
echo Syntax: %0 [run]
echo Where: run - stay running
goto :eof
:processArgs

if ".%1" == ".-h" goto help
if ".%1" == ".-?" goto help
if ".%1" == ".--help" goto help

if not ".%1" == ".run" set XXSR="--single-run"

call sbt jsontest-sharedJS/test:npmUpdate jsontest-sharedJS/test:buildKarma
cd shared\js\target\scala-2.12\scalajs-bundler\test
call node .\node_modules\karma\bin\karma start karma.conf.js %XXSR%
