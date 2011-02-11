Introduction  
============
The BDB (Jave Edition) is a basic interactive shell. It's pretty
straight forward, I call the BDB API for the methods described below.

How to Use  
==========
1. Do a maven build: mvn clean package  
2. bdb-shell-0.0.1-SNAPSHOT-jar-with-dependencies.jar will be generated
   in target folder.  
3. In command line: `java -jar bdb-shell-0.0.1-SNAPSHOT-jar-with-dependencies.jar`
3. You will see the `bdb>` command line.  
4. Give the path to the folder where you BDB database is present (the
   `.jdb` file).  
5. Try out the commands listed below!  


Methods Supported  
=================
get key  
put key value  
delete key  
exit  
help  

Contact  
=======
Please put up your questions, feature requests, bugs [here][1]


  [1]: https://github.com/utkarsh2012/bdb-interactive-shell/issues
