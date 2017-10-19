setlocal
cd %~dp0
call sbt jsontest-sharedJS/test:npmUpdate jsontest-sharedJS/test:buildKarma
call runKarma.bat
