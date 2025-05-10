$srcPath = "src"
$binPath = "bin"
$libPath = "lib\junit-platform-console-standalone-1.11.4.jar"
$mainClass = "com.example.shop.Main"

if (-not (Test-Path $binPath)) {
    New-Item -ItemType Directory -Path $binPath | Out-Null
}

javac -cp $libPath -d $binPath (Get-ChildItem -Path $srcPath -Filter *.java -Recurse).FullName

$ordersFile = "E:\Nauka\Java jest super\Supermarket\json\orders.json" # absolute path
$paymentsFile = "json\paymentmethods.json" # relative path

java -cp "$binPath" $mainClass "$ordersFile" "$paymentsFile"