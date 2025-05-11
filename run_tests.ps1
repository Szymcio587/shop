$srcPath = "src"
$testPath = "test"
$binPath = "bin"
$libPath = "lib\junit-platform-console-standalone-1.11.4.jar"

if (-not (Test-Path $binPath)) {
    New-Item -ItemType Directory -Path $binPath | Out-Null
}

javac -cp $libPath -d $binPath (Get-ChildItem -Path $srcPath -Filter *.java -Recurse).FullName + `
                                   (Get-ChildItem -Path $testPath -Filter *.java -Recurse).FullName

java -jar $libPath --class-path $binPath --scan-classpath
