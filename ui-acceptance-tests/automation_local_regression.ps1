# Setting up some report data
$ShouldDisplayReport = Read-Host -Prompt 'Would you like to view the report after the test run locally? [Y] Yes / [N] No'
$ShouldDisplayReport = switch ($ShouldDisplayReport) { {$_ -eq "Yes" -or $_ -eq  "Y"}{$True} default{$false}}

$Browser = Read-Host -Prompt 'Which browser would you like to execute in? chrome or firefox'
$Browser = switch ($Browser) { {$_ -eq "firefox" -or $_ -eq  "Firefox"}{'firefox'} default{'chrome'}}

# Kill all existing chromedriver and geckodriver processes to avoid leakage
Stop-Process -Force -Name chromedriver
Stop-Process -Force -Name geckodriver
Stop-Process -Force -Name javaw
Stop-Process -Force -Name java

# Move to the execution location of the script itself
Set-Location -Path $PSScriptRoot

# Clean up the test framework results and artifacts work space
cmd.exe /c "mvn clean"

# Execute full regression tests (scenarios tagged with '@regression' //todo -> customise more and more of these options or take varargs...
cmd.exe /c "mvn test -Dbrowser=$Browser -Dgui.feature.tags=@regression -Drun.on.grid=false -Dforked.jvm.count=3"


# Display the report locally if ShouldDisplayReport is true
if($ShouldDisplayReport) {
cd target
allure serve
}