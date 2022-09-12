# FILE-I-O-
Written in plain java.
This repository involves file I/O and reading of config files as soon as the application loads.  
Command line arguments are used to specify the environment which invariably dictates the config file to read.
Below is an illustration
For Production environment,  java Main.java is used.
For Staging, java Main.java staging is used
For Development, java Main.java development is used.
The environment specifies the file being read from.
For example, if the environment passed to it is “production” the file  read
was filename + environment;
if the name of the file is not passed to the
constructor, it automatically reads from the production environment.
Here is a format of an example config file:
dbname=sq04_db
host=127.0.0.1
port=8080
[application]
name=fintek
port=8081
context-url=/api/v1
mode=dev
theme=yellow
pipeline=fast
[application]
name=fintrack
For each of these values, the values are used by calling the
get methods and passing the keys e.g
String dbName = config.get(‘dbname’);
String stagingDbname = config.get(‘application.name’);
Where there are multiple values for a key, the first one that appears in the
config file was used. For example
• If the get method for the key ‘application.name’ is called, based on the
example file provided above, it returns the value fintek.
• If the get method for the key ‘dbname’ is called, it returns
‘sq04_db’.
