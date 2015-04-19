# Monitora Server

Command & Control component for an Oracle DB monitorization tool.

## Requisites

*  Tomcat 7
*  Eclipse Kepler
*  Jersey 2.6
*  Jersey testing framework
*  Maven
*  monitora_core


## Installation

1.  Clone repo and monitora_core
2.  Import both in eclipse
3.  Maven `clean install -U` to get dependencies
4.  Deploy war in Tomcat
5.  (Optional) Run junit tests in eclipse


## TODO

*  Detailed description
*  License clarification


## Log

### [19/04/2015]


### [02/04/2015] 
 
* PersistenceTests & AgenteServiceTest fails
* Model Layer has empty constructors and get/set/add/delete methods. Service Layer has createXXX with params to fill mandatory fields and link with existent entities
* Maybe its necessary create additional cascade creation methods 
	
### [14/03/2015]

* I don't remember current status. DB successfully opens, but integrity restrictions fail.
* ClienteServiceTest.update fails when assigning logo field (BLOB in BD, String in class)


---
Thanks to [GitHub Education](https://education.github.com) for support
