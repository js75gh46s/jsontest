setlocal
cd %~dp0
cd shared\js\target\scala-2.12\scalajs-bundler\test
call node .\node_modules\karma\bin\karma start karma.conf.js
