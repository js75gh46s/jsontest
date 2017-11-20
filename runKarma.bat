setlocal
call sbt jsontest-sharedJS/test:npmUpdate jsontest-sharedJS/test:buildKarma
cd shared\js\target\scala-2.12\scalajs-bundler\test
call node .\node_modules\karma\bin\karma start karma.conf.js --single-run
