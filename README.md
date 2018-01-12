# Geographic_WIFI

OOP course, Ariel University.

Updated to: Stage#3, 12/1/18
Please note that until now, readme had only theoretical explanations about this project, now it includes the instructions for running this project as well.

RUNNING THE PROJECT:
To run the project as required in phase 3 please follow these instructions:
	1) Clone the project.
	2) Change the path in the geo_wifi class to the right directory in your PC, after cloning it.
	3) Run geo_wifi class.

EXPLANATIONS ABOUT THE PROJECT:
Our project has 5 packages, each one is responsible for a different part of the project.
(For a full depth explanation please read the "oop project" file in the project source files.)

Wifi_Data holds all the objects we defined in this project to hold our data in different views, so that we will be able to work properly with the information we have and use the right amount of memory we need for each purpose.
Filter package belongs to the third stage of our project, and it allows the GUI to make better filtering and apply more filters, now the data can be filtered not only by ID, Location and Time, but we can also apply these three at the same time or even apply a 'NOT' filter on them and combine with others.
geo_wifi package is the package responsible for the GUI of our project. It contains only one class, and of course it uses the other packages in our project to execute the operations in the GUI.
General package holds the main classes in the project, that are with us since stage 0 and 1 in this project. The classes are relevant to working with a Csv file, a KML file by the JAK library, and also contains the Time object we use in this project.
Algorithms package holds all relevant calculations and functions for executing Algorithms 1&@ as required in stage 2 of this project.

Please note that this is very generic information about the project and the deeper explanations are in the "oop project" file as mentioined above.

NEXT PHASE:
-Connecting the project to a Database.
-Using Gradle build tool to manage the project better.
