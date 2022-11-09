# cps-tests
This repo contains automatic tests for certain web app, which has a proprietary character. 

These are my first tests created using Selenium Webdriver and Java along with TestNG. 

This project has helped me to get more familiar with using git(e.g. branching), maven, testng, xpath selectors and so on. 

Approximate Project structure(there can be a slightly differences between class names present in repo and on the structure below). 

![2022-11-08_11h37_55](https://user-images.githubusercontent.com/99602564/200543101-2411bf1d-cfcc-4f0f-b241-9bbbe4c26e45.png)

Short description: 

[main]

pl.config.AppPages, this class is responsible for holding app pages addresses and performing basic operations on them, such as going to main page. 
pl.config.DataFiles, this enum is responsible for storing paths to necessary files/directories(such as path to csv file that contains data used in tests)
pl.config.WebDrivers, this class is responsible for storing chromedriver path etc. 

pl.operations.CsvReader, this class is responsible for reading a ceratin data from csv file
pl.operations.Screenshot, this class is responsible for taking snapshots

pl.pages - this package contains app pages that are being tested. Elements on the page are identified using XPath. 

[tests]

At this moment - tests have only positive meaning, i.e. they check whether app behaves properly when correct data is given. 

tests.test-input-data.db-connections - this directory is responsible for storing tests inputs, at this moment only data used to check db connection is being stored(inside SettingsData.csv file) 
tests.tests-screenshots - when snapshot is taken, the results are stored inside this directory 
