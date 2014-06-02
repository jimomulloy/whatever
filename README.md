# Whatever The Weather Demo Application

##Comments based on http://spring-petclinic.cloudfoundry.com/

##What is it
A demo application supporting a variety of cloud based weather services.

##Why
As a calling card to support furutre job applications and as an exercise in exploring and learning about a variety of service oriented application design principles.

##Based on
1. The Weather app used in Maven: The Definitive Guide (OReilly)
2. The CI examples in Jenkins: The Definitive Guide (OReilly)
3. ....

##Architectural principles
1. Modularity.
2. Encapsulation, separation of concerns, Loose coupling.
3. Flexible, Extensible.
4. Distributed processing
5. Asynchronous processing
6. Variety of front ends, web, mobile, desktop
7. SOA, Web Services
8. Cloud deployment.
9. Continuous Integration, build, test, (unit, integration, UAT, performance, smoke test,) deploy.
10. Source code management with GIT, branches and master development.
11. Architect for OSGi.

## What does it look like?
wtw will be deployed here on aws: http://54.72.213.202:8080/wtw

## Running wtw locally
```
	git clone https://github.com/jimomulloy/whatever.git
	mvn jersey:run
```

You can then access wtw here: http://localhost:8080/wtw/

## Working with WTW in Eclipse/STS

### prerequisites
The following items should be installed in your system:
* Maven 3 (http://www.sonatype.com/books/mvnref-book/reference/installation.html)
* git command line tool (https://help.github.com/articles/set-up-git)
* Eclipse with the m2e plugin (m2e is installed by default when using the STS (http://www.springsource.org/sts) distribution of Eclipse)

Note: when m2e is available, there is an m2 icon in Help -> About dialog.
If m2e is not there, just follow the install process here: http://eclipse.org/m2e/download/


### Steps:

In the command line
```
git clone https://github.com/jimomulloy/whatever.git
```
Inside Eclipse: 
```
File -> Import -> Maven -> Existing Maven project
```


## Looking for something in particular?
